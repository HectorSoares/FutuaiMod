package net.hectorjpsoares.futuaimod.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public final class TicketSerieBFactory {
  private static final List<String> MATCHES = createMatches();
  private static int nextMatchIndex;

  private TicketSerieBFactory() {
  }

  public static synchronized ItemStack createTicket() {
    ItemStack ticket = new ItemStack(ModItems.FRED_SPECIAL_ITEM.get());
    nameTicket(ticket);
    return ticket;
  }

  public static synchronized void nameTicket(ItemStack ticket) {
    if (!ticket.is(ModItems.FRED_SPECIAL_ITEM.get())) {
      return;
    }

    if (nextMatchIndex >= MATCHES.size()) {
      Collections.shuffle(MATCHES);
      nextMatchIndex = 0;
    }

    ticket.set(DataComponents.CUSTOM_NAME,
        Component.literal("Ingresso Série B: " + MATCHES.get(nextMatchIndex++)));
  }

  private static List<String> createMatches() {
    List<String> teams = List.of(
        "América-MG", "Athletic", "Atlético-GO", "Avaí", "Botafogo-SP",
        "Ceará", "CRB", "Criciúma", "Cuiabá", "Fortaleza", "Goiás",
        "Juventude", "Londrina", "Náutico", "Novorizontino", "Operário-PR",
        "Ponte Preta", "São Bernardo", "Sport", "Vila Nova");
    List<String> matches = new ArrayList<>();

    for (String homeTeam : teams) {
      for (String awayTeam : teams) {
        if (!homeTeam.equals(awayTeam)) {
          matches.add(homeTeam + " x " + awayTeam);
        }
      }
    }

    Collections.shuffle(matches);
    return matches;
  }
}
