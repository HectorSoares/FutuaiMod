package net.hectorjpsoares.futuaimod.entity.custom;

import net.hectorjpsoares.futuaimod.trades.JamalTrades;
import net.hectorjpsoares.futuaimod.villager.ModVillagerProfessions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class JamalEntity extends Villager {
  public JamalEntity(
      EntityType<? extends Villager> entityType,
      Level level) {
    super(entityType, level);

    this.setVillagerData(
        this.getVillagerData()
            .setProfession(
                ModVillagerProfessions.JORNALISTA.get())
            .setLevel(1));
  }

  @Override
  protected void registerGoals() {
    super.registerGoals();

    this.goalSelector.addGoal(
        8,
        new RandomStrollGoal(this, 0.6D));

    this.goalSelector.addGoal(
        9,
        new LookAtPlayerGoal(this, Player.class, 8.0F));
  }

  @Override
  protected void updateTrades() {
    this.getOffers().clear();

    if (!(this.level() instanceof ServerLevel serverLevel))
      return;

    JamalTrades.addTrades(
        this.getOffers(),
        this.getVillagerData().getLevel(),
        serverLevel);
  }

  @Override
  public void setVillagerData(VillagerData data) {
    super.setVillagerData(
        data.setProfession(
            ModVillagerProfessions.JORNALISTA.get()));
  }

  @Override
  public void tick() {
    super.tick();

    if (!this.level().isClientSide()) {
      this.setInvisible(!this.level().isDay());
    }
  }

  @Override
  public Component getDisplayName() {
    return Component.literal("Jamal");
  }
}