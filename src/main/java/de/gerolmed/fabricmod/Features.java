package de.gerolmed.fabricmod;

import de.gerolmed.fabricmod.structure.simple.SimpleFeature;
import de.gerolmed.fabricmod.structure.simple.SimpleGenerator;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.StructureFeature;

public class Features {

    public static final StructurePieceType myStructurePieceType = Registry.register(Registry.STRUCTURE_PIECE, "my_piece", SimpleGenerator.Piece::new);
    public static final StructureFeature<DefaultFeatureConfig> myFeature = Registry.register(Registry.FEATURE, "my_feature", new SimpleFeature());
    public static final StructureFeature<?> myStructure = Registry.register(Registry.STRUCTURE_FEATURE, "my_structure", myFeature);

    public static void initialize() {
        Feature.STRUCTURES.put("My Awesome Feature", myFeature);

        for(Biome biome : Registry.BIOME)
        {
            if(biome.getCategory() != Biome.Category.OCEAN && biome.getCategory() != Biome.Category.RIVER)
            {
                biome.addStructureFeature(myFeature, new DefaultFeatureConfig());
                biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(myFeature, new DefaultFeatureConfig(), Decorator.CHANCE_PASSTHROUGH, new ChanceDecoratorConfig(0)));
            }
        }
    }

}
