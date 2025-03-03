package mods.arcomit.negorerouse.block;

import net.minecraft.world.level.block.Block;

public class DivinityBlock extends Block{

    private String origin;//本源
    private String type;//类型

    public DivinityBlock(Properties properties, String origin, String type) {
        super(properties);
        this.origin = origin;
        this.type = type;
    }
}
