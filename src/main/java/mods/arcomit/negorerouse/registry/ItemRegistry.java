package mods.arcomit.negorerouse.registry;

import mods.arcomit.negorerouse.NegoreRouseMod;
import mods.arcomit.negorerouse.item.DivinityItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static mods.arcomit.negorerouse.NegoreRouseMod.MODID;

public class ItemRegistry {
    //物品注册表
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);


    public static void register(IEventBus modEventBus) {
        createDivinityItemsForName("artemis");
        createDivinityItemsForName("hercules");
        createDivinityItemsForName("erebus");
        createDivinityItemsForName("chronos");
        createDivinityItemsForName("protogenoi");
        createDivinityItemsForName("chaos");
        createDivinityItemsForName("tartarus");
        ITEMS.register(modEventBus);
    }

    //批量创建
    public static void createDivinityItemsForName(String name) {
        //神之碎片
        ITEMS.register(name + "_fragment",() ->
                new DivinityItem(new Item.Properties().rarity(Rarity.EPIC), name,"fragment"));
        //神残魂
        ITEMS.register(name + "_soul",() ->
                new DivinityItem(new Item.Properties().rarity(Rarity.EPIC), name,"soul"));
        //神魂凝粒
        ITEMS.register(name + "_grain",() ->
                new DivinityItem(new Item.Properties().rarity(Rarity.EPIC), name,"grain"));
        //神魂凝锭
        ITEMS.register(name + "_ingot",() ->
                new DivinityItem(new Item.Properties().rarity(Rarity.EPIC), name,"ingot"));
    }


}
