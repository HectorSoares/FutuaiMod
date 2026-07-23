package net.hectorjpsoares.futuaimod.news;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.hectorjpsoares.futuaimod.util.EntityUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FutUaiMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NewsEventHandler {

  @SubscribeEvent
  public static void onLivingDeath(LivingDeathEvent event) {

    if (event.getEntity().level().isClientSide())
      return;

    Entity victim = event.getEntity();
    Entity killer = event.getSource().getEntity();

    if (killer == null)
      return;

    if (!EntityUtils.involvesFutUaiEntity(victim, killer))
      return;

    if (!(victim.level() instanceof ServerLevel level))
      return;

    String victimName = victim.getName().getString();
    String killerName = killer.getName().getString();

    NewsEvent newsEvent = new NewsEvent(
        NewsEventType.MOB_KILLED,
        victimName,
        killerName,
        level.dimension().location().toString(),
        victim.getBlockX(),
        victim.getBlockY(),
        victim.getBlockZ());

    NewsManager manager = NewsManager.get(level);
    manager.addNews(newsEvent);

    FutUaiMod.LOGGER.info(
        "News event registered: {} killed by {} at {}, {}, {}",
        victimName,
        killerName,
        newsEvent.getX(),
        newsEvent.getY(),
        newsEvent.getZ());
  }
}