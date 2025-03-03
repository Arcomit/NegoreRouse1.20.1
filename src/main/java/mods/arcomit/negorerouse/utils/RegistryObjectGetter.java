package mods.arcomit.negorerouse.utils;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryObjectGetter {
    //根据注册名获取物品
    public static Item getItemByName(String modid, String itemName) {
        ResourceLocation resourceLocation = new ResourceLocation(modid, itemName);
        return ForgeRegistries.ITEMS.getValue(resourceLocation);
    }

    //根据注册名获取方块
    public static Block getBlockByName(String modid, String itemName) {
        ResourceLocation resourceLocation = new ResourceLocation(modid, itemName);
        return ForgeRegistries.BLOCKS.getValue(resourceLocation);
    }

}
