package de.gerolmed.wandustry.block.entity;

import de.gerolmed.wandustry.BlockEntities;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class EnchanterBlockEntity extends BasicBlockEntity {

    private ItemStack itemStack;

    public EnchanterBlockEntity() {
        super(BlockEntities.ENCHANTER);
    }

    @Override
    public CompoundTag toTag(CompoundTag compoundTag_1) {

        if(itemStack != null) {
            compoundTag_1.put("item", itemStack.toTag(new CompoundTag()));
        }

        return super.toTag(compoundTag_1);
    }

    @Override
    public void fromTag(CompoundTag compoundTag_1) {

        if(compoundTag_1.containsKey("item")) {
            this.itemStack = ItemStack.fromTag(compoundTag_1.getCompound("item"));
        }

        super.fromTag(compoundTag_1);
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        System.out.println("Setting item");
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
