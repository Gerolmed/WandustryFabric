package de.gerolmed.wandustry.block.container;

import de.gerolmed.wandustry.utils.inventory.EnchanterSlot;
import net.minecraft.container.Container;
import net.minecraft.container.Slot;
import net.minecraft.container.SlotActionType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WandEditContainer extends Container {

    private static final Logger LOGGER = LogManager.getLogger(WandEditContainer.class);

    public final PlayerInventory playerInventory;
    BlockPos pos;
    private Inventory inventory;

    public WandEditContainer(int syncId, PlayerEntity playerEntity) {
        super(null, syncId);
        this.playerInventory = playerEntity.inventory;
        this.inventory = new BasicInventory(16);

        drawPlayerInventory();
        drawEditorInventory();
    }

    private void drawEditorInventory() {
        this.addSlot(new EnchanterSlot(inventory, 0, 147, 35, EnchanterSlot.Type.WAND));

        this.addSlot(new EnchanterSlot(inventory, 1, 109, 10, EnchanterSlot.Type.TYPE_1));

        this.addSlot(new EnchanterSlot(inventory, 2, 14, 10, EnchanterSlot.Type.MOD_1_1));
        this.addSlot(new EnchanterSlot(inventory, 3, 32, 10, EnchanterSlot.Type.MOD_1_2));
        this.addSlot(new EnchanterSlot(inventory, 4, 50, 10, EnchanterSlot.Type.MOD_1_3));
        this.addSlot(new EnchanterSlot(inventory, 5, 68, 10, EnchanterSlot.Type.MOD_1_4));


        this.addSlot(new EnchanterSlot(inventory, 6, 101, 35, EnchanterSlot.Type.TYPE_2));

        this.addSlot(new EnchanterSlot(inventory, 7, 6, 35, EnchanterSlot.Type.MOD_2_1));
        this.addSlot(new EnchanterSlot(inventory, 8, 24, 35, EnchanterSlot.Type.MOD_2_2));
        this.addSlot(new EnchanterSlot(inventory, 9, 42, 35, EnchanterSlot.Type.MOD_2_3));
        this.addSlot(new EnchanterSlot(inventory, 10, 60, 35, EnchanterSlot.Type.MOD_2_4));


        this.addSlot(new EnchanterSlot(inventory, 11, 109, 60, EnchanterSlot.Type.TYPE_3));

        this.addSlot(new EnchanterSlot(inventory, 12, 14, 60, EnchanterSlot.Type.MOD_3_1));
        this.addSlot(new EnchanterSlot(inventory, 13, 32, 60, EnchanterSlot.Type.MOD_3_2));
        this.addSlot(new EnchanterSlot(inventory, 14, 50, 60, EnchanterSlot.Type.MOD_3_3));
        this.addSlot(new EnchanterSlot(inventory, 15, 68, 60, EnchanterSlot.Type.MOD_3_4));
    }

    private void drawPlayerInventory() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int j = 0; j < 9; ++j) {
            this.addSlot(new Slot(playerInventory, j, 8 + j * 18, 84 +58));
        }
    }

    @Override
    public boolean canUse(PlayerEntity playerEntity) {
        return true;
    }

    @Override
    public boolean canInsertIntoSlot(Slot slot) {

        if(!(slot instanceof EnchanterSlot))
            return super.canInsertIntoSlot(slot);

        EnchanterSlot enchanterSlot = (EnchanterSlot) slot;

        //Check if slot is unlocked
        EnchanterSlot parentSlot = getSlotByType(enchanterSlot.getType().getParent());

        if(parentSlot == null)
            return true;

        return parentSlot.hasStack();
    }

    private EnchanterSlot getSlotByType(EnchanterSlot.Type parent) {

        if(parent == null)
            return null;

        for(Slot slot : slotList) {
            if(slot instanceof EnchanterSlot) {
                EnchanterSlot enchanterSlot = (EnchanterSlot) slot;

                if(enchanterSlot.getType() == parent) {
                    return enchanterSlot;
                }
            }
        }

        return null;
    }

    @Override
    public boolean canInsertIntoSlot(ItemStack itemStack, Slot slot) {

        if(!canInsertIntoSlot(slot))
            return false;

        if(!(slot instanceof EnchanterSlot))
            return super.canInsertIntoSlot(itemStack, slot);

        EnchanterSlot enchanterSlot = (EnchanterSlot) slot;

        return enchanterSlot.getType().isValidItem(itemStack);
    }

    @Override
    public ItemStack onSlotClick(int slotNr, int int_2, SlotActionType action, PlayerEntity playerEntity) {

        if(slotNr < 0)
            return super.onSlotClick(slotNr, int_2, action, playerEntity);

        Slot slot = this.slotList.get(slotNr);

        if(!(slot instanceof EnchanterSlot))
            return super.onSlotClick(slotNr, int_2, action, playerEntity);

        EnchanterSlot enchanterSlot = (EnchanterSlot) slot;

        ItemStack oldVal = enchanterSlot.getStack().copy();
        ItemStack cursor = playerEntity.inventory.getCursorStack();

        if(canInsertIntoSlot(cursor, slot)) {

            ItemStack returnValue = super.onSlotClick(slotNr, int_2, action, playerEntity);

            if(enchanterSlot.getType() == EnchanterSlot.Type.WAND) {
                if(action == SlotActionType.PICKUP || action == SlotActionType.PICKUP_ALL) {
                    ItemStack newVal = enchanterSlot.getStack();

                    pop(oldVal);
                    push(newVal);

                    playerEntity.inventory.setCursorStack(oldVal);
                }
                return returnValue;
            }

            return returnValue;
        }

        return cursor;
    }

    /**
     * Reads all data from the inventory and applies it to the wand
     *
     * @param itemStack the value to read to
     */
    private void pop(ItemStack itemStack) {
        for(int i = 1; i < inventory.getInvSize(); i++) {
            inventory.setInvStack(i, ItemStack.EMPTY);
        }
    }

    /**
     * Reads all data from the wand and applies it to the inventory
     *
     * @param itemStack the value to read from
     */
    private void push(ItemStack itemStack) {
    }

    @Override
    public void close(PlayerEntity playerEntity_1) {
        super.close(playerEntity_1);
    }
}
