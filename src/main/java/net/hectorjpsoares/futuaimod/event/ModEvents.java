package net.hectorjpsoares.futuaimod.event;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.hectorjpsoares.futuaimod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = FutUaiMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    // private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    // // Done with the help of https://github.com/CoFH/CoFHCore/blob/1.19.x/src/main/java/cofh/core/event/AreaEffectEvents.java
    // // Don't be a jerk License
    // @SubscribeEvent
    // public static void onHammerUsage(BlockEvent.BreakEvent event) {
    //     Player player = event.getPlayer();
    //     ItemStack mainHandItem = player.getMainHandItem();

    //     if(mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
    //         BlockPos initialBlockPos = event.getPos();
    //         if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
    //             return;
    //         }

    //         for(BlockPos pos : HammerItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
    //             if(pos == initialBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
    //                 continue;
    //             }

    //             HARVESTED_BLOCKS.add(pos);
    //             serverPlayer.gameMode.destroyBlock(pos);
    //             HARVESTED_BLOCKS.remove(pos);
    //         }
    //     }
    // }

    // @SubscribeEvent
    // public static void onLivingDamage(LivingDamageEvent event) {
    //     if(event.getEntity() instanceof Sheep sheep && event.getSource().getDirectEntity() instanceof Player player) {
    //         if(player.getMainHandItem().getItem() == Items.END_ROD) {
    //             player.sendSystemMessage(Component.literal(player.getName().getString() + " JUST HIT A SHEEP WITH AN END ROD! YOU SICK FRICK!"));
    //             sheep.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 5));
    //             player.getMainHandItem().shrink(1);
    //         }
    //     }
    // }

    // @SubscribeEvent
    // public static void onBrewingRecipeRegister(BrewingRecipeRegisterEvent event) {
    //     PotionBrewing.Builder builder = event.getBuilder();

    //     builder.addMix(Potions.AWKWARD, Items.SLIME_BALL, ModPotions.SLIMEY_POTION.getHolder().get());
    // }


    // private static VillagerTrades.ItemListing randomItemTrade(ItemCost cost, int maxUses, int xp, float priceMultiplier) {
    //     return (trader, random) -> {

    //         List<Item> items = BuiltInRegistries.ITEM.stream()
    //                 .filter(item -> item != Items.AIR)
    //                 .filter(item -> item != Items.BARRIER)
    //                 .filter(item -> item != Items.COMMAND_BLOCK)
    //                 .filter(item -> item != Items.CHAIN_COMMAND_BLOCK)
    //                 .filter(item -> item != Items.REPEATING_COMMAND_BLOCK)
    //                 .filter(item -> item != Items.STRUCTURE_BLOCK)
    //                 .filter(item -> item != Items.JIGSAW)
    //                 .filter(item -> item != Items.DEBUG_STICK)
    //                 .filter(item -> item != ModItems.PREXECA_MILTON.get())
    //                 .toList();

    //         Item randomItem = items.get(random.nextInt(items.size()));

    //         return new MerchantOffer(
    //                 cost,
    //                 new ItemStack(randomItem),
    //                 maxUses,
    //                 xp,
    //                 priceMultiplier
    //         );
    //     };
    // }

    // @SubscribeEvent
    // public static void addWanderingTrades(WandererTradesEvent event) {

    //     List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
    //     List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

    //     genericTrades.add(randomItemTrade(
    //             new ItemCost(ModItems.PREXECA_MILTON.get(), 64),
    //             999999,
    //             50,
    //             0.2f
    //     ));

    //     genericTrades.add(randomItemTrade(
    //             new ItemCost(ModItems.PORTO_FARIA.get(), 32),
    //             5,
    //             20,
    //             0.1f
    //     ));

    //     rareTrades.add(randomItemTrade(
    //             new ItemCost(ModItems.BLACK_PEARL_JAM_DISC.get(), 64),
    //             1,
    //             50,
    //             0.05f
    //     ));
    // }
}