package net.hectorjpsoares.futuaimod.entity.custom;

import net.hectorjpsoares.futuaimod.item.ModItems;
import net.hectorjpsoares.futuaimod.item.TicketSerieBFactory;
import net.hectorjpsoares.futuaimod.block.ModBlocks;
import net.hectorjpsoares.futuaimod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FredEntity extends EnderMan {

  private static final int MIN_PLACE_TIME = 20 * 2;
  private static final int MAX_PLACE_TIME = 20 * 5;

  private int placeCooldown;
  private BlockPos lastPlacedBlock;
  private BlockPos pendingPlacePos;

  public FredEntity(EntityType<? extends EnderMan> entityType, Level level) {
    super(entityType, level);
    this.placeCooldown = randomPlaceCooldown();
  }

  public static AttributeSupplier.Builder createAttributes() {
    return EnderMan.createAttributes();
  }

  @Override
  public void aiStep() {
    super.aiStep();

    if (this.level().isClientSide()) {
      return;
    }

    if (this.getCarriedBlock() == null) {
      this.setCarriedBlock(ModBlocks.FRED_BRICKS.get().defaultBlockState());
    }
  }

  @Override
  public void tick() {
    super.tick();

    if (this.level().isClientSide()) {
      return;
    }

    if (pendingPlacePos != null) {
      double distance = this.distanceToSqr(
          pendingPlacePos.getX() + 0.5,
          pendingPlacePos.getY(),
          pendingPlacePos.getZ() + 0.5);

      if (distance <= 16) {
        placeBlockAt(pendingPlacePos);

        pendingPlacePos = null;
        placeCooldown = randomPlaceCooldown();
        return;
      }

      this.getNavigation().moveTo(
          pendingPlacePos.getX() + 0.5,
          pendingPlacePos.getY() - 1,
          pendingPlacePos.getZ() + 0.5,
          1.0);

      return;
    }

    if (--placeCooldown <= 0) {
      findNextPlacePosition();
    }
  }

  @Override
  protected boolean teleport() {
    return false;
  }

  private void findNextPlacePosition() {
    BlockPos base = lastPlacedBlock;

    for (int i = 0; i < 10; i++) {

      BlockPos target;

      if (base == null) {
        int x = this.getBlockX() + this.random.nextInt(7) - 3;
        int z = this.getBlockZ() + this.random.nextInt(7) - 3;

        target = new BlockPos(
            x,
            this.getBlockY(),
            z);
      } else {
        BlockPos[] directions = {
            base.north(),
            base.south(),
            base.east(),
            base.west()
        };

        target = directions[this.random.nextInt(directions.length)];
      }

      BlockPos placePos = findGroundPosition(target);

      if (placePos != null) {

        pendingPlacePos = placePos;

        this.getNavigation().moveTo(
            placePos.getX() + 0.5,
            placePos.getY() - 1,
            placePos.getZ() + 0.5,
            1.0);

        System.out.println("Fred indo para: " + placePos);

        return;
      }
    }

    System.out.println("Fred não encontrou posição válida");
  }

  private BlockPos findGroundPosition(BlockPos target) {

    BlockPos placePos = target.above();

    for (int y = 0; y < 3; y++) {

      if (!this.level().getBlockState(placePos).isAir()) {
        return null;
      }

      if (!this.level().getBlockState(placePos.below()).isAir()) {
        return placePos;
      }

      placePos = placePos.below();
    }

    return null;
  }

  private void placeBlockAt(BlockPos pos) {

    System.out.println("Tentando colocar bloco em: " + pos);

    if (!this.level().getBlockState(pos).isAir()) {
      System.out.println("Posição ocupada");
      return;
    }

    this.level().setBlock(
        pos,
        ModBlocks.FRED_BRICKS.get().defaultBlockState(),
        3);

    lastPlacedBlock = pos;

    System.out.println("Fred colocou bloco em: " + pos);
  }

  private int randomPlaceCooldown() {
    return MIN_PLACE_TIME
        + this.random.nextInt(
            MAX_PLACE_TIME - MIN_PLACE_TIME + 1);
  }

  @Override
  protected void dropCustomDeathLoot(
      ServerLevel level,
      DamageSource damageSource,
      boolean recentlyHit) {

    super.dropCustomDeathLoot(
        level,
        damageSource,
        recentlyHit);

    this.spawnAtLocation(
        TicketSerieBFactory.createTicket());
  }

  @Override
  protected SoundEvent getDeathSound() {
    return ModSounds.FRED_DEATH_SOUND.get();
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource damageSource) {
    return ModSounds.FRED_HURT_SOUND.get();
  }
}
