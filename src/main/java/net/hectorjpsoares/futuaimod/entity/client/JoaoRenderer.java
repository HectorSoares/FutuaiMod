package net.hectorjpsoares.futuaimod.entity.client;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.FrogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.frog.Frog;

public class JoaoRenderer extends FrogRenderer {
  private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(FutUaiMod.MOD_ID,
      "textures/entity/joao_mob.png");

  public JoaoRenderer(EntityRendererProvider.Context context) {
    super(context);
  }

  @Override
  public ResourceLocation getTextureLocation(Frog entity) {
    return TEXTURE;
  }
}
