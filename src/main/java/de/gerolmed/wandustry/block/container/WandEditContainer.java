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
import org.apache.logging.log4j.Logger;

public class WandEditContainer extends Container {

    public final PlayerInventory playerInventory;
    BlockPos pos;
    private Inventory inventory;

    public WandEditContainer(int syncId, PlayerEntity playerEntity) {
        super(null, syncId);
        this.playerInventory = playerEntity.inventory;
        this.inventory = new BasicInventory(16);

        drawPlayerInventory(84);
        drawEditorInventory();
    }

    private void drawEditorInventory() {
        this.addSlot(new EnchanterSlot(inventory, 0, 147, 35, EnchanterSlot.Type.WAND));

        this.addSlot(new EnchanterSlot(inventory, 1, 109, 10, EnchanterSlot.Type.TYPE_1));
        this.addSlot(new EnchanterSlot(inventory, 1+5, 101, 35, EnchanterSlot.Type.TYPE_2));
        this.addSlot(new EnchanterSlot(inventory, 1+5*2, 109, 60, EnchanterSlot.Type.TYPE_3));
    }

    private void drawPlayerInventory(int offset) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, offset + i * 18));
            }
        }

        for (int j = 0; j < 9; ++j) {
            this.addSlot(new Slot(playerInventory, j, 8 + j * 18, offset+58));
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

        return true;
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

        //do stuff
        ItemStack cursor = playerEntity.inventory.getCursorStack();

        if(canInsertIntoSlot(cursor, slot)) {
            return super.onSlotClick(slotNr, int_2, action, playerEntity);
        }

        return cursor;
    }

    @Override
    public void close(PlayerEntity playerEntity_1) {
        super.close(playerEntity_1);
    }
}
