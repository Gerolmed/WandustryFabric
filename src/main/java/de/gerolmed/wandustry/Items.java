package de.gerolmed.wandustry;

import de.gerolmed.wandustry.item.*;

/**
 * Class offers all custom items
 */
public class Items {

    public static final ItemTest ITEM_TEST = new ItemTest();
    public static final ItemMagicIngot ITEM_MAGIC_INGOT = new ItemMagicIngot();
    public static final ItemMagicIron ITEM_MAGIC_IRON = new ItemMagicIron();
    public static final ItemMagicGold ITEM_MAGIC_GOLD = new ItemMagicGold();
    public static final ItemMagicDiamond ITEM_MAGIC_DIAMOND = new ItemMagicDiamond();

    public static BasicItem[] values() {
        return new BasicItem[] {ITEM_TEST, ITEM_MAGIC_INGOT, ITEM_MAGIC_IRON, ITEM_MAGIC_GOLD, ITEM_MAGIC_DIAMOND};
    }
}
