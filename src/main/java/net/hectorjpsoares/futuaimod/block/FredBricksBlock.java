package net.hectorjpsoares.futuaimod.block;

import java.util.List;

import net.hectorjpsoares.futuaimod.item.TicketSerieBFactory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

public class FredBricksBlock extends Block {
  public FredBricksBlock(BlockBehaviour.Properties properties) {
    super(properties);
  }

  @Override
  protected List<ItemStack> getDrops(BlockState state, LootParams.Builder lootParams) {
    List<ItemStack> drops = super.getDrops(state, lootParams);
    drops.forEach(TicketSerieBFactory::nameTicket);
    return drops;
  }
}
