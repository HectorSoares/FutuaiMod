package net.hectorjpsoares.futuaimod.entity.custom;

import net.hectorjpsoares.futuaimod.trades.JamalTrades;
import net.hectorjpsoares.futuaimod.villager.ModVillagerProfessions;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
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
  protected void updateTrades() {

    this.getOffers().clear();

    JamalTrades.addTrades(
        this.getOffers(),
        this.getVillagerData().getLevel());
  }

  @Override
  public void setVillagerData(VillagerData data) {

    super.setVillagerData(
        data.setProfession(
            ModVillagerProfessions.JORNALISTA.get()));
  }

  @Override
  public Component getDisplayName() {
    return Component.literal("Jamal");
  }
}