package net.hectorjpsoares.futuaimod.trades;

import java.util.List;

import net.hectorjpsoares.futuaimod.news.NewsBookBuilder;
import net.hectorjpsoares.futuaimod.news.NewsManager;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.network.Filterable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.WrittenBookContent;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

public class DailyNewsMerchantOffer extends MerchantOffer {

  private static final String TITLE = "Notícias do Jamal";
  private static final String AUTHOR = "Jamal";

  private final ServerLevel level;

  public DailyNewsMerchantOffer(
      ServerLevel level,
      ItemCost cost,
      int maxUses,
      int xp,
      float priceMultiplier) {

    super(
        cost,
        Items.WRITTEN_BOOK.getDefaultInstance(),
        maxUses,
        xp,
        priceMultiplier);

    this.level = level;
  }

  @Override
  public ItemStack getResult() {
    return createDailyNewsBook();
  }

  @Override
  public ItemStack assemble() {
    return createDailyNewsBook();
  }

  private ItemStack createDailyNewsBook() {
    ItemStack book = new ItemStack(Items.WRITTEN_BOOK);

    NewsManager manager = NewsManager.get(level);

    List<Filterable<Component>> pages = NewsBookBuilder.buildDailyNews(manager);

    Filterable<String> title = Filterable.passThrough(TITLE);

    WrittenBookContent content = new WrittenBookContent(
        title,
        AUTHOR,
        0,
        pages,
        true);

    book.set(
        DataComponents.WRITTEN_BOOK_CONTENT,
        content);

    return book;
  }
}