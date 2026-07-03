package net.hectorjpsoares.futuaimod.item;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.hectorjpsoares.futuaimod.entity.ModEntities;
import net.hectorjpsoares.futuaimod.item.custom.ColdBeerItem;
import net.hectorjpsoares.futuaimod.sound.ModSounds;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.UseAnim;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FutUaiMod.MOD_ID);

    public static final RegistryObject<Item> PORTO_FARIA = ITEMS.register("porto_faria",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRACKLING_PORK = ITEMS.register("crackling_pork",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(6)          // Quantidade de barras de fome que recupera
                            .saturationModifier(0.6f)   // Modificador de saturação (0.6f é bom para torresmo)
                            .build())));

    public static final RegistryObject<Item> PORK_SKIN = ITEMS.register("pork_skin",
            () -> new Item(new Item.Properties()
                .food(new FoodProperties.Builder()
                    .nutrition(1)          // Quantidade de barras de fome que recupera
                    .saturationModifier(0.6f)   // Modificador de saturação (0.6f é bom para torresmo)
                    .build())));

    public static final RegistryObject<Item> COLD_BEER = ITEMS.register("cold_beer",
            () -> new ColdBeerItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationModifier(0.3f)
                            .build())
                    .stacksTo(1)
                    .craftRemainder(Items.GLASS_BOTTLE)));

    public static final RegistryObject<Item> BLACK_PEARL_JAM_DISC = ITEMS.register("black_pearl_jam_disc",
            () -> new Item( new Item.Properties().jukeboxPlayable(ModSounds.BLACK_PEARL_JAM_KEY).stacksTo(1)));

    // *****
    // EGGS
    // *****

    public static final RegistryObject<Item> POMBO_SPAWN_EGG = ITEMS.register("pombo_spawn_egg",
            () -> new net.minecraftforge.common.ForgeSpawnEggItem(ModEntities.POMBO_MOB, 0xD9D9D9, 0x3B7A57, new Item.Properties()));

    public static final RegistryObject<Item> PITERA_SPAWN_EGG = ITEMS.register("pitera_spawn_egg",
            () -> new net.minecraftforge.common.ForgeSpawnEggItem(ModEntities.PITERA_MOB, 0x3B214F, 0xC26BFF, new Item.Properties()));

    public static final RegistryObject<Item> YURI_SPAWN_EGG = ITEMS.register("yuri_spawn_egg",
            () -> new net.minecraftforge.common.ForgeSpawnEggItem(ModEntities.YURI_MOB, 0x0B3D3E, 0x20E0B2, new Item.Properties()));

    public static final RegistryObject<Item> FRED_SPAWN_EGG = ITEMS.register("fred_spawn_egg",
            () -> new net.minecraftforge.common.ForgeSpawnEggItem(ModEntities.FRED_MOB, 0x16111F, 0xB04DFF, new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
