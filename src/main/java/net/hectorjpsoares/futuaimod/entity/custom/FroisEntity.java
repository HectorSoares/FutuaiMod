package net.hectorjpsoares.futuaimod.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.level.Level;

public class FroisEntity extends Vindicator {
    public FroisEntity(EntityType<? extends Vindicator> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Vindicator.createAttributes();
    }
}
