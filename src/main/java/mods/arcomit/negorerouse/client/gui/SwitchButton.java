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

public class SwitchButton extends Button {
    public static final ResourceLocation RESOURCE_PACKS_LOCATION = new ResourceLocation("textures/gui/resource_packs.png");
    private final boolean isDown;

    public SwitchButton(
            int x, int y, boolean isDown,
            OnPress onPress) {
        super(x, y, 11, 10, Component.empty(), onPress, DEFAULT_NARRATION);
        this.isDown = isDown;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (!FilterManager.isFiltered(ICreativeInventoryMixin.getSelectedTab())){
            this.visible=false;
            return;
        }
        this.visible=true;
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        Minecraft minecraft = Minecraft.getInstance();
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        int u= isDown ? 83 : 115;;
        int v = isDown ? (this.isHovered() ? 52 : 20) : (this.isHovered() ? 36 : 4);

        guiGraphics.blit(RESOURCE_PACKS_LOCATION, this.getX(), this.getY(), u, v, 11, 10, 256, 256);
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        int i = getFGColor();
        this.renderString(guiGraphics, minecraft.font, i | Mth.ceil(this.alpha * 255.0F) << 24);
    }
}
