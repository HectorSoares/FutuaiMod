package net.hectorjpsoares.futuaimod.entity;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.hectorjpsoares.futuaimod.entity.custom.FredEntity;
import net.hectorjpsoares.futuaimod.entity.custom.FroisEntity;
import net.hectorjpsoares.futuaimod.entity.custom.PiteraEntity;
import net.hectorjpsoares.futuaimod.entity.custom.PomboEntity;
import net.hectorjpsoares.futuaimod.entity.custom.YuriEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    // 1. O DeferredRegister DEVE ser a primeira coisa instanciada na classe
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FutUaiMod.MOD_ID);

    // 2. Mantenha o registro da entidade
    public static final RegistryObject<EntityType<PomboEntity>> POMBO_MOB =
            ENTITIES.register("pombo_mob", () -> EntityType.Builder.of(PomboEntity::new, MobCategory.CREATURE)
                    .sized(0.5f, 0.9f)
                    .build("pombo_mob"));

    public static final RegistryObject<EntityType<PiteraEntity>> PITERA_MOB =
            ENTITIES.register("pitera_mob", () -> EntityType.Builder.of(PiteraEntity::new, MobCategory.MONSTER)
                    .sized(0.5f, 0.9f)
                    .build("pitera_mob"));

    public static final RegistryObject<EntityType<YuriEntity>> YURI_MOB =
            ENTITIES.register("yuri_mob", () -> EntityType.Builder.of(YuriEntity::new, MobCategory.MONSTER)
                    .sized(0.9f, 2.9f)
                    .build("yuri_mob"));

    public static final RegistryObject<EntityType<FredEntity>> FRED_MOB =
            ENTITIES.register("fred_mob", () -> EntityType.Builder.of(FredEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 2.9f)
                    .build("fred_mob"));

    public static final RegistryObject<EntityType<FroisEntity>> FROIS_MOB =
            ENTITIES.register("frois_mob", () -> EntityType.Builder.of(FroisEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.95f)
                    .build("frois_mob"));

    // 3. Método de registro que o FutUaiMod chama
    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
