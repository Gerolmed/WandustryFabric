package de.gerolmed.wandustry.block.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import de.gerolmed.wandustry.block.container.WandEditContainer;
import net.minecraft.client.gui.screen.ingame.AbstractContainerScreen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.Identifier;

public class WandEditContainerScreen extends AbstractContainerScreen<WandEditContainer> {

    private static final Identifier BG_TEXTURE = new Identifier("textures/gui/container/horse.png");

    public WandEditContainerScreen(WandEditContainer container) {
        super(container, container.playerInventory, new TextComponent("Wand Editor"));
    }

    @Override
    protected void drawBackground(float v, int i, int i1) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bindTexture(BG_TEXTURE);
        this.blit(left, top, 0, 0, 200, 166);
    }
}