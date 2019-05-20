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

    public enum Type {
        WAND(Items.STICK),

        TYPE_1(Items.NETHER_STAR),
        MOD_1_1,
        MOD_1_2,
        MOD_1_3,
        MOD_1_4,

        TYPE_2(Items.QUARTZ),
        MOD_2_1,
        MOD_2_2,
        MOD_2_3,
        MOD_2_4,

        TYPE_3(Items.BEACON),
        MOD_3_1,
        MOD_3_2,
        MOD_3_3,
        MOD_3_4;

        private Item[] items;

        Type(Item... items) {
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
    }
}
