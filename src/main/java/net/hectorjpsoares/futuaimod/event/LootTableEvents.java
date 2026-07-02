package net.hectorjpsoares.futuaimod.event;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.hectorjpsoares.futuaimod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FutUaiMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LootTableEvents {

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        // Verifica se é o porco
        if ("minecraft:entities/pig".equals(event.getName().toString())) {

            // Adiciona a pele de porco ao loot
            event.getTable().addPool(LootPool.lootPool()
                    .name("pork_skin_drop")
                    .add(LootItem.lootTableItem(ModItems.PORK_SKIN.get()))
                    .setRolls(ConstantValue.exactly(1)) // Quantidade por drop
                    .build());
        }
    }
}