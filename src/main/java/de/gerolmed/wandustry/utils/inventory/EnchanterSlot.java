package de.gerolmed.wandustry.utils.inventory;

import net.minecraft.container.Slot;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class EnchanterSlot extends Slot {

    private final Type type;

    public EnchanterSlot(Inventory inventory, int inventoryNr, int xPos, int yPos, Type type) {
        super(inventory, inventoryNr, xPos, yPos);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public int getMaxStackAmount() {
        return 1;
    }

    public enum Type {
        WAND(de.gerolmed.wandustry.Items.ITEM_WAND, Items.AIR),

        TYPE_1(WAND, Items.NETHER_STAR),
        MOD_1_1(TYPE_1, Items.STONE, Items.AIR),
        MOD_1_2(TYPE_1, Items.STONE, Items.AIR),
        MOD_1_3(TYPE_1, Items.STONE, Items.AIR),
        MOD_1_4(TYPE_1, Items.STONE, Items.AIR),

        TYPE_2(WAND, Items.QUARTZ),
        MOD_2_1(TYPE_2, Items.STONE, Items.AIR),
        MOD_2_2(TYPE_2, Items.STONE, Items.AIR),
        MOD_2_3(TYPE_2, Items.STONE, Items.AIR),
        MOD_2_4(TYPE_2, Items.STONE, Items.AIR),

        TYPE_3(WAND, Items.BEACON),
        MOD_3_1(TYPE_3, Items.STONE, Items.AIR),
        MOD_3_2(TYPE_3, Items.STONE, Items.AIR),
        MOD_3_3(TYPE_3, Items.STONE, Items.AIR),
        MOD_3_4(TYPE_3, Items.STONE, Items.AIR);

        private final Type parent;
        private Item[] items;

        Type(Item... items) {
            this(null, items);
        }

        Type(Type parent, Item... items) {
            this.parent = parent;
            this.items = items;
            if(this.items == null)
                this.items = new Item[0];
        }

        public boolean isValidItem(ItemStack itemStack) {
            Item item = itemStack.getItem();

            for(Item it : items)
                if(it.equals(item))
                    return true;

            return false;
        }

        public Type getParent() {
            return parent;
        }
    }
}
