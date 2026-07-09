package net.hectorjpsoares.futuaimod.entity.client;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PhantomRenderer;
import net.minecraft.client.renderer.entity.VillagerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.Villager;

public class HectorRenderer extends PhantomRenderer {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(FutUaiMod.MOD_ID, "textures/entity/default_mob.png");

    public HectorRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    // @Override
    // public ResourceLocation getTextureLocation(Villager entity) {
    //     return TEXTURE;
    // }
}
