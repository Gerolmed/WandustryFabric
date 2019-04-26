package de.gerolmed.fabricmod;

import de.gerolmed.fabricmod.item.BasicItem;
import de.gerolmed.fabricmod.item.ItemMagicIngot;
import de.gerolmed.fabricmod.item.ItemTest;

/**
 * Class offers all custom items
 */
public class Items {

    public static final ItemTest ITEM_TEST = new ItemTest();
    public static final ItemMagicIngot ITEM_MAGIC_INGOT = new ItemMagicIngot();

    public static BasicItem[] values() {
        return new BasicItem[] {ITEM_TEST, ITEM_MAGIC_INGOT};
    }
}
