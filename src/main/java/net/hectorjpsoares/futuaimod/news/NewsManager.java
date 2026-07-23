package net.hectorjpsoares.futuaimod.news;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class NewsManager extends SavedData {

  private static final String DATA_NAME = "futuaimod_news";
  private static final String NEWS_LIST = "news";
  private static final String SPOTTED_ENTITIES = "spotted_entities";
  private static final String UUID = "uuid";

  private static final int MAX_NEWS = 30;

  private final LinkedList<NewsEvent> news = new LinkedList<>();
  private final Set<UUID> spottedEntities = new HashSet<>();

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

    ListTag spottedList = tag.getList(
        SPOTTED_ENTITIES,
        CompoundTag.TAG_COMPOUND);

    for (int i = 0; i < spottedList.size(); i++) {
      CompoundTag spottedTag = spottedList.getCompound(i);

      if (spottedTag.hasUUID(UUID))
        manager.spottedEntities.add(
            spottedTag.getUUID(UUID));
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

    ListTag spottedList = new ListTag();

    for (UUID entityUUID : spottedEntities) {
      CompoundTag spottedTag = new CompoundTag();
      spottedTag.putUUID(UUID, entityUUID);
      spottedList.add(spottedTag);
    }

    tag.put(
        SPOTTED_ENTITIES,
        spottedList);

    return tag;
  }

  public void addNews(NewsEvent event) {
    news.addLast(event);

    if (news.size() > MAX_NEWS)
      news.removeFirst();

    setDirty();
  }

  public boolean hasBeenSpotted(UUID entityUUID) {
    return spottedEntities.contains(entityUUID);
  }

  public void registerSpottedEntity(UUID entityUUID) {
    spottedEntities.add(entityUUID);
    setDirty();
  }

  public List<NewsEvent> getNews() {
    return List.copyOf(news);
  }

  public List<NewsEvent> getLatestNews(int amount) {
    int fromIndex = Math.max(
        0,
        news.size() - amount);

    return List.copyOf(
        news.subList(
            fromIndex,
            news.size()));
  }

  public void clear() {
    news.clear();
    setDirty();
  }
}