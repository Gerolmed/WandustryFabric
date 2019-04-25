package de.gerolmed.fabricmod.item;

import de.gerolmed.fabricmod.interfaces.IItem;
import net.minecraft.item.Item;

public class BasicItem extends Item implements IItem{

    private final String registryName;

    public BasicItem(String registryName, Settings settings) {
        super(settings);
        this.registryName = registryName;
    }

    @Override
    public String getRegistryName() {
        return registryName;
    }
}
