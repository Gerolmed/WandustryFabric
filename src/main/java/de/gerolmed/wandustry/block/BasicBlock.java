package de.gerolmed.wandustry.block;

import com.sun.istack.internal.Nullable;
import de.gerolmed.wandustry.interfaces.IBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class BasicBlock extends Block implements IBlock {

    private final String registryName;
    private final Item.Settings itemSettings;

    public BasicBlock(String registryName, Settings settings, @Nullable Item.Settings itemSettings) {
        super(settings);
        this.registryName = registryName;
        this.itemSettings = itemSettings;
    }

    @Override
    public String getRegistryName() {
        return registryName;
    }

    @Override
    public boolean hasBlockItem() {
        return itemSettings != null;
    }

    @Override
    public Item.Settings getItemSettings() {
        return itemSettings;
    }
}
