package net.hectorjpsoares.futuaimod;

import com.mojang.logging.LogUtils;
import net.hectorjpsoares.futuaimod.entity.ModEntities;
import net.hectorjpsoares.futuaimod.entity.client.FredRenderer;
import net.hectorjpsoares.futuaimod.entity.client.FroisRenderer;
import net.hectorjpsoares.futuaimod.entity.client.HectorRenderer;
import net.hectorjpsoares.futuaimod.entity.client.JamalRenderer;
import net.hectorjpsoares.futuaimod.entity.client.JoaoRenderer;
import net.hectorjpsoares.futuaimod.entity.client.MarquesRenderer;
import net.hectorjpsoares.futuaimod.entity.client.PiteraRenderer;
import net.hectorjpsoares.futuaimod.entity.client.PomboRenderer;
import net.hectorjpsoares.futuaimod.entity.client.YuriRenderer;
import net.hectorjpsoares.futuaimod.entity.client.ZePedroRenderer;
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
import net.hectorjpsoares.futuaimod.item.ModCreativeModeTabs;
import net.hectorjpsoares.futuaimod.item.ModItems;
import net.hectorjpsoares.futuaimod.sound.ModSounds;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FutUaiMod.MOD_ID)
public class FutUaiMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "futuaimod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public FutUaiMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        ModEntities.register(modEventBus);

        ModItems.register(modEventBus);
        ModSounds.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.CRACKLING_PORK);
            event.accept(ModItems.PORK_SKIN);
            event.accept(ModItems.COLD_BEER);
            event.accept(ModItems.BLACK_PEARL_JAM_DISC);
            event.accept(ModItems.PORTO_FARIA);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event)
        {
            // O uso do get() aqui pode falhar se a entidade não estiver registrada.
            // O Forge garante que, neste evento, as entidades já foram processadas.
            event.registerEntityRenderer(ModEntities.POMBO_MOB.get(), PomboRenderer::new);
            event.registerEntityRenderer(ModEntities.PITERA_MOB.get(), PiteraRenderer::new);
            event.registerEntityRenderer(ModEntities.YURI_MOB.get(), YuriRenderer::new);
            event.registerEntityRenderer(ModEntities.FRED_MOB.get(), FredRenderer::new);
            event.registerEntityRenderer(ModEntities.FROIS_MOB.get(), FroisRenderer::new);
            event.registerEntityRenderer(ModEntities.HECTOR_MOB.get(), HectorRenderer::new);
            event.registerEntityRenderer(ModEntities.MARQUES_MOB.get(), MarquesRenderer::new);
            event.registerEntityRenderer(ModEntities.ZE_PEDRO_MOB.get(), ZePedroRenderer::new);
            event.registerEntityRenderer(ModEntities.JOAO_MOB.get(), JoaoRenderer::new);
            event.registerEntityRenderer(ModEntities.JAMAL_MOB.get(), JamalRenderer::new);
        }
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void registerAttributes(EntityAttributeCreationEvent event) {
            event.put(ModEntities.POMBO_MOB.get(), PomboEntity.createAttributes().build());
            event.put(ModEntities.PITERA_MOB.get(), PiteraEntity.createAttributes().build());
            event.put(ModEntities.YURI_MOB.get(), YuriEntity.createAttributes().build());
            event.put(ModEntities.FRED_MOB.get(), FredEntity.createAttributes().build());
            event.put(ModEntities.FROIS_MOB.get(), FroisEntity.createAttributes().build());
            event.put(ModEntities.HECTOR_MOB.get(), HectorEntity.createAttributes().build());
            event.put(ModEntities.MARQUES_MOB.get(), MarquesEntity.createAttributes().build());
            event.put(ModEntities.ZE_PEDRO_MOB.get(), ZePedroEntity.createAttributes().build());
            event.put(ModEntities.JOAO_MOB.get(), JoaoEntity.createAttributes().build());
            event.put(ModEntities.JAMAL_MOB.get(), JamalEntity.createAttributes().build());
        }

        @SubscribeEvent
        public static void registerSpawnPlacements(net.minecraftforge.event.entity.SpawnPlacementRegisterEvent event) {
            event.register(
                    ModEntities.POMBO_MOB.get(),
                    SpawnPlacementTypes.ON_GROUND, // Nasce no chão (não no ar)
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, // Pega o bloco mais alto que não seja folha
                    Animal::checkAnimalSpawnRules, // Usa a regra padrão de animais (luz do dia e bloco de grama)
                    net.minecraftforge.event.entity.SpawnPlacementRegisterEvent.Operation.REPLACE
            );
        }
    }
}
