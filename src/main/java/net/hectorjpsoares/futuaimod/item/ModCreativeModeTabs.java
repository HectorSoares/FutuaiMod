package net.hectorjpsoares.futuaimod.item;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FutUaiMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> FUTUAI_TAB = CREATIVE_MODE_TABS.register("futuai_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CRACKLING_PORK.get()))
                    .title(Component.translatable("creativetab.futuaimod.futuai_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.CRACKLING_PORK.get());
                        output.accept(ModItems.PORK_SKIN.get());
                        output.accept(ModItems.PREXECA_MILTON.get());
                        output.accept(ModItems.COLD_BEER.get());
                        output.accept(ModItems.BLACK_PEARL_JAM_DISC.get());
                        output.accept(ModItems.PORTO_FARIA.get());

                        output.accept(ModItems.POMBO_SPAWN_EGG.get());
                        output.accept(ModItems.PITERA_SPAWN_EGG.get());
                        output.accept(ModItems.YURI_SPAWN_EGG.get());
                        output.accept(ModItems.FRED_SPAWN_EGG.get());
                        output.accept(ModItems.FROIS_SPAWN_EGG.get());
                        output.accept(ModItems.HECTOR_SPAWN_EGG.get());
                        output.accept(ModItems.MARQUES_SPAWN_EGG.get());
                        output.accept(ModItems.ZE_PEDRO_SPAWN_EGG.get());
                        output.accept(ModItems.JOAO_SPAWN_EGG.get());
                        output.accept(ModItems.JAMAL_SPAWN_EGG.get());
                        output.accept(ModItems.FUTUAI_FOX_SPAWN_EGG.get());

                        //output.accept(ModItems.ABADA_HELMET.get());
                        output.accept(ModItems.ABADA_CHESTPLATE.get());
                        // output.accept(ModItems.ABADA_LEGGINGS.get());
                        // output.accept(ModItems.ABADA_BOOTS.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}