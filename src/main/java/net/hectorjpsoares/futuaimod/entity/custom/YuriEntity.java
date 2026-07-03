package net.hectorjpsoares.futuaimod.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.level.Level;

public class YuriEntity extends Warden {
    public YuriEntity(EntityType<? extends Warden> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Warden.createAttributes();
    }
}