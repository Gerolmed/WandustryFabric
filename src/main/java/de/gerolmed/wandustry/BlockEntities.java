package de.gerolmed.wandustry;

import de.gerolmed.wandustry.block.entity.EnchanterBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlockEntities {

    private static final Logger LOGGER = LogManager.getLogger(BlockEntities.class);

    public static final BlockEntityType<EnchanterBlockEntity> ENCHANTER = BlockEntityType.Builder.create(EnchanterBlockEntity::new).build(null);

    public static void register(){
        LOGGER.info("Starting to register all BlockEntities");
        register("enchanter", ENCHANTER);
    }

    private static void register(String registryName, BlockEntityType<? extends BlockEntity> type) {
        Registry.register(Registry.BLOCK_ENTITY, new Identifier(WandustryMod.MOD_ID, registryName), type);
        LOGGER.info("Registering block entity {}", registryName);

    }
}
