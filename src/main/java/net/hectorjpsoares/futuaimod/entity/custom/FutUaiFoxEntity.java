package net.hectorjpsoares.futuaimod.entity.custom;

import net.hectorjpsoares.futuaimod.entity.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;

public class FutUaiFoxEntity extends Fox {

    public FutUaiFoxEntity(EntityType<? extends Fox> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Fox.createAttributes();
    }

    @Override
    public Fox getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return ModEntities.FUTUAI_FOX.get().create(level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return super.isFood(stack) 
                || stack.is(Items.CHICKEN);
    }
}