package net.hectorjpsoares.futuaimod.entity.client;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.hectorjpsoares.futuaimod.entity.custom.FutUaiFoxEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.FoxRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Fox;

public class FutUaiFoxRenderer extends FoxRenderer {

    private static final ResourceLocation ADULT_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    FutUaiMod.MOD_ID,
                    "textures/entity/fox/blue.png"
            );

    private static final ResourceLocation BABY_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    FutUaiMod.MOD_ID,
                    "textures/entity/fox/blue.png"
            );

    public FutUaiFoxRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(Fox fox) {
        FutUaiFoxEntity entity = (FutUaiFoxEntity) fox;
        return entity.isBaby() ? BABY_TEXTURE : ADULT_TEXTURE;
    }
    
}