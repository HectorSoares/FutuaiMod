package net.hectorjpsoares.futuaimod.entity.custom;

import net.hectorjpsoares.futuaimod.sound.ModSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.level.Level;

public class PomboEntity extends Parrot {
    public PomboEntity(EntityType<? extends Parrot> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Parrot.createAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    @Override
    public SoundEvent getAmbientSound() {
        return ModSounds.PIGEON_SOUND.get();
    }
}
