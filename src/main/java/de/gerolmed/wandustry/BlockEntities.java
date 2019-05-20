package de.gerolmed.wandustry;

import de.gerolmed.wandustry.block.BlockEnchanter;
import de.gerolmed.wandustry.block.entity.EnchanterBlockEntity;
import de.gerolmed.wandustry.block.render.EnchanterBlockEntityRenderer;
import net.fabricmc.fabric.api.client.render.BlockEntityRendererRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashSet;

public class BlockEntities {

    private static final Logger LOGGER = LogManager.getLogger(BlockEntities.class);

    public static final BlockEntityType<EnchanterBlockEntity> ENCHANTER = BlockEntityType.Builder.create(EnchanterBlockEntity::new, Blocks.BLOCK_ENCHANTER).build(null);

    public static void register(){
        LOGGER.info("Starting to register all BlockEntities");
        register("enchanter", ENCHANTER, EnchanterBlockEntity.class, new EnchanterBlockEntityRenderer());
    }

    private static <T extends BlockEntity> void register(String registryName, BlockEntityType<T> type, Class<T> clazz, BlockEntityRenderer<T> renderer) {
        Registry.register(Registry.BLOCK_ENTITY, new Identifier(WandustryMod.MOD_ID, registryName), type);
        BlockEntityRendererRegistry.INSTANCE.register( clazz, renderer);

        LOGGER.info("Registering block entity {}", registryName);

    }
}
