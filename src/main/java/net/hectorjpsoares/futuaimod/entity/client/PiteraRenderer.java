package net.hectorjpsoares.futuaimod.entity.client;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PhantomRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Phantom;

public class PiteraRenderer extends PhantomRenderer {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    FutUaiMod.MOD_ID,
                    "textures/entity/pitera_mob.png"
            );

    public PiteraRenderer(EntityRendererProvider.Context context) {
        super(context);

        this.layers.clear();
    }

    @Override
    public ResourceLocation getTextureLocation(Phantom entity) {
        return TEXTURE;
    }
}