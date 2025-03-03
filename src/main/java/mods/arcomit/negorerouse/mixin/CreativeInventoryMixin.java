package mods.arcomit.negorerouse.mixin;

import mods.arcomit.negorerouse.client.gui.FilterManager;
import mods.arcomit.negorerouse.client.gui.ToggleButton;
import mods.arcomit.negorerouse.registry.TabRegistry;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;

@Mixin(CreativeModeInventoryScreen.class)
public abstract class CreativeInventoryMixin {
    @Shadow
    private static CreativeModeTab selectedTab;

    @Inject(
            method = "selectTab(Lnet/minecraft/world/item/CreativeModeTab;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screens/inventory/CreativeModeInventoryScreen$ItemPickerMenu;scrollTo(F)V",
                    shift = At.Shift.BEFORE
            )
    )
    private void onSelectTab(CreativeModeTab tab, CallbackInfo ci) {
        if (FilterManager.isFiltered(this.selectedTab)) {
            NonNullList<ItemStack> items =((AbstractContainerScreen<CreativeModeInventoryScreen.ItemPickerMenu>) (Object) this)
                    .getMenu().items;
            //TODO: 暂时为清除所有物品，这里应该为替换选择的分类物品
            items.clear();
            ToggleButton.isRefresh = true;
        }
    }

}
