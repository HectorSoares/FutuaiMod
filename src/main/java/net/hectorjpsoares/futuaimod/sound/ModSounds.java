package net.hectorjpsoares.futuaimod.sound;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FutUaiMod.MOD_ID);


    public static final RegistryObject<SoundEvent> BLACK_PEARL_JAM = registerSoundEvent("black_pearl_jam");
    public static final RegistryObject<SoundEvent> SNORING_SOUND = registerSoundEvent("snoring_sound");
    public static final RegistryObject<SoundEvent> PIGEON_SOUND = registerSoundEvent("pigeon_sound");
    public static final ResourceKey<JukeboxSong> BLACK_PEARL_JAM_KEY = ResourceKey.create(Registries.JUKEBOX_SONG,
            ResourceLocation.fromNamespaceAndPath(FutUaiMod.MOD_ID, "black_pearl_jam"));



    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(FutUaiMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
