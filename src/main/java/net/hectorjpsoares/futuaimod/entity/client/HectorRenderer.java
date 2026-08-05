package net.hectorjpsoares.futuaimod.entity.client;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EvokerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.level.Level;

public class HectorRenderer extends EvokerRenderer<Evoker> {
  private static final ResourceLocation OVERWORLD_TEXTURE = ResourceLocation.fromNamespaceAndPath(FutUaiMod.MOD_ID,
      "textures/entity/hector_overworld_mob.png");

  private static final ResourceLocation NETHER_TEXTURE = ResourceLocation.fromNamespaceAndPath(FutUaiMod.MOD_ID,
      "textures/entity/hector_nether_mob.png");

  public HectorRenderer(EntityRendererProvider.Context context) {
    super(context);
  }

  @Override
  public ResourceLocation getTextureLocation(Evoker entity) {
    if (entity.level().dimension().equals(Level.OVERWORLD)) {
      return OVERWORLD_TEXTURE;
    }

    return NETHER_TEXTURE;
  }
}