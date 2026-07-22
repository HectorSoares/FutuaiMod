package net.hectorjpsoares.futuaimod.entity.custom;

import net.hectorjpsoares.futuaimod.item.ModItems;
import net.hectorjpsoares.futuaimod.sound.ModSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class YuriEntity extends Warden {
    public YuriEntity(EntityType<? extends Warden> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Warden.createAttributes()
                .add(Attributes.MAX_HEALTH, 400.0D)          // igual ao jogador
                .add(Attributes.ATTACK_DAMAGE, 10.0D)        // 1,5 coração
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    protected void dropCustomDeathLoot(ServerLevel level, DamageSource damageSource, boolean recentlyHit) {
        super.dropCustomDeathLoot(level, damageSource, recentlyHit);

        this.spawnAtLocation(new ItemStack(ModItems.BLACK_PEARL_JAM_DISC.get()));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.YURI_AMBIENT_SOUND.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ModSounds.YURI_HURT_SOUND.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.YURI_DEATH_SOUND.get();
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

    @Override
    public boolean hurt(DamageSource source, float amount) {

        Entity attacker = source.getEntity();

        if (attacker instanceof Player player) {
            stealAdrenalineInjection(player);
        }

        return super.hurt(source, amount);
    }

    private void stealAdrenalineInjection(Player player) {
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {

            ItemStack stack = player.getInventory().getItem(i);

            if (stack.is(ModItems.ADRENALINE_INJECTION.get())) {
                stack.shrink(1);

                if (stack.isEmpty()) {
                    player.getInventory().setItem(i, ItemStack.EMPTY);
                }

                return;
            }
        }
    }
}
