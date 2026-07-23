package net.hectorjpsoares.futuaimod.news;

import net.minecraft.network.chat.Component;
import net.minecraft.server.network.Filterable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewsBookBuilder {

  private static final int MAX_NEWS = 30;
  private static final int MAX_PAGE_LENGTH = 220;

  private NewsBookBuilder() {
  }

  public static List<Filterable<Component>> buildDailyNews(NewsManager manager) {

    List<NewsEvent> news = new ArrayList<>(
        manager.getLatestNews(MAX_NEWS));

    Collections.reverse(news);

    if (news.isEmpty())
      return List.of(
          createPage("JORNAL DIÁRIO\n\nNenhuma notícia registrada ainda."));

    List<Filterable<Component>> pages = new ArrayList<>();
    StringBuilder currentPage = new StringBuilder();

    for (NewsEvent event : news) {
      String formattedNews = NewsFormatter.format(event);

      String newsBlock = formattedNews + "\n\n";

      if (currentPage.length() > 0
          && currentPage.length()
              + newsBlock.length() > MAX_PAGE_LENGTH) {

        pages.add(createPage(currentPage.toString().trim()));
        currentPage.setLength(0);
      }

      currentPage.append(newsBlock);
    }

    if (currentPage.length() > 0)
      pages.add(
          createPage(
              currentPage.toString().trim()));

    return pages;
  }

  private static Filterable<Component> createPage(String text) {
    Component page = Component.literal(text);
    return Filterable.passThrough(page);
  }
}