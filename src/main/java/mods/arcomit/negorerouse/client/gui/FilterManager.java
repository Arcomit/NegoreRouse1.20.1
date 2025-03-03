package mods.arcomit.negorerouse.client.gui;

import mods.arcomit.negorerouse.NegoreRouseMod;
import mods.arcomit.negorerouse.registry.TabRegistry;
import mods.arcomit.negorerouse.utils.RegistryObjectGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.*;

import static mods.arcomit.negorerouse.NegoreRouseMod.MODID;

public class FilterManager {
    private static boolean isInit = false;
    private static final Map<CreativeModeTab, List<Filter>> TAB_TO_FILTERS = new HashMap<>();

    public static void init() {
        if (isInit) return;
        Filter test = new Filter("test", new ItemStack(RegistryObjectGetter.getItemByName(MODID,"artemis_soul")));
        addTab(TabRegistry.NEGOREROUSE_TAB.get(), test);
        addTab(CreativeModeTabs.BUILDING_BLOCKS);
        isInit = true;
    }

    /**
     * 添加需要分类筛选功能的标签页（同时初始化其过滤器列表）
     * @param tab 目标标签页
     * @param filters 初始化的过滤器列表
     */
    public static void addTab(@Nonnull CreativeModeTab tab, @Nonnull Filter... filters) {
        List<Filter> filterList = TAB_TO_FILTERS.computeIfAbsent(tab, k -> new ArrayList<>());
        Collections.addAll(filterList, filters);
    }

    /**
     * 添加需要分类筛选功能的标签页（原版标签页）
     * @param tabKey 原版标签页
     * @param filters 初始化的过滤器列表
     */
    public static void addTab(@Nonnull ResourceKey<CreativeModeTab> tabKey, @Nonnull Filter... filters) {
        List<Filter> filterList = TAB_TO_FILTERS.computeIfAbsent(BuiltInRegistries.CREATIVE_MODE_TAB.get(tabKey), k -> new ArrayList<>());
        Collections.addAll(filterList, filters);
    }

    /**
     * 检查标签页是否需要分类筛选
     */
    public static boolean isFiltered(@Nonnull CreativeModeTab tab) {
        return TAB_TO_FILTERS.containsKey(tab);
    }

    /**
     * 为标签页添加一个过滤器
     * @param tab 目标标签页
     * @param filter 过滤器对象
     */
    public static void addFilter(@Nonnull CreativeModeTab tab, @Nonnull Filter filter) {
        Objects.requireNonNull(tab, "CreativeModeTab 不能为 null");
        Objects.requireNonNull(filter, "Filter 不能为 null");
        TAB_TO_FILTERS.computeIfAbsent(tab, k -> new ArrayList<>()).add(filter);
    }

    /**
     * 获取标签页的过滤器列表
     */
    @Nonnull
    public static List<Filter> getFiltersForTab(@Nonnull CreativeModeTab tab) {
        return Collections.unmodifiableList(
                TAB_TO_FILTERS.getOrDefault(tab, Collections.emptyList())
        );
    }

    /**
     * 清空所有标签页的过滤器（用于重载或测试）
     */
    public static void clearAll() {
        TAB_TO_FILTERS.clear();
    }
}