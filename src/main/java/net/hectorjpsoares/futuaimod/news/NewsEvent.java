package net.hectorjpsoares.futuaimod.news;

import net.minecraft.nbt.CompoundTag;

public class NewsEvent {

  private final NewsEventType type;
  private final String entityName;
  private final String playerName;
  private final String levelName;
  private final int x;
  private final int y;
  private final int z;

  public NewsEvent(
      NewsEventType type,
      String entityName,
      String playerName,
      String levelName,
      int x,
      int y,
      int z) {
    this.type = type;
    this.entityName = entityName;
    this.playerName = playerName;
    this.levelName = levelName;
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public void save(CompoundTag tag) {
    tag.putString("type", type.name());
    tag.putString("entityName", entityName);
    tag.putString("playerName", playerName);
    tag.putString("levelName", levelName);
    tag.putInt("x", x);
    tag.putInt("y", y);
    tag.putInt("z", z);
  }

  public static NewsEvent load(CompoundTag tag) {
    NewsEventType type = NewsEventType.valueOf(tag.getString("type"));

    return new NewsEvent(
        type,
        tag.getString("entityName"),
        tag.getString("playerName"),
        tag.getString("levelName"),
        tag.getInt("x"),
        tag.getInt("y"),
        tag.getInt("z"));
  }

  public NewsEventType getType() {
    return type;
  }

  public String getEntityName() {
    return entityName;
  }

  public String getPlayerName() {
    return playerName;
  }

  public String getLevelName() {
    return levelName;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getZ() {
    return z;
  }
}