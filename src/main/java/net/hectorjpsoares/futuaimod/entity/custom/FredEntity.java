package net.hectorjpsoares.futuaimod.entity.custom;

import net.hectorjpsoares.futuaimod.sound.ModSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.level.Level;

public class FredEntity extends EnderMan {
    public FredEntity(EntityType<? extends EnderMan> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return EnderMan.createAttributes();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.FRED_SOUND.get();
    }
}
