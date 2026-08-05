package net.hectorjpsoares.futuaimod.entity.custom;

import net.hectorjpsoares.futuaimod.sound.ModSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.EnumSet;

public class PomboEntity extends Parrot {
  private boolean droppedForFox = false;

  public PomboEntity(EntityType<? extends Parrot> entityType, Level level) {
    super(entityType, level);
  }

  public static AttributeSupplier.Builder createAttributes() {
    return Parrot.createAttributes()
        .add(Attributes.MAX_HEALTH, 6.0)
        .add(Attributes.MOVEMENT_SPEED, 0.25)
        .add(Attributes.ATTACK_DAMAGE, 1.0);
  }

  @Override
  public SoundEvent getAmbientSound() {
    return ModSounds.POMBO_AMBIENT_SOUND.get();
  }

  @Override
  protected void registerGoals() {
    super.registerGoals();
    this.goalSelector.addGoal(1, new PomboAttackGoal(this));
  }

  @Override
  public void tick() {
    super.tick();

    if (!this.level().isClientSide()) {
      checkFoxInteraction();
    }
  }

  private void checkFoxInteraction() {
    if (droppedForFox) {
      return;
    }

    var foxes = this.level().getEntitiesOfClass(
        FutUaiFoxEntity.class,
        this.getBoundingBox().inflate(5));

    boolean hasAdultFox = foxes.stream().anyMatch(fox -> !fox.isBaby());
    boolean hasBabyFox = foxes.stream().anyMatch(fox -> fox.isBaby());

    if (hasAdultFox && hasBabyFox) {
      this.spawnAtLocation(Items.PINK_SHULKER_BOX);
      droppedForFox = true;
    }
  }

  private static class PomboAttackGoal extends Goal {
    private final PomboEntity pombo;
    private Player target;
    private int cooldown = 0;

    private static final double SEARCH_RADIUS = 8.0D;
    private static final double ATTACK_DISTANCE = 1.5D;
    private static final int ATTACK_COOLDOWN = 20 * 10;

    public PomboAttackGoal(PomboEntity pombo) {
      this.pombo = pombo;
      this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
      if (cooldown > 0) {
        cooldown--;
        return false;
      }

      Player nearestPlayer = pombo.level().getNearestPlayer(
          pombo.getX(),
          pombo.getY(),
          pombo.getZ(),
          SEARCH_RADIUS,
          player -> player.isAlive());

      if (nearestPlayer == null) {
        return false;
      }

      this.target = nearestPlayer;
      return true;
    }

    @Override
    public boolean canContinueToUse() {
      return target != null
          && target.isAlive()
          && pombo.distanceToSqr(target) <= SEARCH_RADIUS * SEARCH_RADIUS;
    }

    @Override
    public void start() {
      if (pombo.getNavigation() instanceof FlyingPathNavigation flyingNavigation) {
        flyingNavigation.moveTo(target, 1.5D);
      }
    }

    @Override
    public void tick() {
      if (target == null) {
        return;
      }

      pombo.getLookControl().setLookAt(target);

      if (pombo.getNavigation() instanceof FlyingPathNavigation flyingNavigation) {
        flyingNavigation.moveTo(target, 1.5D);
      }

      if (pombo.distanceToSqr(target) <= ATTACK_DISTANCE * ATTACK_DISTANCE) {
        target.hurt(pombo.damageSources().mobAttack(pombo), 1.0F);
        cooldown = ATTACK_COOLDOWN;
        stop();
      }
    }

    @Override
    public void stop() {
      pombo.getNavigation().stop();
      target = null;
    }
  }
}