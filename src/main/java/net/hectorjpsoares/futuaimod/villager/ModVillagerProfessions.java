package net.hectorjpsoares.futuaimod.villager;

import com.google.common.collect.ImmutableSet;
import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModVillagerProfessions {

  public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(
      Registries.VILLAGER_PROFESSION,
      FutUaiMod.MOD_ID);

  public static final RegistryObject<VillagerProfession> JORNALISTA = PROFESSIONS.register("jornalista",
      () -> new VillagerProfession(
          "jornalista",
          holder -> holder.is(
              ModPOIs.JORNALISTA.getKey()),
          holder -> holder.is(
              ModPOIs.JORNALISTA.getKey()),
          ImmutableSet.of(),
          ImmutableSet.of(),
          SoundEvents.VILLAGER_WORK_LIBRARIAN));
}