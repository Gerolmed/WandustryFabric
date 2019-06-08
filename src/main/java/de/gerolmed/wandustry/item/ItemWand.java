package de.gerolmed.wandustry.item;

import de.gerolmed.wandustry.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class ItemWand extends BasicItem {

    public ItemWand() {
        super("item_wand", new Settings().stackSize(1).itemGroup(CreativeTabs.BASE_GROUP));
    }

    public WandData readData(ItemStack itemStack) {
        WandData wandData = new WandData();

        CompoundTag tag = itemStack.getOrCreateTag();

        wandData.setHasType1(tag.getBoolean("hasType1"));
        wandData.setHasType2(tag.getBoolean("hasType2"));
        wandData.setHasType3(tag.getBoolean("hasType3"));

        return wandData;
    }

    public void writeData(ItemStack itemStack, WandData wandData) {
        CompoundTag tag = itemStack.getOrCreateTag();

        tag.putBoolean("hasType1", wandData.isHasType1());
        tag.putBoolean("hasType2", wandData.isHasType2());
        tag.putBoolean("hasType3", wandData.isHasType3());

        itemStack.setTag(tag);
    }

    public static class WandData {

        private boolean hasType1, hasType2, hasType3;

        public boolean isHasType1() {
            return hasType1;
        }

        public void setHasType1(boolean hasType1) {
            this.hasType1 = hasType1;
        }

        public boolean isHasType2() {
            return hasType2;
        }

        public void setHasType2(boolean hasType2) {
            this.hasType2 = hasType2;
        }

        public boolean isHasType3() {
            return hasType3;
        }

        public void setHasType3(boolean hasType3) {
            this.hasType3 = hasType3;
        }
    }
}
