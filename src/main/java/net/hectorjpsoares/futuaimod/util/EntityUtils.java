package net.hectorjpsoares.futuaimod.util;

import net.hectorjpsoares.futuaimod.entity.ModEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.Set;

public class EntityUtils {

  private static final Set<EntityType<?>> FUTUAI_ENTITIES = Set.of(
      ModEntities.FRED_MOB.get(),
      ModEntities.FROIS_MOB.get(),
      ModEntities.FUTUAI_FOX.get(),
      ModEntities.HECTOR_MOB.get(),
      ModEntities.JAMAL_MOB.get(),
      ModEntities.JOAO_MOB.get(),
      ModEntities.MARQUES_MOB.get(),
      ModEntities.PITERA_MOB.get(),
      ModEntities.POMBO_MOB.get(),
      ModEntities.YURI_MOB.get(),
      ModEntities.ZE_PEDRO_MOB.get());

  public static boolean isFutUaiEntity(Entity entity) {
    return entity != null
        && FUTUAI_ENTITIES.contains(entity.getType());
  }

  public static boolean involvesFutUaiEntity(Entity first, Entity second) {
    return isFutUaiEntity(first)
        || isFutUaiEntity(second);
  }
}