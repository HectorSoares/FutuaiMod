package net.hectorjpsoares.futuaimod.entity.client;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.VindicatorRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Vindicator;

public class FroisRenderer extends VindicatorRenderer {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(FutUaiMod.MOD_ID, "textures/entity/frois_mob.png");

    public FroisRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(Vindicator entity) {
        return TEXTURE;
    }
}
