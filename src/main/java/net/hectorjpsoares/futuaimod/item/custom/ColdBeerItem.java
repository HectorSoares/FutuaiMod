package net.hectorjpsoares.futuaimod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;

public class ColdBeerItem extends Item {
    public ColdBeerItem(Properties properties) {
        super(properties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK; // Define a animação de beber
    }
}