package net.hectorjpsoares.futuaimod.block;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FutUaiMod.MOD_ID);

  public static final RegistryObject<Block> FRED_BRICKS = BLOCKS.register("fred_bricks",
      () -> new FredBricksBlock(BlockBehaviour.Properties.of()
          .mapColor(MapColor.COLOR_RED)
          .instrument(NoteBlockInstrument.BASEDRUM)
          .strength(1.0F, 6.0F)
          .sound(SoundType.STONE)));

  public static void register(IEventBus eventBus) {
    BLOCKS.register(eventBus);
  }
}
