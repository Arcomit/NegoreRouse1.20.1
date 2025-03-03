package mods.arcomit.negorerouse.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class DivinityBlockItem extends BlockItem {

    private String origin;//本源
    private String type;//类型

    public DivinityBlockItem(Block block, Properties properties, String origin, String type) {
        super(block, properties);
        this.origin = origin;
        this.type = type;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Component getName(ItemStack p_41458_) {
        return Component.translatable("item_type.negorerouse." + type);
        //名称统一
    }

    //描述
    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.translatable("tip.negorerouse." + this.origin + ".divinity_item")
                .withStyle(ChatFormatting.GRAY));
        //本源介绍

        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }
}
