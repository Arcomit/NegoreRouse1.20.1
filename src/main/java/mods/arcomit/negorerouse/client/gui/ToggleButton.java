package mods.arcomit.negorerouse.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import mods.arcomit.negorerouse.mixin.ICreativeInventoryMixin;
import mods.arcomit.negorerouse.registry.TabRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ToggleButton extends Button {
    private final int ID;
    private Filter currentFilter;
    public static boolean isRefresh = true;
    private boolean isPressed = false; // 按钮状态
    public static final ResourceLocation SLIDER_LOCATION = new ResourceLocation("textures/gui/slider.png");

    public ToggleButton(
            int x, int y, int width, int height,
            int id, OnPress onPress) {
        super(x, y, width, height, Component.empty(), onPress, DEFAULT_NARRATION);
        this.ID = id;
    }

    @Override
    public void onPress() {
        this.isPressed = !this.isPressed; // 切换状态
        super.onPress();
    }

    public boolean isPressed() {
        return this.isPressed;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (FilterManager.isFiltered(ICreativeInventoryMixin.getSelectedTab())){
            if (isRefresh) {
                refresh();
            }
            if (currentFilter != null){
                this.visible = true;
                super.render(guiGraphics, mouseX, mouseY, partialTick);
                return;
            }
        }
        this.visible = false;
    }

    public void refresh(){
        this.currentFilter = safeGet(FilterManager.getFiltersForTab(ICreativeInventoryMixin.getSelectedTab()),this.ID);
        isRefresh = false;
    }

    public static <T> T safeGet(List<T> list, int index) {
        // 如果列表为空、索引越界，返回 null
        if (list == null || index < 0 || index >= list.size()) {
            return null;
        }
        return list.get(index);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        Minecraft minecraft = Minecraft.getInstance();
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        guiGraphics.blitNineSliced(SLIDER_LOCATION, this.getX(), this.getY(), this.getWidth(), this.getHeight(), 20, 4, 200, 20, 0, this.getTextureY());
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        int i = getFGColor();
        this.renderString(guiGraphics, minecraft.font, i | Mth.ceil(this.alpha * 255.0F) << 24);

        renderItemIcon(guiGraphics);

        // 鼠标悬停时渲染提示
        if (this.isHovered()) {
            guiGraphics.renderTooltip(Minecraft.getInstance().font, Component.translatable("filter.negorerouse." + currentFilter.getName()), mouseX, mouseY);
        }
    }

    //0按下，1按下-悬停，2未按下，3未按下-悬停
    private int getTextureY() {
        int i = 2;
        if (this.isPressed) {
            i = 0;
            if (this.isHovered()) {
                i = 1;
            }
        } else if (this.isHovered()) {
            i = 3;
        }

        return i * 20;
    }

    private void renderItemIcon(GuiGraphics guiGraphics) {
        int iconSize = 16; // 图标大小
        int x = (this.getX() + (this.width - iconSize) / 2);
        int y = (this.getY() + (this.height - iconSize) / 2) - 1;

        //渲染阴影
        guiGraphics.setColor(0.0F, 0.0F, 0.0F, 0.5F); // 黑色，50%透明度
        guiGraphics.renderItem(this.currentFilter.getIcon(), x + 1, y + 1);
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F); // 恢复颜色

        //渲染物品图标
        guiGraphics.renderItem(this.currentFilter.getIcon(), x, y);
    }
}
