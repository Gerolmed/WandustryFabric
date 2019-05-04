package de.gerolmed.wandustry.block.render;

import com.mojang.blaze3d.platform.GlStateManager;
import de.gerolmed.wandustry.WandustryMod;
import de.gerolmed.wandustry.block.entity.EnchanterBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.GuiLighting;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class EnchanterBlockEntityRenderer extends BlockEntityRenderer<EnchanterBlockEntity> {

    private static final Logger LOGGER = LogManager.getLogger(EnchanterBlockEntity.class);

    @Override
    public void render(EnchanterBlockEntity enchanter, double renderX, double renderY, double renderZ, float float_1, int int_1) {
        super.render(enchanter, renderX, renderY, renderZ, float_1, int_1);

        int itemNum = 0;
        RenderPosition[] positions = getRenders(enchanter.getItemStacks().size());
        for(ItemStack itemStack : enchanter.getItemStacks()) {

            if(positions.length <= itemNum)
                break;

            GlStateManager.pushMatrix();

            GuiLighting.enable();

            GlStateManager.enableLighting();
            GlStateManager.disableRescaleNormal();

            RenderPosition position = positions[itemNum];

            GlStateManager.translated(renderX + .5 + position.offsetX, renderY + 0.02 + position.offsetY, renderZ + .5 + position.offsetZ);
            GlStateManager.rotated(position.rot, 0,1,0);
            GlStateManager.scaled(0.7, 0.7, 0.7);

            MinecraftClient.getInstance().getItemRenderer().renderItem(itemStack, ModelTransformation.Type.GROUND);
            GlStateManager.popMatrix();

            itemNum++;
        }

        if(enchanter.hasContents()) {
            // Render hover information
            HitResult hitResult = MinecraftClient.getInstance().hitResult;
            if(hitResult instanceof BlockHitResult) {
                BlockHitResult blockHit = (BlockHitResult) hitResult;
                if(blockHit.getBlockPos().equals(enchanter.getPos())) {

                    float yaw = 0.0F - this.renderManager.cameraEntity.getYaw();
                    float pitch = this.renderManager.cameraEntity.getPitch();

                    GlStateManager.pushMatrix();
                    GlStateManager.disableDepthTest();
                    GlStateManager.disableLighting();

                    GlStateManager.translated(renderX+.5f, renderY+.5f, renderZ+.5f);

                    GlStateManager.rotatef(yaw, 0.0F, 1.0F, 0.0F);
                    GlStateManager.rotatef(pitch, 1.0F, 0.0F, 0.0F);
                    GlStateManager.scaled(0.7,0.7,0.7);
                    GlStateManager.translated(-.5f, -.5f, -.5f);

                    {
                        GlStateManager.pushMatrix();
                        Tessellator tessellator = Tessellator.getInstance();
                        BufferBuilder buf = tessellator.getBufferBuilder();

                        buf.begin(GL_QUADS, VertexFormats.POSITION_UV);

                        //Render image here
                        buf.vertex(0,0,0).texture(0,0).next();
                        buf.vertex(0,1,0).texture(0,1).next();
                        buf.vertex(1,1,0).texture(1,1).next();
                        buf.vertex(1,0,0).texture(1,0).next();
                        MinecraftClient.getInstance().getTextureManager().bindTexture(new Identifier(WandustryMod.MOD_ID, "textures/block/ui/enchanter_enchant.png"));

                        tessellator.draw();
                        GlStateManager.popMatrix();
                    }

                    //Display progress
                    if(enchanter.isEnchanting()){
                        GlStateManager.pushMatrix();
                        Tessellator tessellator = Tessellator.getInstance();
                        BufferBuilder buf = tessellator.getBufferBuilder();

                        buf.begin(GL_QUADS, VertexFormats.POSITION_UV);

                        //Render image here
                        buf.vertex(0,0,0).texture(0,0).next();
                        buf.vertex(0,enchanter.enchantProgress,0).texture(0,enchanter.enchantProgress).next();
                        buf.vertex(1,enchanter.enchantProgress,0).texture(1,enchanter.enchantProgress).next();
                        buf.vertex(1,0,0).texture(1,0).next();
                        MinecraftClient.getInstance().getTextureManager().bindTexture(new Identifier(WandustryMod.MOD_ID, "textures/block/ui/enchanter_enchant_progress.png"));

                        tessellator.draw();
                        GlStateManager.popMatrix();
                    }

                    //Render result
                    if(enchanter.isEnchanting()){
                        if(enchanter.hasPower()) {
                            GlStateManager.pushMatrix();

                            GuiLighting.enable();

                            GlStateManager.enableLighting();
                            GlStateManager.disableRescaleNormal();

                            GlStateManager.translated(0.5, 0.35, 0);
                            //GlStateManager.rotated(position.rot, 0,1,0);
                            GlStateManager.scaled(0.8, 0.8, 0.8);

                            MinecraftClient.getInstance().getItemRenderer().renderItem(enchanter.getRecipe().getResult()[0], ModelTransformation.Type.GROUND);
                            GlStateManager.popMatrix();
                        } else {
                            GlStateManager.pushMatrix();
                            Tessellator tessellator = Tessellator.getInstance();
                            BufferBuilder buf = tessellator.getBufferBuilder();

                            buf.begin(GL_QUADS, VertexFormats.POSITION_UV);

                            //Render image here
                            buf.vertex(0,0,0).texture(0,0).next();
                            buf.vertex(0,1,0).texture(0,1).next();
                            buf.vertex(1,1,0).texture(1,1).next();
                            buf.vertex(1,0,0).texture(1,0).next();
                            MinecraftClient.getInstance().getTextureManager().bindTexture(new Identifier(WandustryMod.MOD_ID, "textures/block/ui/enchanter_enchant_missing_power.png"));

                            tessellator.draw();
                            GlStateManager.popMatrix();
                        }
                    } else {
                        // Show x to mark that no recipe is found
                        GlStateManager.pushMatrix();
                        Tessellator tessellator = Tessellator.getInstance();
                        BufferBuilder buf = tessellator.getBufferBuilder();

                        buf.begin(GL_QUADS, VertexFormats.POSITION_UV);

                        //Render image here
                        buf.vertex(0,0,0).texture(0,0).next();
                        buf.vertex(0,1,0).texture(0,1).next();
                        buf.vertex(1,1,0).texture(1,1).next();
                        buf.vertex(1,0,0).texture(1,0).next();
                        MinecraftClient.getInstance().getTextureManager().bindTexture(new Identifier(WandustryMod.MOD_ID, "textures/block/ui/enchanter_enchant_missing_recipe.png"));

                        tessellator.draw();
                        GlStateManager.popMatrix();
                    }

                    GlStateManager.enableLighting();
                    GlStateManager.enableDepthTest();
                    GlStateManager.popMatrix();
                }
            }
        }
    }

    private RenderPosition[] getRenders(int total) {
        switch (total) {
            case 1: return new RenderPosition[] {new RenderPosition(0,0,0, 45)};
            case 2: return new RenderPosition[] {
                    new RenderPosition(0.3,0,0.3, 45),
                    new RenderPosition(-0.3,0,-0.3, 45)
            };
            case 3: return new RenderPosition[] {
                    new RenderPosition(-0.5*0.6,0,-0.433*0.6, 45),
                    new RenderPosition(0.5*0.6,0,-0.433*0.6, 45),
                    new RenderPosition(0,0,0.433*0.6, 0)
            };
            case 4: return new RenderPosition[] {
                    new RenderPosition(0.3,0,-0.3, 45),
                    new RenderPosition(-0.3,0,-0.3, 45),
                    new RenderPosition(-0.3,0,0.3, 45),
                    new RenderPosition(0.3,0,0.3, 45)
            };
            case 5: return new RenderPosition[] {
                    new RenderPosition(0,0,1*0.3, 0),// TOP
                    new RenderPosition(-0.95*0.3,0,0.31*0.3, 35),// UP LEFT
                    new RenderPosition(-0.59*0.3,0,-0.81*0.3, 45),// DOWN LEFT
                    new RenderPosition(0.59*0.3,0,-0.81*0.3, 45),//DOWN RIGHT
                    new RenderPosition(0.95*0.3,0,0.31*0.3, 55)//UP RIGHT
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
