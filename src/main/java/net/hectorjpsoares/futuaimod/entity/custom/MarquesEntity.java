package net.hectorjpsoares.futuaimod.entity.custom;


import net.hectorjpsoares.futuaimod.trades.MarquesTrades;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;

public class MarquesEntity extends WanderingTrader {

    private boolean tradesInitialized = false;

    public MarquesEntity(EntityType<? extends WanderingTrader> entityType, Level level) {
        super(entityType, level);
    }


    @Override
    public MerchantOffers getOffers() {

        MerchantOffers offers = super.getOffers();

        if (!tradesInitialized) {
            MarquesTrades.addTrades(
                    offers,
                    this.getRandom()
            );

            tradesInitialized = true;
        }

        return offers;
    }


    public static AttributeSupplier.Builder createAttributes() {
        return WanderingTrader.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D);
    }
}