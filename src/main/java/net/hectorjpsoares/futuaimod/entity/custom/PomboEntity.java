package net.hectorjpsoares.futuaimod.entity.custom;

import java.util.List;

import net.hectorjpsoares.futuaimod.item.ModItems;
import net.hectorjpsoares.futuaimod.sound.ModSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class PomboEntity extends Parrot {
    public PomboEntity(EntityType<? extends Parrot> entityType, Level level) {
        super(entityType, level);
    }

    private boolean droppedForFox = false;

    public static AttributeSupplier.Builder createAttributes() {
        return Parrot.createAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    @Override
    public SoundEvent getAmbientSound() {
        return ModSounds.POMBO_AMBIENT_SOUND.get();
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide) {
            checkFoxInteraction();
        }
    }

    private void checkFoxInteraction() {

        if (droppedForFox) {
            return;
        }

        var foxes = this.level()
                .getEntitiesOfClass(
                        FutUaiFoxEntity.class,
                        this.getBoundingBox().inflate(5)
                );

        boolean hasAdultFox = foxes.stream()
                .anyMatch(fox -> !fox.isBaby());

        boolean hasBabyFox = foxes.stream()
                .anyMatch(fox -> fox.isBaby());

        if (hasAdultFox && hasBabyFox) {

            this.spawnAtLocation(
                    Items.PINK_SHULKER_BOX
            );

            droppedForFox = true;
        }
    }
}
