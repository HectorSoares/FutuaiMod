package net.hectorjpsoares.futuaimod.trades;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.WrittenBookContent;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;

import net.minecraft.server.network.Filterable;

import java.util.List;

public class JamalTrades {

  public static void addTrades(
      MerchantOffers offers,
      int level) {

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
          new MerchantOffer(
              new ItemCost(Items.EMERALD, 5),
              createTestNewsBook(),
              1,
              20,
              0.05F));
    }
  }

  private static ItemStack createTestNewsBook() {

    ItemStack book = new ItemStack(Items.WRITTEN_BOOK);

    String titleText = "Notícias do Jamal";

    Component pageText = Component.literal(
        "Primeira notícia registrada por Jamal.\n\n" +
            "O mundo de Nem frio Nem calor " +
            "acaba de receber um novo jornalista.");

    Filterable<String> title = new Filterable<>(
        titleText,
        java.util.Optional.of(titleText));

    List<Filterable<Component>> pages = List.of(
        new Filterable<>(
            pageText,
            java.util.Optional.of(pageText)));

    WrittenBookContent content = new WrittenBookContent(
        title,
        "Jamal",
        0,
        pages,
        true);

    book.set(
        DataComponents.WRITTEN_BOOK_CONTENT,
        content);

    return book;
  }
}