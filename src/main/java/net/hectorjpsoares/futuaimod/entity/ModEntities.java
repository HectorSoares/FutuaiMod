package net.hectorjpsoares.futuaimod.entity;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.hectorjpsoares.futuaimod.entity.custom.FredEntity;
import net.hectorjpsoares.futuaimod.entity.custom.FroisEntity;
import net.hectorjpsoares.futuaimod.entity.custom.HectorEntity;
import net.hectorjpsoares.futuaimod.entity.custom.JamalEntity;
import net.hectorjpsoares.futuaimod.entity.custom.JoaoEntity;
import net.hectorjpsoares.futuaimod.entity.custom.MarquesEntity;
import net.hectorjpsoares.futuaimod.entity.custom.PiteraEntity;
import net.hectorjpsoares.futuaimod.entity.custom.PomboEntity;
import net.hectorjpsoares.futuaimod.entity.custom.YuriEntity;
import net.hectorjpsoares.futuaimod.entity.custom.ZePedroEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FutUaiMod.MOD_ID);

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

    public static final RegistryObject<EntityType<HectorEntity>> HECTOR_MOB =
            ENTITIES.register("hector_mob", () -> EntityType.Builder.of(HectorEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.95f)
                    .build("hector_mob"));

    public static final RegistryObject<EntityType<MarquesEntity>> MARQUES_MOB =
            ENTITIES.register("marques_mob", () -> EntityType.Builder.of(MarquesEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.95f)
                    .build("marques_mob"));

    public static final RegistryObject<EntityType<ZePedroEntity>> ZE_PEDRO_MOB =
            ENTITIES.register("ze_pedro_mob", () -> EntityType.Builder.of(ZePedroEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.95f)
                    .build("ze_pedro_mob"));

    public static final RegistryObject<EntityType<JoaoEntity>> JOAO_MOB =
            ENTITIES.register("joao_mob", () -> EntityType.Builder.of(JoaoEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.95f)
                    .build("joao_mob"));

    public static final RegistryObject<EntityType<JamalEntity>> JAMAL_MOB =
            ENTITIES.register("jamal_mob", () -> EntityType.Builder.of(JamalEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.95f)
                    .build("jamal_mob"));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}