package mods.arcomit.negorerouse.client.gui;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackLinkedSet;

import java.util.Collection;

public class Filter {
    private final String name;
    private final ItemStack icon;//图标
    private Collection<ItemStack> displayItems = ItemStackLinkedSet.createTypeAndTagSet();

    public Filter(String name,ItemStack icon) {
        this.name = name;
        this.icon = icon;
    }

    //为当前过滤器添加一个物品
    public void accept(ItemStack stack) {
        if (stack.getCount() != 1) {
            throw new IllegalArgumentException("Stack size must be exactly 1");
        } else {
            boolean flag = this.displayItems.contains(stack);
            if (flag) {
                throw new IllegalStateException("Accidentally adding the same item stack twice " + stack.getDisplayName().getString());
            } else {
                this.displayItems.add(stack);
            }
        }
    }

    public String getName() {
        return this.name;
    }

    public ItemStack getIcon() {
        return this.icon;
    }

    public Collection<ItemStack> getDisplayItems() {
        return this.displayItems;
    }
}
