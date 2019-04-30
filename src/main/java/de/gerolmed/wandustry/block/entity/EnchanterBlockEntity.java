package de.gerolmed.wandustry.block.entity;

import de.gerolmed.wandustry.BlockEntities;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;

import java.util.ArrayList;

public class EnchanterBlockEntity extends BasicBlockEntity implements BlockEntityClientSerializable, Tickable {

    private ArrayList<ItemStack> itemStacks;
    private final int SLOT_COUNT = 5;
    private boolean ready = true;
    private int ticker = 0, tickerReset = 10;

    public EnchanterBlockEntity() {
        super(BlockEntities.ENCHANTER);
        itemStacks = new ArrayList<>();
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

        if(!ready)
            return false;
        ready = false;
        ticker = tickerReset;

        if(itemStacks.size()>= SLOT_COUNT)
            return false;

        return this.itemStacks.add(itemStack);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<ItemStack> clearAllItems() {
        if(!ready)
            return new ArrayList<>();
        ready = false;
        ticker = tickerReset;

        ArrayList<ItemStack> items = (ArrayList<ItemStack>) itemStacks.clone();
        itemStacks.clear();
        return items;
    }

    public ItemStack clearLastItem() {

        if(!ready)
            return null;
        ready = false;
        ticker = tickerReset;

        ItemStack last = null;

        if(itemStacks.size() > 0) {
            last = itemStacks.get(itemStacks.size()-1);
            itemStacks.remove(last);
        }

        return last;
    }


    @Override
    public void fromClientTag(CompoundTag compoundTag) {
        this.fromTag(compoundTag);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag compoundTag) {
        return this.toTag(compoundTag);
    }

    @Override
    public void tick() {
        if(ready)
            return;
        ticker--;
        if(ticker <= 0)
            ready = true;
    }

    public ArrayList<ItemStack> getItemStacks() {
        return itemStacks;
    }
}
