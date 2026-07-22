package net.hectorjpsoares.futuaimod.item;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.hectorjpsoares.futuaimod.entity.ModEntities;
import net.hectorjpsoares.futuaimod.item.custom.ColdBeerItem;
import net.hectorjpsoares.futuaimod.sound.ModSounds;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.UseAnim;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FutUaiMod.MOD_ID);

    public static final RegistryObject<Item> PORTO_FARIA = ITEMS.register("porto_faria",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ADRENALINE_INJECTION = ITEMS.register("adrenaline_injection",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRACKLING_PORK = ITEMS.register("crackling_pork",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)          // Quantidade de barras de fome que recupera
                            .saturationModifier(0.6f)   // Modificador de saturação (0.6f é bom para torresmo)
                            .build())));

    public static final RegistryObject<Item> PREXECA_MILTON = ITEMS.register("prexeca_milton",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(8)          // Quantidade de barras de fome que recupera
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

    public static final RegistryObject<Item> ABADA_HELMET = ITEMS.register("abada_helmet",
            () -> new ArmorItem(ModArmorMaterials.ABADA_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(18))));
    public static final RegistryObject<Item> ABADA_CHESTPLATE = ITEMS.register("abada_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ABADA_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(18))));
    public static final RegistryObject<Item> ABADA_LEGGINGS = ITEMS.register("abada_leggings",
            () -> new ArmorItem(ModArmorMaterials.ABADA_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(18))));
    public static final RegistryObject<Item> ABADA_BOOTS = ITEMS.register("abada_boots",
            () -> new ArmorItem(ModArmorMaterials.ABADA_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(18))));

    // *****
    // EGGS
    // *****

    public static final RegistryObject<Item> POMBO_SPAWN_EGG =
        registerSpawnEgg("pombo_spawn_egg", ModEntities.POMBO_MOB, 0xD9D9D9, 0x3B7A57);

    public static final RegistryObject<Item> PITERA_SPAWN_EGG =
        registerSpawnEgg("pitera_spawn_egg", ModEntities.PITERA_MOB, 0x3B214F, 0xC26BFF);

    public static final RegistryObject<Item> YURI_SPAWN_EGG =
        registerSpawnEgg("yuri_spawn_egg", ModEntities.YURI_MOB, 0x0B3D3E, 0x20E0B2);

    public static final RegistryObject<Item> FRED_SPAWN_EGG =
        registerSpawnEgg("fred_spawn_egg", ModEntities.FRED_MOB, 0x16111F, 0xB04DFF);

    public static final RegistryObject<Item> FROIS_SPAWN_EGG =
        registerSpawnEgg("frois_spawn_egg", ModEntities.FROIS_MOB, 0x6E747C, 0x2F4F6F);

    public static final RegistryObject<Item> HECTOR_SPAWN_EGG =
        registerSpawnEgg("hector_spawn_egg", ModEntities.HECTOR_MOB, 0x2E86DE, 0xF8F9FA);

    public static final RegistryObject<Item> MARQUES_SPAWN_EGG =
        registerSpawnEgg("marques_spawn_egg", ModEntities.MARQUES_MOB, 0x8E6B3A, 0x00C853);

    public static final RegistryObject<Item> ZE_PEDRO_SPAWN_EGG =
        registerSpawnEgg("ze_pedro_spawn_egg", ModEntities.ZE_PEDRO_MOB, 0x7B5E57, 0xFFD54F);

    public static final RegistryObject<Item> JOAO_SPAWN_EGG =
        registerSpawnEgg("joao_spawn_egg", ModEntities.JOAO_MOB, 0xC62828, 0xFFEB3B);

    public static final RegistryObject<Item> JAMAL_SPAWN_EGG =
        registerSpawnEgg("jamal_spawn_egg", ModEntities.JAMAL_MOB, 0x263238, 0x90CAF9);

    public static final RegistryObject<Item> FUTUAI_FOX_SPAWN_EGG =
        registerSpawnEgg("futuai_fox_spawn_egg", ModEntities.FUTUAI_FOX, 0x003366, 0xFFFFFF); 

        


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static <T extends Mob> RegistryObject<Item> registerSpawnEgg(
        String name,
        RegistryObject<EntityType<T>> entity,
        int primaryColor,
        int secondaryColor) {

    return ITEMS.register(name,
            () -> new ForgeSpawnEggItem(entity, primaryColor, secondaryColor, new Item.Properties()));
}
}
