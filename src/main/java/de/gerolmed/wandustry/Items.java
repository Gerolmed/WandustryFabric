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
    public static final ItemRubyShard ITEM_RUBY_SHARD = new ItemRubyShard();
    public static final ItemEmeraldShard ITEM_EMERALD_SHARD = new ItemEmeraldShard();
    public static final ItemSapphireShard ITEM_SAPPHIRE_SHARD = new ItemSapphireShard();

    public static BasicItem[] values() {
        return new BasicItem[] {
                ITEM_TEST,
                ITEM_MAGIC_INGOT,
                ITEM_MAGIC_IRON,
                ITEM_MAGIC_GOLD,
                ITEM_MAGIC_DIAMOND,
                ITEM_RUBY_SHARD,
                ITEM_EMERALD_SHARD,
                ITEM_SAPPHIRE_SHARD
        };
    }
}
