package mods.arcomit.negorerouse;

import com.mojang.logging.LogUtils;
import mods.arcomit.negorerouse.client.gui.FilterManager;
import mods.arcomit.negorerouse.registry.BlockRegistry;
import mods.arcomit.negorerouse.registry.ItemRegistry;
import mods.arcomit.negorerouse.registry.TabRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(NegoreRouseMod.MODID)
public class NegoreRouseMod
{
    public static final String MODID = "negorerouse";
    private static final Logger LOGGER = LogUtils.getLogger();

    public NegoreRouseMod()
    {
        LOGGER.info("NegoreRouse is loaded!");
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemRegistry.register(modEventBus);
        BlockRegistry.register(modEventBus);
        TabRegistry.register(modEventBus);

    }


}
