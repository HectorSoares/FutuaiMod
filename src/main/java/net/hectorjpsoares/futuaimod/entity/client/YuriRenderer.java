package net.hectorjpsoares.futuaimod.entity.client;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.hectorjpsoares.futuaimod.entity.client.layers.YuriEyesLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WardenRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.warden.Warden;

public class YuriRenderer extends WardenRenderer {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    FutUaiMod.MOD_ID,
                    "textures/entity/yuri_mob.png"
            );

    public YuriRenderer(EntityRendererProvider.Context context) {
        super(context);

        this.layers.clear();
    }

    @Override
    public ResourceLocation getTextureLocation(Warden entity) {
        return TEXTURE;
    }
}

