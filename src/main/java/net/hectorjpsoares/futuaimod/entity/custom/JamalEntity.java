package net.hectorjpsoares.futuaimod.entity.custom;

import javax.annotation.Nullable;

import net.hectorjpsoares.futuaimod.villager.ModVillagerProfessions;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class JamalEntity extends Villager {

    public JamalEntity(
            EntityType<? extends Villager> entityType,
            Level level
    ) {
        super(entityType, level);
    }

    @Override
    public SpawnGroupData finalizeSpawn(
            ServerLevelAccessor level,
            DifficultyInstance difficulty,
            MobSpawnType spawnType,
            @Nullable SpawnGroupData spawnData
    ) {
        SpawnGroupData data = super.finalizeSpawn(
                level,
                difficulty,
                spawnType,
                spawnData
        );

        this.setVillagerData(
                this.getVillagerData().setProfession(
                        ModVillagerProfessions.JORNALISTA.get()
                )
        );

        return data;
    }
}