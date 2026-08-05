package net.hectorjpsoares.futuaimod.entity.custom;

import net.hectorjpsoares.futuaimod.item.ModItems;
import net.hectorjpsoares.futuaimod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumSet;

public class HectorEntity extends Evoker {

  private static final int MUSIC_RADIUS = 6;
  private static final int CREATION_COOLDOWN = 20 * 15;
  private static final int CASTING_TIME = 80;
  private static final int LIGHTNING_DELAY = 20 * 3;
  private int creationCooldown = 0;
  /*
   * -1 = ainda não iniciou o timer
   * >= 0 = timer contando
   * -2 = raio já foi criado nessa entrada no Overworld
   */
  private int lightningTimer = -1;
  private boolean wasInOverworld = false;

  public HectorEntity(
      EntityType<? extends Evoker> entityType,
      Level level) {
    super(entityType, level);
  }

  public static AttributeSupplier.Builder createAttributes() {
    return Evoker.createAttributes()
        .add(Attributes.MAX_HEALTH, 6.0D)
        .add(Attributes.MOVEMENT_SPEED, 0.25D);
  }

  @Override
  public SpawnGroupData finalizeSpawn(
      ServerLevelAccessor level,
      net.minecraft.world.DifficultyInstance difficulty,
      MobSpawnType spawnType,
      SpawnGroupData spawnGroupData) {

    SpawnGroupData data = super.finalizeSpawn(
        level,
        difficulty,
        spawnType,
        spawnGroupData);

    this.playSound(
        ModSounds.HECTOR_SPAWN_SOUND.get(),
        1.0F,
        1.0F);

    return data;
  }

  @Override
  protected void registerGoals() {
    super.registerGoals();

    // Remove as magias originais do Evoker
    this.goalSelector.getAvailableGoals().removeIf(
        goal -> goal.getGoal() instanceof SpellcasterIllager.SpellcasterUseSpellGoal);

    /*
     * Hector segue jogadores que estejam segurando Black
     * ou o Maço de Porto Faria.
     */
    this.goalSelector.addGoal(
        3,
        new TemptGoal(
            this,
            1.0D,
            Ingredient.of(ModItems.BLACK_PEARL_JAM_DISC.get()),
            false));

    this.goalSelector.addGoal(
        3,
        new TemptGoal(
            this,
            1.0D,
            Ingredient.of(ModItems.PORTO_FARIA.get()),
            false));

    // Comportamento criador do Hector
    this.goalSelector.addGoal(
        2,
        new HectorCreationGoal(this));
  }

  @Override
  public void tick() {
    super.tick();

    if (this.level().isClientSide())
      return;

    /*
     * Detecta entrada no Overworld.
     *
     * Isso funciona tanto para:
     * - Hector spawnando diretamente no Overworld
     * - Hector entrando no Overworld por portal
     */
    boolean inOverworld = this.level().dimension().equals(Level.OVERWORLD);

    if (inOverworld && !wasInOverworld) {
      lightningTimer = LIGHTNING_DELAY;
    }

    /*
     * Se saiu do Overworld, prepara o Hector para
     * iniciar novamente o processo quando voltar.
     */
    if (!inOverworld && wasInOverworld) {
      lightningTimer = -1;
    }

    wasInOverworld = inOverworld;

    /*
     * Contador do raio.
     */
    if (lightningTimer > 0) {
      lightningTimer--;

      if (lightningTimer == 0) {
        summonLightning();

        // Impede que o raio seja criado novamente
        lightningTimer = -2;
      }
    }

    /*
     * Cooldown da criação.
     */
    if (creationCooldown > 0) {
      creationCooldown--;
    }
  }

  private void summonLightning() {

    if (!(this.level() instanceof ServerLevel serverLevel))
      return;

    LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(serverLevel);

    if (lightning == null)
      return;

    lightning.moveTo(
        this.getX(),
        this.getY(),
        this.getZ());

    serverLevel.addFreshEntity(lightning);
  }

  private boolean isNearBlackJukebox() {

    // O comportamento criador só funciona no Overworld
    if (!this.level().dimension().equals(Level.OVERWORLD))
      return false;

    BlockPos center = this.blockPosition();

    for (BlockPos pos : BlockPos.betweenClosed(
        center.offset(
            -MUSIC_RADIUS,
            -MUSIC_RADIUS,
            -MUSIC_RADIUS),
        center.offset(
            MUSIC_RADIUS,
            MUSIC_RADIUS,
            MUSIC_RADIUS))) {

      BlockState state = this.level().getBlockState(pos);

      if (!state.is(Blocks.JUKEBOX))
        continue;

      if (!state.getValue(JukeboxBlock.HAS_RECORD))
        continue;

      if (this.level().getBlockEntity(pos) instanceof JukeboxBlockEntity jukebox) {
        if (jukebox.getTheItem().is(ModItems.BLACK_PEARL_JAM_DISC.get()))
          return true;
      }
    }
    return false;
  }

  private static class HectorCreationGoal extends Goal {
    private final HectorEntity hector;
    private int spellTicks;

    public HectorCreationGoal(HectorEntity hector) {
      this.hector = hector;

      this.setFlags(EnumSet.of(
          Goal.Flag.MOVE,
          Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
      if (!hector.level().dimension().equals(Level.OVERWORLD))
        return false;

      if (hector.creationCooldown > 0)
        return false;

      if (hector.isCastingSpell())
        return false;

      return hector.isNearBlackJukebox();
    }

    @Override
    public boolean canContinueToUse() {
      return spellTicks > 0;
    }

    @Override
    public void start() {
      this.spellTicks = CASTING_TIME;
      hector.setIsCastingSpell(SpellcasterIllager.IllagerSpell.WOLOLO);
    }

    @Override
    public void tick() {
      spellTicks--;

      if (spellTicks <= 0) {
        hector.setIsCastingSpell(SpellcasterIllager.IllagerSpell.NONE);
        hector.createLife();
        hector.creationCooldown = CREATION_COOLDOWN;
      }
    }

    @Override
    public void stop() {
      spellTicks = 0;
      hector.setIsCastingSpell(SpellcasterIllager.IllagerSpell.NONE);
    }
  }

  private void createLife() {

    if (!(this.level() instanceof ServerLevel serverLevel))
      return;

    createFlowers(serverLevel);

    // 35% de chance de criar um animal
    if (this.random.nextFloat() < 0.35F) {
      spawnCuteAnimal(serverLevel);
      if (this.random.nextFloat() < 0.20F) {
        spawnCuteAnimal(serverLevel);
      }
    }
  }

  private void createFlowers(ServerLevel level) {

    RandomSource random = this.random;
    int amount = 3 + random.nextInt(4);

    for (int i = 0; i < amount; i++) {
      BlockPos pos = findSpawnPosition(level, MUSIC_RADIUS);

      if (pos == null)
        continue;

      BlockState flower = getRandomFlower(random);

      if (level.getBlockState(pos).isAir() && flower.canSurvive(level, pos)) {
        level.setBlock(pos, flower, 3);
      }
    }
  }

  private BlockState getRandomFlower(RandomSource random) {
    return switch (random.nextInt(6)) {
      case 0 -> Blocks.DANDELION.defaultBlockState();
      case 1 -> Blocks.POPPY.defaultBlockState();
      case 2 -> Blocks.BLUE_ORCHID.defaultBlockState();
      case 3 -> Blocks.ALLIUM.defaultBlockState();
      case 4 -> Blocks.AZURE_BLUET.defaultBlockState();
      default -> Blocks.OXEYE_DAISY.defaultBlockState();
    };
  }

  private void spawnCuteAnimal(ServerLevel level) {

    EntityType<? extends Mob> type = getRandomCuteAnimal();

    Mob animal = type.create(level);

    if (animal == null)
      return;

    BlockPos spawnPos = findSpawnPosition(level, 5);

    if (spawnPos == null)
      return;

    animal.moveTo(
        spawnPos.getX() + 0.5D,
        spawnPos.getY(),
        spawnPos.getZ() + 0.5D,
        this.random.nextFloat() * 360.0F,
        0.0F);

    level.addFreshEntity(animal);
  }

  private EntityType<? extends Mob> getRandomCuteAnimal() {
    return switch (this.random.nextInt(8)) {
      case 0 -> EntityType.PANDA;
      case 1 -> EntityType.RABBIT;
      case 2 -> EntityType.CAT;
      case 3 -> EntityType.CHICKEN;
      case 4 -> EntityType.SHEEP;
      case 5 -> EntityType.FROG;
      case 6 -> EntityType.TURTLE;
      default -> EntityType.BEE;
    };
  }

  private BlockPos findSpawnPosition(
      ServerLevel level,
      int radius) {

    RandomSource random = this.random;

    for (int attempt = 0; attempt < 10; attempt++) {

      int x = this.getBlockX()
          + random.nextInt(radius * 2 + 1)
          - radius;

      int z = this.getBlockZ()
          + random.nextInt(radius * 2 + 1)
          - radius;

      int y = this.getBlockY();

      /*
       * Procura o chão descendo a partir
       * da altura atual do Hector.
       */
      while (y > level.getMinBuildHeight()) {

        BlockPos groundPos = new BlockPos(x, y, z);

        BlockState groundState = level.getBlockState(groundPos);

        if (!groundState.isAir()
            && groundState.isCollisionShapeFullBlock(
                level,
                groundPos)) {

          BlockPos spawnPos = groundPos.above();

          if (spawnPos.distSqr(
              this.blockPosition()) <= MUSIC_RADIUS * MUSIC_RADIUS

              && level.getBlockState(
                  spawnPos).isAir()) {

            return spawnPos;
          }

          break;
        }

        y--;
      }
    }

    return null;
  }

  @Override
  public boolean hurt(DamageSource source, float amount) {
    if (source.getEntity() instanceof LightningBolt
        || source.getDirectEntity() instanceof LightningBolt) {
      return false;
    }

    return false;
  }
}