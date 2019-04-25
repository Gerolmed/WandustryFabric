package de.gerolmed.fabricmod.structure.base;


import com.sun.istack.internal.NotNull;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;

import java.util.ArrayList;
import java.util.Arrays;

public class StructureGenerationSettings {
    private ArrayList<Biome.Category> biomes;
    private GenerationStep.Feature type;

    public StructureGenerationSettings(GenerationStep.Feature type, @NotNull Biome.Category... biomes) {
        this.biomes = new ArrayList<>(Arrays.asList(biomes));
        this.type = type;
    }

    public ArrayList<Biome.Category> getBiomes() {
        return biomes;
    }

    public GenerationStep.Feature getType() {
        return type;
    }
}
