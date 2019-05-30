package de.gerolmed.wandustry.block.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import de.gerolmed.wandustry.WandustryMod;
import de.gerolmed.wandustry.block.container.WandEditContainer;
import net.minecraft.client.gui.screen.ingame.AbstractContainerScreen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.Identifier;

public class WandEditContainerScreen extends AbstractContainerScreen<WandEditContainer> {

    private static final Identifier BG_TEXTURE = new Identifier(WandustryMod.MOD_ID, "textures/gui/container/wand_editor.png");

    public WandEditContainerScreen(WandEditContainer container) {
        super(container, container.playerInventory, new TextComponent("Wand Editor"));
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void drawBackground(float v, int i, int i1) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bindTexture(BG_TEXTURE);
        this.blit(left, top, 0, 0, 200, 166);
    }

    @Override
    public void render(int int_1, int int_2, float float_1) {
        super.render(int_1, int_2, float_1);
        this.drawMouseoverTooltip(int_1, int_2);
    }
}