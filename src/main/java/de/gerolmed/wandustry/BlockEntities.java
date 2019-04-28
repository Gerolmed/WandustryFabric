package de.gerolmed.wandustry;

import de.gerolmed.wandustry.block.entity.EnchanterBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockEntities {

    public static final BlockEntityType<EnchanterBlockEntity> ENCHANTER = create("enchanter", BlockEntityType.Builder.create(EnchanterBlockEntity::new));

    @SuppressWarnings("unchecked")
    private static <T extends net.minecraft.block.entity.BlockEntity> BlockEntityType<T> create(String registryName, BlockEntityType.Builder builder) {

        return Registry.register(Registry.BLOCK_ENTITY, new Identifier(WandustryMod.MOD_ID, registryName), builder.build(null));
    }
}
