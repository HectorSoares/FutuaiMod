package net.hectorjpsoares.futuaimod.entity.custom;

import net.hectorjpsoares.futuaimod.item.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class ZePedroEntity extends Guardian {

    public ZePedroEntity(EntityType<? extends Guardian> entityType, Level level) {
        super(entityType, level);
    }

    

    public static AttributeSupplier.Builder createAttributes() {
        return Guardian.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1,  
            new MeleeAttackGoal(this, 1.2D, true)
        );
        this.targetSelector.addGoal(1, new ZePedroTargetGoal(this));
    }
    
    private boolean hasDangerousItem(Player player) {
        return hasItem(player, Items.WHEAT)
        || hasItem(player, Items.BREAD)
        || hasItem(player, Items.COOKIE)
        || hasItem(player, Items.CAKE)
        || hasItem(player, ModItems.COLD_BEER.get());
    }
    
    @Override
    public boolean hurt(DamageSource source, float amount) {
        Entity attacker = source.getEntity();

        if (attacker instanceof Player player) {
            if (hasDangerousItem(player)) {
                return super.hurt(source, amount);
            }
        }

        return false;
    }

    private boolean hasItem(Player player, Item item) {
        for (ItemStack stack : player.getInventory().items) {
            if (stack.is(item)) return true;
        }

        return false;
    }

    private static class ZePedroTargetGoal extends TargetGoal {

        private final ZePedroEntity zePedro;
        private Player target;

        public ZePedroTargetGoal(ZePedroEntity zePedro) {
            super(zePedro, false);
            this.zePedro = zePedro;
        }

        @Override
        public boolean canUse() {

            Player nearestPlayer = this.zePedro.level().getNearestPlayer(
                    this.zePedro.getX(),
                    this.zePedro.getY(),
                    this.zePedro.getZ(),
                    16.0D,
                    player -> player instanceof Player
                            && this.zePedro.hasDangerousItem((Player) player)
            );

            if (nearestPlayer == null) return false;

            this.target = nearestPlayer;

            return true;
        }

        @Override
        public boolean canContinueToUse() {

            if (this.target == null) return false;
            if (!this.target.isAlive()) return false;
            if (this.zePedro.distanceToSqr(this.target) > 16.0D * 16.0D) return false;

            return this.zePedro.hasDangerousItem(this.target);
        }

        @Override
        public void start() {
            this.zePedro.setTarget(this.target);
            super.start();
        }

        @Override
        public void stop() {
            this.target = null;
            this.zePedro.setTarget(null);
            super.stop();
        }
    }

    @Override
    protected void dropCustomDeathLoot(
            ServerLevel level,
            DamageSource damageSource,
            boolean recentlyHit
    ) {
        super.dropCustomDeathLoot(level, damageSource, recentlyHit);

        this.spawnAtLocation(
                new ItemStack(ModItems.ADRENALINE_INJECTION.get())
        );
    }
}