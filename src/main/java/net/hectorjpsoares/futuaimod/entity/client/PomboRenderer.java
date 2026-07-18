package net.hectorjpsoares.futuaimod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.hectorjpsoares.futuaimod.entity.custom.PomboEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ParrotRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Parrot;

public class PomboRenderer extends ParrotRenderer {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(FutUaiMod.MOD_ID, "textures/entity/pombo_mob.png");

    public PomboRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(Parrot entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(net.minecraft.world.entity.animal.Parrot entity,
                        PoseStack poseStack,
                        float partialTickTime) {
        poseStack.scale(2.0F, 2.0F, 2.0F);
    }
}
