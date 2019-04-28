package de.gerolmed.wandustry.item;

import de.gerolmed.wandustry.interfaces.IItem;
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
