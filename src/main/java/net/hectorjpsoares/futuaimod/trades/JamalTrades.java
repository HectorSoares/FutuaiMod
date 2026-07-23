package net.hectorjpsoares.futuaimod.trades;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;

public class JamalTrades {

  public static void addTrades(
      MerchantOffers offers,
      int level,
      ServerLevel serverLevel) {

    if (level >= 1) {

      offers.add(
          new MerchantOffer(
              new ItemCost(Items.PAPER, 24),
              Items.EMERALD.getDefaultInstance(),
              16,
              2,
              0.05F));

      offers.add(
          new MerchantOffer(
              new ItemCost(Items.BOOK, 4),
              Items.EMERALD.getDefaultInstance(),
              16,
              2,
              0.05F));
    }

    if (level >= 2) {

      offers.add(
          new MerchantOffer(
              new ItemCost(Items.FEATHER, 16),
              Items.EMERALD.getDefaultInstance(),
              16,
              5,
              0.05F));

      offers.add(
          new MerchantOffer(
              new ItemCost(Items.INK_SAC, 12),
              Items.EMERALD.getDefaultInstance(),
              16,
              5,
              0.05F));
    }

    if (level >= 3) {

      offers.add(
          new MerchantOffer(
              new ItemCost(Items.EMERALD, 8),
              Items.BOOK.getDefaultInstance(),
              16,
              10,
              0.05F));
    }

    if (level >= 4) {

      offers.add(
          new MerchantOffer(
              new ItemCost(Items.EMERALD, 12),
              Items.MAP.getDefaultInstance(),
              16,
              15,
              0.05F));

      offers.add(
          new DailyNewsMerchantOffer(
              serverLevel,
              new ItemCost(Items.EMERALD, 5),
              1,
              20,
              0.05F));
    }
  }
}