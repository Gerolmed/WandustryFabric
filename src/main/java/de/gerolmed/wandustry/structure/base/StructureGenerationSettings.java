package de.gerolmed.wandustry.structure.base;


import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;

import java.util.ArrayList;
import java.util.Arrays;

public class StructureGenerationSettings {
    private ArrayList<Biome.Category> biomes;
    private GenerationStep.Feature type;
    private int chunkDistance;

    public StructureGenerationSettings(GenerationStep.Feature type, int chunkDistance, Biome.Category... biomes) {
        this.chunkDistance = chunkDistance;
        this.biomes = new ArrayList<>(Arrays.asList(biomes));
        this.type = type;
    }

    public ArrayList<Biome.Category> getBiomes() {
        return biomes;
    }

    public GenerationStep.Feature getType() {
        return type;
    }

    public int getChunkDistance() {
        return chunkDistance;
    }
}
