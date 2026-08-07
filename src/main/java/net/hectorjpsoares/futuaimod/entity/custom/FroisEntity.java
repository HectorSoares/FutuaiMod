package net.hectorjpsoares.futuaimod.entity.custom;

import net.hectorjpsoares.futuaimod.item.ModItems;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;


public class FroisEntity extends Vindicator {
  public FroisEntity(EntityType<? extends Vindicator> entityType, Level level) {
    super(entityType, level);
    this.setItemSlot(EquipmentSlot.MAINHAND, createFroisAxe(level.registryAccess()));
    this.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
  }

  private boolean hasReceivedCrackling = false;
  private boolean hasReceivedBeer = false;
  private boolean isPacified = false;

  public static AttributeSupplier.Builder createAttributes() {
    return Vindicator.createAttributes()
        .add(Attributes.ATTACK_DAMAGE, 4.0D);
  }

  @Override
  public boolean isAggressive() {
    return !isPacified;
  }

  @Override
  public boolean canAttack(LivingEntity target) {
    return !isPacified && super.canAttack(target);
  }

  @Override
  public void setTarget(LivingEntity target) {
    super.setTarget(isPacified ? null : target);
  }

  @Override
  public InteractionResult mobInteract(Player player, InteractionHand hand) {
    ItemStack item = player.getItemInHand(hand);
    boolean gaveCrackling = !isPacified && item.is(ModItems.CRACKLING_PORK.get());
    boolean gaveBeer = !isPacified && item.is(ModItems.COLD_BEER.get());

    if (!gaveCrackling && !gaveBeer) {
      return super.mobInteract(player, hand);
    }

    if (!this.level().isClientSide) {
      if (gaveCrackling) {
        hasReceivedCrackling = true;
      }
      if (gaveBeer) {
        hasReceivedBeer = true;
      }

      item.shrink(1);

      if (hasReceivedCrackling && hasReceivedBeer) {
        isPacified = true;
        this.setTarget(null);
        this.spawnAtLocation(ModItems.PIZZA_FROIS.get());
      }
    }

    return InteractionResult.sidedSuccess(this.level().isClientSide);
  }

  @Override
  protected void dropCustomDeathLoot(ServerLevel level, DamageSource damageSource, boolean recentlyHit) {
    super.dropCustomDeathLoot(level, damageSource, recentlyHit);
    this.spawnAtLocation(createFroisAxe(level.registryAccess()));
  }

  @Override
  public void addAdditionalSaveData(CompoundTag tag) {
    super.addAdditionalSaveData(tag);
    tag.putBoolean("HasReceivedCrackling", hasReceivedCrackling);
    tag.putBoolean("HasReceivedBeer", hasReceivedBeer);
    tag.putBoolean("IsPacified", isPacified);
  }

  @Override
  public void readAdditionalSaveData(CompoundTag tag) {
    super.readAdditionalSaveData(tag);
    hasReceivedCrackling = tag.contains("HasReceivedCrackling")
        ? tag.getBoolean("HasReceivedCrackling")
        : tag.getBoolean("RecebeuTorresminho");
    hasReceivedBeer = tag.contains("HasReceivedBeer")
        ? tag.getBoolean("HasReceivedBeer")
        : tag.getBoolean("RecebeuCerveja");
    isPacified = tag.contains("IsPacified")
        ? tag.getBoolean("IsPacified")
        : tag.getBoolean("Pacificado");
  }

  private static ItemStack createFroisAxe(RegistryAccess registryAccess) {
    ItemStack axe = new ItemStack(ModItems.FROIS_AXE.get());
    axe.enchant(
        registryAccess.registryOrThrow(Registries.ENCHANTMENT)
            .getHolderOrThrow(Enchantments.SHARPNESS),
        5);
    axe.enchant(
        registryAccess.registryOrThrow(Registries.ENCHANTMENT)
            .getHolderOrThrow(Enchantments.EFFICIENCY),
        5);
    axe.enchant(
        registryAccess.registryOrThrow(Registries.ENCHANTMENT)
            .getHolderOrThrow(Enchantments.FORTUNE),
        5);
    axe.enchant(
        registryAccess.registryOrThrow(Registries.ENCHANTMENT)
            .getHolderOrThrow(Enchantments.UNBREAKING),
        5);
    return axe;
  }
}
