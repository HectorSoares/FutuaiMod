package net.hectorjpsoares.futuaimod.news;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.hectorjpsoares.futuaimod.util.EntityUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = FutUaiMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NewsEventHandler {

  private static final double SPOTTING_DISTANCE = 32.0D;

  @SubscribeEvent
  public static void onLivingDeath(LivingDeathEvent event) {
    if (event.getEntity().level().isClientSide())
      return;

    Entity victim = event.getEntity();
    Entity killer = event.getSource().getEntity();

    if (killer == null)
      return;

    boolean victimIsPlayer = victim instanceof Player;
    boolean killerIsPlayer = killer instanceof Player;

    boolean victimIsFutUai = EntityUtils.isFutUaiEntity(victim);
    boolean killerIsFutUai = EntityUtils.isFutUaiEntity(killer);

    boolean validEvent = (victimIsPlayer && killerIsFutUai)
        || (victimIsFutUai && killerIsPlayer);

    if (!validEvent)
      return;

    if (!(victim.level() instanceof ServerLevel level))
      return;

    String victimName = victim.getName().getString();
    String killerName = killer.getName().getString();

    NewsEvent newsEvent = new NewsEvent(
        NewsEventType.MOB_KILLED,
        victim.getUUID(),
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

  @SubscribeEvent
  public static void onPlayerTick(PlayerTickEvent.Post event) {

    Player player = event.player;

    if (player.level().isClientSide())
      return;

    if (!(player.level() instanceof ServerLevel level))
      return;

    NewsManager manager = NewsManager.get(level);

    List<Entity> entities = level.getEntities(
        player,
        player.getBoundingBox()
            .inflate(SPOTTING_DISTANCE),
        EntityUtils::isFutUaiEntity);

    for (Entity entity : entities) {

      if (manager.hasBeenSpotted(
          entity.getUUID()))
        continue;

      manager.registerSpottedEntity(
          entity.getUUID());

      NewsEvent newsEvent = new NewsEvent(
          NewsEventType.ENTITY_SPOTTED,
          entity.getUUID(),
          entity.getName().getString(),
          player.getName().getString(),
          level.dimension()
              .location()
              .toString(),
          entity.getBlockX(),
          entity.getBlockY(),
          entity.getBlockZ());

      manager.addNews(newsEvent);

      FutUaiMod.LOGGER.info(
          "News event registered: {} spotted by {} at {}, {}, {}",
          entity.getName().getString(),
          player.getName().getString(),
          entity.getBlockX(),
          entity.getBlockY(),
          entity.getBlockZ());
    }
  }
}