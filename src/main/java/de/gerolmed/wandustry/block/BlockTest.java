package de.gerolmed.wandustry.block;

import de.gerolmed.wandustry.CreativeTabs;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.Item;

public class BlockTest extends BasicBlock {
    public BlockTest() {
        super("block_test",
                FabricBlockSettings.of(Material.METAL).build(),
                new Item.Settings().itemGroup(CreativeTabs.BASE_GROUP));
    }
}
