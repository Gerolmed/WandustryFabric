package de.gerolmed.fabricmod;

import de.gerolmed.fabricmod.structure.temple.SmallRubyTempleFeature;
import de.gerolmed.fabricmod.structure.temple.SmallRubyTempleGenerator;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.HashMap;
import java.util.Map;

public class Features {

    private static HashMap<String, StructurePieceType> pieces = new HashMap<>();
    private static HashMap<String, StructureFeature<DefaultFeatureConfig>> features = new HashMap<>();
    private static HashMap<String, StructureFeature<?>> structures = new HashMap<>();

    //Init hashmap data
    static {
        //Register pieces
        pieces.put("small_ruby_temple", Registry.register(Registry.STRUCTURE_PIECE, "small_ruby_temple", SmallRubyTempleGenerator.Piece::new));

        //Register features
        features.put("small_ruby_temple", Registry.register(Registry.FEATURE, "small_ruby_temple", new SmallRubyTempleFeature()));

        //Register structures
        structures.put("small_ruby_temple",  Registry.register(Registry.STRUCTURE_FEATURE, "small_ruby_temple", getFeature("small_ruby_temple")));
    }


    public static StructurePieceType getPiece(String key) {
        return pieces.get(key);
    }

    public static StructureFeature<DefaultFeatureConfig> getFeature(String key) {
        return features.get(key);
    }

    public static StructureFeature<?> getStructure(String key) {
        return structures.get(key);
    }

    public static void initialize() {
        for(Map.Entry<String, StructureFeature<DefaultFeatureConfig>> entry : features.entrySet()) {
            Feature.STRUCTURES.put(entry.getKey(), entry.getValue());

            for(Biome biome : Registry.BIOME)
            {
                if(biome.getCategory() != Biome.Category.OCEAN && biome.getCategory() != Biome.Category.RIVER)
                {
                    //TODO read values from StructureGenerationSettings
                    biome.addStructureFeature(entry.getValue(), new DefaultFeatureConfig());
                    biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(entry.getValue(), new DefaultFeatureConfig(), Decorator.CHANCE_PASSTHROUGH, new ChanceDecoratorConfig(0)));
                }
            }
        }
    }

}
