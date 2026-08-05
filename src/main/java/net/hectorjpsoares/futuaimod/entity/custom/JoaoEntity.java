package net.hectorjpsoares.futuaimod.entity.custom;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class JoaoEntity extends Frog {
  private static final double TARGET_RANGE = 10.0D;
  private static final double ATTACK_RANGE = 4.0D;
  private static final int TONGUE_DURATION = 12;
  private static final int ATTACK_COOLDOWN = 30;
  private static final String STOLEN_ITEMS_TAG = "JoaoStolenItems";

  private int tongueTimer = 0;
  private int attackCooldown = 0;
  private final Set<Item> stolenItems = new HashSet<>();

  public JoaoEntity(EntityType<? extends Frog> entityType, Level level) {
    super(entityType, level);
  }

  public static AttributeSupplier.Builder createAttributes() {
    return Frog.createAttributes()
        .add(Attributes.MAX_HEALTH, 6.0D)
        .add(Attributes.MOVEMENT_SPEED, 0.25D)
        .add(Attributes.ATTACK_DAMAGE, 2.0D);
  }

  @Override
  public void tick() {
    super.tick();

    if (this.level().isClientSide()) {
      return;
    }

    if (tongueTimer > 0) {
      tongueTimer--;

      if (tongueTimer == 0) {
        stealItem();
        this.setPose(Pose.STANDING);
        this.eraseTongueTarget();
        attackCooldown = ATTACK_COOLDOWN;
      }

      return;
    }

    if (attackCooldown > 0) {
      attackCooldown--;
    }

    Player player = this.level().getNearestPlayer(
        this.getX(),
        this.getY(),
        this.getZ(),
        TARGET_RANGE,
        entity -> !entity.isSpectator()
            && entity.isAlive()
            && hasSphericalItemInHand(entity));

    if (player == null) {
      return;
    }

    this.getLookControl().setLookAt(player, 30.0F, 30.0F);

    double distance = this.distanceTo(player);

    if (distance > ATTACK_RANGE) {
      this.getNavigation().moveTo(player, 1.2D);
      return;
    }

    this.getNavigation().stop();

    if (attackCooldown <= 0) {
      startTongueAttack(player);
    }
  }

  private void startTongueAttack(Player player) {
    this.setTongueTarget(player);
    this.setPose(Pose.USING_TONGUE);
    this.tongueTimer = TONGUE_DURATION;
  }

  private boolean hasSphericalItemInHand(Entity entity) {
    if (!(entity instanceof Player player)) {
      return false;
    }

    return isAvailableSphericalItem(player.getMainHandItem().getItem())
        || isAvailableSphericalItem(player.getOffhandItem().getItem());
  }

  private boolean isAvailableSphericalItem(Item item) {
    return isSphericalItem(item) && !stolenItems.contains(item);
  }

  private boolean isSphericalItem(Item item) {
    return item == Items.SNOWBALL
        || item == Items.ENDER_PEARL
        || item == Items.ENDER_EYE
        || item == Items.SLIME_BALL
        || item == Items.MAGMA_CREAM;
  }

  private void stealItem() {
    if (!(this.getTongueTarget().orElse(null) instanceof Player player)) {
      return;
    }

    Item item = player.getMainHandItem().getItem();

    if (isAvailableSphericalItem(item)) {
      player.getMainHandItem().shrink(1);
      stolenItems.add(item);
      return;
    }

    item = player.getOffhandItem().getItem();

    if (isAvailableSphericalItem(item)) {
      player.getOffhandItem().shrink(1);
      stolenItems.add(item);
    }
  }

  @Override
  public void addAdditionalSaveData(CompoundTag tag) {
    super.addAdditionalSaveData(tag);

    CompoundTag stolenTag = new CompoundTag();

    for (Item item : stolenItems) {
      ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
      stolenTag.putBoolean(id.toString(), true);
    }

    tag.put(STOLEN_ITEMS_TAG, stolenTag);
  }

  @Override
  public void readAdditionalSaveData(CompoundTag tag) {
    super.readAdditionalSaveData(tag);

    stolenItems.clear();

    if (!tag.contains(STOLEN_ITEMS_TAG)) {
      return;
    }

    CompoundTag stolenTag = tag.getCompound(STOLEN_ITEMS_TAG);

    for (String itemId : stolenTag.getAllKeys()) {
      ResourceLocation id = ResourceLocation.tryParse(itemId);

      if (id == null) {
        continue;
      }

      Item item = BuiltInRegistries.ITEM.get(id);

      if (item != Items.AIR) {
        stolenItems.add(item);
      }
    }
  }
}