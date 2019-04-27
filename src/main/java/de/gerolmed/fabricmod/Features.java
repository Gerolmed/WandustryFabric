package de.gerolmed.fabricmod;

import de.gerolmed.fabricmod.structure.base.StructureGenerationSettings;
import de.gerolmed.fabricmod.structure.temple.SmallRubyTempleFeature;
import de.gerolmed.fabricmod.structure.temple.SmallRubyTempleGenerator;
import de.gerolmed.fabricmod.structure.temple.SmallSapphireTempleFeature;
import de.gerolmed.fabricmod.structure.temple.SmallSapphireTempleGenerator;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.HashMap;
import java.util.Map;

public class Features {

    private static HashMap<String, StructurePieceType> pieces = new HashMap<>();
    private static HashMap<String, StructureFeature<DefaultFeatureConfig>> features = new HashMap<>();
    private static HashMap<String, StructureFeature<?>> structures = new HashMap<>();
    private static HashMap<String, StructureGenerationSettings> settings = new HashMap<>();

    //Init HashMap data
    static {
        //Register pieces
        pieces.put("small_ruby_temple", Registry.register(Registry.STRUCTURE_PIECE, "small_ruby_temple",
                SmallRubyTempleGenerator.Piece::new));
        pieces.put("small_sapphire_temple", Registry.register(Registry.STRUCTURE_PIECE, "small_sapphire_temple",
                SmallSapphireTempleGenerator.Piece::new));

        //Register features
        features.put("small_ruby_temple", Registry.register(Registry.FEATURE, "small_ruby_temple",
                new SmallRubyTempleFeature()));
        features.put("small_sapphire_temple", Registry.register(Registry.FEATURE, "small_sapphire_temple",
                new SmallSapphireTempleFeature()));


        //Register structures
        structures.put("small_ruby_temple",  Registry.register(Registry.STRUCTURE_FEATURE, "small_ruby_temple",
                getFeature("small_ruby_temple")));
        structures.put("small_sapphire_temple",  Registry.register(Registry.STRUCTURE_FEATURE, "small_sapphire_temple",
                getFeature("small_sapphire_temple")));


        //Add settings
        settings.put("small_ruby_temple", new StructureGenerationSettings(GenerationStep.Feature.SURFACE_STRUCTURES,
                25,Category.MUSHROOM, Category.JUNGLE, Category.FOREST, Category.TAIGA));
        settings.put("small_sapphire_temple", new StructureGenerationSettings(GenerationStep.Feature.SURFACE_STRUCTURES,
                45,Category.OCEAN, Category.RIVER, Category.ICY));

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
            StructureGenerationSettings generationSettings = settings.get(entry.getKey());

            for(Biome biome : Registry.BIOME)
            {
                if(generationSettings.getBiomes().contains(biome.getCategory()))
                {
                    biome.addStructureFeature(entry.getValue(), new DefaultFeatureConfig());
                    biome.addFeature(generationSettings.getType(), Biome.configureFeature(entry.getValue(),
                            new DefaultFeatureConfig(), Decorator.CHANCE_PASSTHROUGH,
                            new ChanceDecoratorConfig(generationSettings.getChunkDistance())));
                }
            }
        }
    }

}
