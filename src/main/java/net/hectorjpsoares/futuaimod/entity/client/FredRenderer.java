package net.hectorjpsoares.futuaimod.entity.client;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.minecraft.client.renderer.entity.EndermanRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.EnderMan;

public class FredRenderer extends EndermanRenderer {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(FutUaiMod.MOD_ID, "textures/entity/fred_mob.png");

    public FredRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.layers.clear();
    }

    @Override
    public ResourceLocation getTextureLocation(EnderMan entity) {
        return TEXTURE;
    }
}
