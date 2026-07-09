package net.hectorjpsoares.futuaimod.entity.custom;

import net.hectorjpsoares.futuaimod.item.ModItems;
import net.hectorjpsoares.futuaimod.sound.ModSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class YuriEntity extends Warden {
    public YuriEntity(EntityType<? extends Warden> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Warden.createAttributes();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.YURI_AMBIENT_SOUND.get();
    }

    @Override
    public void tick() {
        super.tick();

        if (isHoldingPortoFaria(this.getTarget())) {
            this.setTarget(null);
        }
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        return !isHoldingPortoFaria(target) && super.canAttack(target);
    }

    @Override
    public boolean canTargetEntity(Entity entity) {
        return !isHoldingPortoFaria(entity) && super.canTargetEntity(entity);
    }

    private static boolean isHoldingPortoFaria(Entity entity) {
        if (!(entity instanceof Player player)) {
            return false;
        }

        return player.getMainHandItem().is(ModItems.PORTO_FARIA.get())
                || player.getOffhandItem().is(ModItems.PORTO_FARIA.get());
    }
}
