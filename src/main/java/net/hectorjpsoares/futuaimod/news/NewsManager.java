package net.hectorjpsoares.futuaimod.news;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.LinkedList;
import java.util.List;

public class NewsManager extends SavedData {

  private static final String DATA_NAME = "futuaimod_news";
  private static final String NEWS_LIST = "news";
  private static final int MAX_NEWS = 30;

  private final LinkedList<NewsEvent> news = new LinkedList<>();

  public NewsManager() {
  }

  public static NewsManager get(ServerLevel level) {
    return level.getDataStorage().computeIfAbsent(
        new SavedData.Factory<>(
            NewsManager::new,
            NewsManager::load,
            null),
        DATA_NAME);
  }

  public static NewsManager load(
      CompoundTag tag,
      HolderLookup.Provider registries) {

    NewsManager manager = new NewsManager();

    ListTag newsList = tag.getList(
        NEWS_LIST,
        CompoundTag.TAG_COMPOUND);

    for (int i = 0; i < newsList.size(); i++) {
      CompoundTag newsTag = newsList.getCompound(i);
      NewsEvent event = NewsEvent.load(newsTag);
      manager.news.add(event);
    }

    return manager;
  }

  @Override
  public CompoundTag save(
      CompoundTag tag,
      HolderLookup.Provider registries) {

    ListTag newsList = new ListTag();

    for (NewsEvent event : news) {
      CompoundTag newsTag = new CompoundTag();
      event.save(newsTag);
      newsList.add(newsTag);
    }

    tag.put(NEWS_LIST, newsList);
    return tag;
  }

  public void addNews(NewsEvent event) {
    news.addLast(event);

    if (news.size() > MAX_NEWS)
      news.removeFirst();

    setDirty();
    System.out.println(
        "NewsManager: " +
            news.size() +
            " news stored.");
  }

  public List<NewsEvent> getNews() {
    return List.copyOf(news);
  }

  public void clear() {
    news.clear();
    setDirty();
  }
}