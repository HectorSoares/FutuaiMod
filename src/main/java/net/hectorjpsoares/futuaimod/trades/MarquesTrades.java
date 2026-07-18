package net.hectorjpsoares.futuaimod.trades;

import net.hectorjpsoares.futuaimod.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.util.RandomSource;

import java.util.List;

public class MarquesTrades {
    private static boolean isRareBlock(Item item) {

        return item == Items.DIAMOND_BLOCK ||
            item == Items.EMERALD_BLOCK ||
            item == Items.NETHERITE_BLOCK ||
            item == Items.BEACON ||
            item == Items.ENCHANTING_TABLE;
    }


    private static boolean isRareItem(Item item) {

        return item == Items.DIAMOND ||
            item == Items.EMERALD ||
            item == Items.NETHERITE_INGOT ||
            item == Items.NETHER_STAR ||
            item == Items.TOTEM_OF_UNDYING ||
            item == Items.ELYTRA ||
            item == Items.DRAGON_EGG ||
            item == Items.ENCHANTED_GOLDEN_APPLE ||
            item == Items.GOLDEN_APPLE ||
            item == Items.END_CRYSTAL ||
            item == Items.SHULKER_BOX;
    }

    private static final List<Item> RANDOM_ITEMS = BuiltInRegistries.ITEM.stream()
            .filter(item -> item != Items.AIR)
            .filter(item -> item != Items.BARRIER)
            .filter(item -> item != Items.COMMAND_BLOCK)
            .filter(item -> item != Items.CHAIN_COMMAND_BLOCK)
            .filter(item -> item != Items.REPEATING_COMMAND_BLOCK)
            .filter(item -> item != Items.STRUCTURE_BLOCK)
            .filter(item -> item != Items.JIGSAW)
            .filter(item -> item != Items.DEBUG_STICK)
            .filter(item -> item != ModItems.PREXECA_MILTON.get())
            .toList();

    private static final List<Item> RARE_ITEMS = RANDOM_ITEMS.stream()
        .filter(item ->
                item instanceof TieredItem ||       // ferramentas
                item instanceof ArmorItem ||        // armaduras
                item instanceof BlockItem && isRareBlock(item) ||
                isRareItem(item)
        )
        .toList();


    public static void addTrades(MerchantOffers offers, RandomSource random) {

        offers.clear();

        offers.add(randomItemTrade(
                RARE_ITEMS,
                new ItemCost(ModItems.PREXECA_MILTON.get(), 32),
                999999,
                50,
                0.2f,
                random
        ));
        offers.add(randomItemTrade(
                RANDOM_ITEMS,
                new ItemCost(ModItems.PREXECA_MILTON.get(), 2),
                999999,
                50,
                0.2f,
                random
        ));
        offers.add(randomItemTrade(
                RANDOM_ITEMS,
                new ItemCost(ModItems.PORTO_FARIA.get(), 4),
                5,
                20,
                0.1f,
                random
        ));
        offers.add(randomItemTrade(
                RANDOM_ITEMS,
                new ItemCost(ModItems.BLACK_PEARL_JAM_DISC.get(), 16),
                1,
                50,
                0.05f,
                random
        ));
    }


    private static MerchantOffer randomItemTrade(
        List<Item> itemPool,
        ItemCost cost,
        int maxUses,
        int xp,
        float multiplier,
        RandomSource random
) {
    Item item = itemPool.get(random.nextInt(itemPool.size()));

    return new MerchantOffer(
            cost,
            new ItemStack(item),
            maxUses,
            xp,
            multiplier
    );
}
}