package de.gerolmed.wandustry;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class CreativeTabs {
    public static final ItemGroup BASE_GROUP = FabricItemGroupBuilder.create(
            new Identifier(WandustryMod.MOD_ID, "base"))
            .icon(() -> new ItemStack(Items.ITEM_TEST))
            .build();
}
