package de.gerolmed.wandustry.block.render;

import com.mojang.blaze3d.platform.GlStateManager;
import de.gerolmed.wandustry.block.entity.EnchanterBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GuiLighting;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnchanterBlockEntityRenderer extends BlockEntityRenderer<EnchanterBlockEntity> {

    private static final Logger LOGGER = LogManager.getLogger(EnchanterBlockEntity.class);

    @Override
    public void render(EnchanterBlockEntity enchanter, double renderX, double renderY, double renderZ, float float_1, int int_1) {
        super.render(enchanter, renderX, renderY, renderZ, float_1, int_1);
        GlStateManager.pushMatrix();

        BlockPos blockPos = enchanter.getPos();
        Vector3f vector3f = new Vector3f(blockPos.getX(),blockPos.getY(),blockPos.getZ());
        vector3f.subtract(new Vector3f((float) renderX, (float) renderY, (float) renderZ));

        GuiLighting.enable();

        GlStateManager.enableLighting();
        GlStateManager.disableRescaleNormal();

        int itemNum = 0;
        RenderPosition[] positions = getRenders(enchanter.getItemStacks().size());
        for(ItemStack itemStack : enchanter.getItemStacks()) {

            if(positions.length <= itemNum)
                break;

            RenderPosition position = positions[itemNum];
            GlStateManager.translated(vector3f.x() + .5 + position.offsetX, vector3f.y() + 0.02 + position.offsetY, vector3f.z() + .5 + position.offsetZ);
            GlStateManager.rotated(position.rot, 0,1,0);
            GlStateManager.scaled(0.7, 0.7, 0.7);
            MinecraftClient.getInstance().getItemRenderer().renderItem(itemStack, ModelTransformation.Type.GROUND);
            itemNum++;
        }

        GlStateManager.popMatrix();

    }

    public RenderPosition[] getRenders(int total) {
        switch (total) {
            case 1: return new RenderPosition[] {new RenderPosition(0,0,0, 45)};
            case 2: return new RenderPosition[] {
                    new RenderPosition(0.3,0,0.3, 45),
                    new RenderPosition(-0.3,0,-0.3, 45)
            };
            case 3: return new RenderPosition[] {
                    new RenderPosition(-0.5,0,-0.433, 45),
                    new RenderPosition(0.5,0,-0.433, 45),
                    new RenderPosition(0,0,0.433, 45)
            };
            default: return new RenderPosition[0];
        }
    }

    private class RenderPosition {
        private final double offsetX, offsetY, offsetZ;
        private final double rot;

        private RenderPosition(double offsetX, double offsetY, double offsetZ, double rot) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.offsetZ = offsetZ;
            this.rot = rot;
        }
    }
}
