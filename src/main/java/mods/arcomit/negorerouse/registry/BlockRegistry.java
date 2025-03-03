package mods.arcomit.negorerouse.registry;

import mods.arcomit.negorerouse.block.DivinityBlock;
import mods.arcomit.negorerouse.item.DivinityBlockItem;
import mods.arcomit.negorerouse.utils.RegistryObjectGetter;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static mods.arcomit.negorerouse.NegoreRouseMod.MODID;
import static mods.arcomit.negorerouse.registry.ItemRegistry.ITEMS;

public class BlockRegistry {
    //方块注册表
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static void register(IEventBus modEventBus) {
        createDivinityBlocksForName("artemis");
        createDivinityBlocksForName("hercules");
        createDivinityBlocksForName("erebus");
        createDivinityBlocksForName("chronos");
        createDivinityBlocksForName("protogenoi");
        createDivinityBlocksForName("chaos");
        createDivinityBlocksForName("tartarus");
        BLOCKS.register(modEventBus);
    }

    //批量创建
    public static void createDivinityBlocksForName(String name) {
        //神魂聚合块
        registerBlockAndItem(name,"block");
        //赐福神魂块
        registerBlockAndItem(name,"glory");
    }

    public static void registerBlockAndItem(String name, String type){
        BLOCKS.register(name + "_" + type,
                () -> new DivinityBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK),name,type));
        ITEMS.register(name + "_" + type,
                () -> new DivinityBlockItem(RegistryObjectGetter.getBlockByName(MODID,name + "_" + type),
                        new Item.Properties().rarity(Rarity.EPIC), name,type));
    }
}
