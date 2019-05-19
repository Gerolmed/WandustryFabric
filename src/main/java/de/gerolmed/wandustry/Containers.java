package de.gerolmed.wandustry;

import de.gerolmed.wandustry.block.container.WandEditContainer;
import de.gerolmed.wandustry.block.screen.WandEditContainerScreen;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.util.Identifier;

public class Containers {

    public static final Identifier WAND_EDITOR = new Identifier(WandustryMod.MOD_ID, "wand_editor");

    public static void register() {
        ContainerProviderRegistry.INSTANCE.registerFactory(WAND_EDITOR, (syncId, identifier, player, buf) -> {
            return new WandEditContainer(syncId, player);
        });
    }

    public static void registerClient() {
        ScreenProviderRegistry.INSTANCE.registerFactory(WAND_EDITOR, WandEditContainerScreen::new);
    }
}
