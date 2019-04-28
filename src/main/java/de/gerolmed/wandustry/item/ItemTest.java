package de.gerolmed.wandustry.item;

import de.gerolmed.wandustry.CreativeTabs;
import net.minecraft.item.Item;

public class ItemTest extends BasicItem {

    public ItemTest() {
        super("item_test", new Item.Settings().itemGroup(CreativeTabs.BASE_GROUP));
    }
}
