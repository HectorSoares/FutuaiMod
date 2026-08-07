package net.hectorjpsoares.futuaimod.entity.client;

import java.util.Iterator;

import net.hectorjpsoares.futuaimod.FutUaiMod;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.renderer.entity.EndermanRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.EnderEyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.EnderMan;

public class FredRenderer extends EndermanRenderer {

  private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
      FutUaiMod.MOD_ID,
      "textures/entity/fred_mob.png");

  public FredRenderer(EntityRendererProvider.Context context) {
    super(context);

    Iterator<RenderLayer<EnderMan, EndermanModel<EnderMan>>> iterator = this.layers.iterator();

    while (iterator.hasNext()) {
      RenderLayer<EnderMan, EndermanModel<EnderMan>> layer = iterator.next();

      if (layer instanceof EnderEyesLayer) {
        iterator.remove();
      }
    }
  }

  @Override
  public ResourceLocation getTextureLocation(EnderMan entity) {
    return TEXTURE;
  }
}