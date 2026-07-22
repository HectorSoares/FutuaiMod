package net.hectorjpsoares.futuaimod.villager;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModPOIs {

  public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(
      Registries.POINT_OF_INTEREST_TYPE,
      FutUaiMod.MOD_ID);

  public static final RegistryObject<PoiType> JORNALISTA = POI_TYPES.register("jornalista", () -> new PoiType(
      Set.of(
          Blocks.CRAFTING_TABLE.defaultBlockState()),
      1,
      1));
}