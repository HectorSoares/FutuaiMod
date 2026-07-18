package net.hectorjpsoares.futuaimod.entity.custom;

import net.hectorjpsoares.futuaimod.item.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class FroisEntity extends Vindicator {
    public FroisEntity(EntityType<? extends Vindicator> entityType, Level level) {
        super(entityType, level);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
    }

    private boolean recebeuTorresminho = false;
    private boolean recebeuCerveja = false;

    public static AttributeSupplier.Builder createAttributes() {
        return Vindicator.createAttributes()
                .add(Attributes.ATTACK_DAMAGE, 4.0D);
    }

    @Override
    public boolean isAggressive() {
        return true;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {

        ItemStack item = player.getItemInHand(hand);

        if (!this.level().isClientSide) {
            boolean entregou = false;

            if (item.is(ModItems.CRACKLING_PORK.get())) {
                recebeuTorresminho = true;
                entregou = true;
            }

            if (item.is(ModItems.COLD_BEER.get())) {
                recebeuCerveja = true;
                entregou = true;
            }

            if (entregou) {
                item.shrink(1);
                if (recebeuTorresminho && recebeuCerveja) {
                    this.spawnAtLocation(
                        ModItems.PREXECA_MILTON.get()
                    );
                    recebeuTorresminho = false;
                    recebeuCerveja = false;
                }
                return InteractionResult.SUCCESS;
            }
        }
        return super.mobInteract(player, hand);
    }
}
