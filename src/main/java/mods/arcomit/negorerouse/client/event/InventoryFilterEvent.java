package mods.arcomit.negorerouse.client.event;

import mods.arcomit.negorerouse.client.gui.FilterManager;
import mods.arcomit.negorerouse.client.gui.SwitchButton;
import mods.arcomit.negorerouse.client.gui.ToggleButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static mods.arcomit.negorerouse.utils.RegistryObjectGetter.getItemByName;

@Mod.EventBusSubscriber(modid = "negorerouse", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class InventoryFilterEvent {

    @SubscribeEvent
    public static void onGuiInit(ScreenEvent.Init.Post event) {
        if(!(event.getScreen() instanceof CreativeModeInventoryScreen screen)) return;
        FilterManager.init();
        // 创建按钮但初始隐藏
        ItemStack icon = new ItemStack(getItemByName("negorerouse","artemis_soul"));
        for (int i = 0; i <= 3; i++) {
            AbstractWidget button = new ToggleButton(
                    screen.getGuiLeft() - 22, // 离创造物品栏左侧2格宽
                    screen.getGuiTop() + 9 + (i * 29),
                    20, 20,
                    i, b -> {
                        if (((ToggleButton) b).isPressed()) {
                            onButtonPress();
                        } else {
                            onButtonUnpressed();
                        }
                    }
            );
            event.addListener(button);
        }

        AbstractWidget upButton = new SwitchButton(
                screen.getGuiLeft() - 13,// 离创造物品栏左侧2格宽
                screen.getGuiTop() + 9 + (3 * 29) + 21,// 过滤类型按钮下方一格
                false,
                b -> {
                    onButtonUp();
                }
        );
        AbstractWidget downButton = new SwitchButton(
                screen.getGuiLeft() - 13,// 离创造物品栏左侧2格宽
                screen.getGuiTop() + 9 + (3 * 29) + 31,
                true,
                b -> {
                    onButtonDown();
                }
        );

        event.addListener(upButton);
        event.addListener(downButton);
    }

    // 按钮点击处理
    private static void onButtonUnpressed() {
        if (Minecraft.getInstance().player != null) {
            // 使用命令包发送
            Minecraft.getInstance().player.connection.sendChat("我玩原神！");
        }
    }

    // 按钮点击处理
    private static void onButtonPress() {
        if (Minecraft.getInstance().player != null) {
            // 使用命令包发送
            Minecraft.getInstance().player.connection.sendCommand("tp ~ 200 ~");
        }
    }

    private static void onButtonDown() {
        if (Minecraft.getInstance().player != null) {
            // 使用命令包发送
            Minecraft.getInstance().player.connection.sendChat("坠机！");
        }
    }

    private static void onButtonUp() {
        if (Minecraft.getInstance().player != null) {
            // 使用命令包发送
            Minecraft.getInstance().player.connection.sendChat("起飞！");
        }
    }
}
