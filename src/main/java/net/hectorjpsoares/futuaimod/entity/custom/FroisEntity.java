package net.hectorjpsoares.futuaimod.entity.custom;

import net.hectorjpsoares.futuaimod.item.ModItems;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class FroisEntity extends Vindicator {
  public FroisEntity(EntityType<? extends Vindicator> entityType, Level level) {
    super(entityType, level);
    this.setItemSlot(EquipmentSlot.MAINHAND, createFroisAxe(level.registryAccess()));
    this.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
  }

  private boolean recebeuTorresminho = false;
  private boolean recebeuCerveja = false;
  private boolean pacificado = false;

  public static AttributeSupplier.Builder createAttributes() {
    return Vindicator.createAttributes()
        .add(Attributes.ATTACK_DAMAGE, 4.0D);
  }

  @Override
  public boolean isAggressive() {
    return !pacificado;
  }

  @Override
  public boolean canAttack(net.minecraft.world.entity.LivingEntity target) {
    return !pacificado && super.canAttack(target);
  }

  @Override
  public void setTarget(net.minecraft.world.entity.LivingEntity target) {
    super.setTarget(pacificado ? null : target);
  }

  @Override
  public InteractionResult mobInteract(Player player, InteractionHand hand) {
    ItemStack item = player.getItemInHand(hand);
    boolean entregouTorresminho = !pacificado && item.is(ModItems.CRACKLING_PORK.get());
    boolean entregouCerveja = !pacificado && item.is(ModItems.COLD_BEER.get());

    if (!entregouTorresminho && !entregouCerveja) {
      return super.mobInteract(player, hand);
    }

    if (!this.level().isClientSide) {
      if (entregouTorresminho) {
        recebeuTorresminho = true;
      }
      if (entregouCerveja) {
        recebeuCerveja = true;
      }

      item.shrink(1);

      if (recebeuTorresminho && recebeuCerveja) {
        pacificado = true;
        this.setTarget(null);
        this.spawnAtLocation(ModItems.FRED_SPECIAL_ITEM.get());
      }
    }

    return InteractionResult.sidedSuccess(this.level().isClientSide);
  }

  @Override
  protected void dropCustomDeathLoot(ServerLevel level, DamageSource damageSource, boolean recentlyHit) {
    super.dropCustomDeathLoot(level, damageSource, recentlyHit);
    this.spawnAtLocation(createFroisAxe(level.registryAccess()));
  }

  @Override
  public void addAdditionalSaveData(CompoundTag tag) {
    super.addAdditionalSaveData(tag);
    tag.putBoolean("RecebeuTorresminho", recebeuTorresminho);
    tag.putBoolean("RecebeuCerveja", recebeuCerveja);
    tag.putBoolean("Pacificado", pacificado);
  }

  @Override
  public void readAdditionalSaveData(CompoundTag tag) {
    super.readAdditionalSaveData(tag);
    recebeuTorresminho = tag.getBoolean("RecebeuTorresminho");
    recebeuCerveja = tag.getBoolean("RecebeuCerveja");
    pacificado = tag.getBoolean("Pacificado");
  }

  private static ItemStack createFroisAxe(RegistryAccess registryAccess) {
    ItemStack axe = new ItemStack(ModItems.FROIS_AXE.get());
    axe.enchant(
        registryAccess.registryOrThrow(Registries.ENCHANTMENT)
            .getHolderOrThrow(Enchantments.SHARPNESS),
        5);
    axe.enchant(
        registryAccess.registryOrThrow(Registries.ENCHANTMENT)
            .getHolderOrThrow(Enchantments.EFFICIENCY),
        5);
    axe.enchant(
        registryAccess.registryOrThrow(Registries.ENCHANTMENT)
            .getHolderOrThrow(Enchantments.FORTUNE),
        5);
    axe.enchant(
        registryAccess.registryOrThrow(Registries.ENCHANTMENT)
            .getHolderOrThrow(Enchantments.UNBREAKING),
        5);
    return axe;
  }
}
