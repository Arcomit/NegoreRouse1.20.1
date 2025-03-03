package mods.arcomit.negorerouse.registry;

import mods.arcomit.negorerouse.NegoreRouseMod;
import mods.arcomit.negorerouse.utils.RegistryObjectGetter;
import mods.flammpfeil.slashblade.SlashBladeCreativeGroup;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

import static mods.arcomit.negorerouse.NegoreRouseMod.MODID;
import static mods.arcomit.negorerouse.registry.BlockRegistry.BLOCKS;
import static mods.arcomit.negorerouse.registry.ItemRegistry.ITEMS;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TabRegistry {
    //创造物品栏注册表
    private static final DeferredRegister<CreativeModeTab> TBAS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NegoreRouseMod.MODID);

    public static final RegistryObject<CreativeModeTab> NEGOREROUSE_TAB = TBAS.register("negorerouse_tab", () -> CreativeModeTab.builder()
            // 设置所要展示的页的名称
            .title(Component.translatable("item_group." + MODID + ".tab"))
            // 设置页图标
            .icon(() -> new ItemStack(RegistryObjectGetter.getItemByName(MODID,"artemis_soul")))
            // 为物品栏页添加默认物品
            .displayItems((params, output) -> {
                //遍历注册表
                ITEMS.getEntries().forEach(registryObject -> {
                    Item item = registryObject.get();
                    output.accept(item);
                });
                BLOCKS.getEntries().forEach(registryObject -> {
                    Block block = registryObject.get();
                    output.accept(block);
                });
            })
            // 物品栏在拔刀剑创造物品栏之后
            .withTabsBefore(SlashBladeCreativeGroup.SLASHBLADE_GROUP.getKey())
            .build()
    );

    public static void register(IEventBus modEventBus) {
        TBAS.register(modEventBus);
    }
}
