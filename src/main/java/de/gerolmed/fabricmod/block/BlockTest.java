package de.gerolmed.fabricmod.block;

import de.gerolmed.fabricmod.CreativeTabs;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BlockTest extends BasicBlock {
    public BlockTest() {
        super("block_test",
                FabricBlockSettings.of(Material.METAL).build(),
                new Item.Settings().itemGroup(CreativeTabs.BASE_GROUP));
    }
}
