package net.hectorjpsoares.futuaimod.news;

import net.minecraft.nbt.CompoundTag;

import java.util.UUID;

public class NewsEvent {

  private static final String TYPE = "type";
  private static final String ENTITY_UUID = "entity_uuid";
  private static final String ENTITY_NAME = "entity_name";
  private static final String PLAYER_NAME = "player_name";
  private static final String LEVEL_NAME = "level_name";
  private static final String X = "x";
  private static final String Y = "y";
  private static final String Z = "z";

  private final NewsEventType type;
  private final UUID entityUUID;
  private final String entityName;
  private final String playerName;
  private final String levelName;
  private final int x;
  private final int y;
  private final int z;

  public NewsEvent(
      NewsEventType type,
      UUID entityUUID,
      String entityName,
      String playerName,
      String levelName,
      int x,
      int y,
      int z) {

    this.type = type;
    this.entityUUID = entityUUID;
    this.entityName = entityName;
    this.playerName = playerName;
    this.levelName = levelName;
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public NewsEventType getType() {
    return type;
  }

  public UUID getEntityUUID() {
    return entityUUID;
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

  public void save(CompoundTag tag) {
    tag.putString(TYPE, type.name());
    tag.putUUID(ENTITY_UUID, entityUUID);
    tag.putString(ENTITY_NAME, entityName);
    tag.putString(PLAYER_NAME, playerName);
    tag.putString(LEVEL_NAME, levelName);
    tag.putInt(X, x);
    tag.putInt(Y, y);
    tag.putInt(Z, z);
  }

  public static NewsEvent load(CompoundTag tag) {
    return new NewsEvent(
        NewsEventType.valueOf(tag.getString(TYPE)),
        tag.getUUID(ENTITY_UUID),
        tag.getString(ENTITY_NAME),
        tag.getString(PLAYER_NAME),
        tag.getString(LEVEL_NAME),
        tag.getInt(X),
        tag.getInt(Y),
        tag.getInt(Z));
  }
}