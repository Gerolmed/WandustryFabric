package de.gerolmed.wandustry.interfaces;

import net.minecraft.item.Item;

public interface IBlock {
    String getRegistryName();
    boolean hasBlockItem();
    Item.Settings getItemSettings();
}
