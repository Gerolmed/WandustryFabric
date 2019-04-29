package de.gerolmed.wandustry.block.entity;

import de.gerolmed.wandustry.BlockEntities;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

import java.util.ArrayList;

public class EnchanterBlockEntity extends BasicBlockEntity {

    private ArrayList<ItemStack> itemStacks;
    private final int SLOT_COUNT = 5;

    public EnchanterBlockEntity() {
        super(BlockEntities.ENCHANTER);
    }

    @Override
    public CompoundTag toTag(CompoundTag compoundTag_1) {

        int i = 0;
        for(ItemStack itemStack : itemStacks) {
            compoundTag_1.put("item"+i, itemStack.toTag(new CompoundTag()));
            i++;
        }

        return super.toTag(compoundTag_1);
    }

    @Override
    public void fromTag(CompoundTag compoundTag_1) {

        for(int i = 0; i < SLOT_COUNT; i++) {
            if(compoundTag_1.containsKey("item"+i)) {
                this.itemStacks.add(ItemStack.fromTag(compoundTag_1.getCompound("item"+i)));
            }
        }

        super.fromTag(compoundTag_1);
    }

    /**
     * Tries to add an item to the list. If this is not possible this method will return false
     *
     * @param itemStack item to add
     * @return was successful
     */
    public boolean addItemStack(ItemStack itemStack) {

        if(itemStacks.size()>= SLOT_COUNT)
            return false;

        return this.itemStacks.add(itemStack);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<ItemStack> clearAllItems() {
        ArrayList<ItemStack> items = (ArrayList<ItemStack>) itemStacks.clone();
        itemStacks.clear();
        return items;
    }

    public ItemStack clearLastItem() {
        ItemStack last = null;

        if(itemStacks.size() > 0) {
            last = itemStacks.get(itemStacks.size()-1);
            itemStacks.remove(last);
        }

        return last;
    }
}
