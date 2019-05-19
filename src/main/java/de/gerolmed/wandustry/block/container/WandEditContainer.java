package de.gerolmed.wandustry.block.container;

import net.minecraft.container.Container;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;

public class WandEditContainer extends Container {
    public final PlayerInventory playerInventory;
    BlockPos pos;

    public WandEditContainer(int syncId, PlayerEntity playerEntity) {
        super(null, syncId);
        this.playerInventory = playerEntity.inventory;
        drawPlayerInventory();
    }

    private void drawPlayerInventory() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int j = 0; j < 9; ++j) {
            this.addSlot(new Slot(playerInventory, j, 8 + j * 18, 142));
        }
    }

    @Override
    public boolean canUse(PlayerEntity playerEntity) {
        return true;
    }
}
