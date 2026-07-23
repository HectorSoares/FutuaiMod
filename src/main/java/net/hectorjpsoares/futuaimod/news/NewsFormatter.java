package net.hectorjpsoares.futuaimod.news;

public class NewsFormatter {

  public static String format(NewsEvent event) {
    return switch (event.getType()) {
      case MOB_KILLED -> formatMobKilled(event);
      case ENTITY_SPOTTED -> formatEntitySpotted(event);
    };
  }

  private static String formatMobKilled(NewsEvent event) {
    return event.getPlayerName()
        + " matou "
        + event.getEntityName()
        + ".\n["
        + event.getX()
        + ", "
        + event.getY()
        + ", "
        + event.getZ()
        + "]";
  }

  private static String formatEntitySpotted(NewsEvent event) {
    return event.getPlayerName()
        + " avistou "
        + event.getEntityName()
        + ".\n["
        + event.getX()
        + ", "
        + event.getY()
        + ", "
        + event.getZ()
        + "]";
  }
}