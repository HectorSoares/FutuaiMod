package net.hectorjpsoares.futuaimod.item;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FutUaiMod.MOD_ID);

    public static final RegistryObject<Item> CRACKLING_PORK = ITEMS.register("crackling_pork",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
