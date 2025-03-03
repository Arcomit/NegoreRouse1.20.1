package mods.arcomit.negorerouse.event;

import com.lowdragmc.photon.client.fx.EntityEffect;
import com.lowdragmc.photon.client.fx.FX;
import com.lowdragmc.photon.client.fx.FXHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "negorerouse", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerTestEvent {
    public static boolean isInit = false;
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return; // 仅在 tick 开始时处理
        }

        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        if (player != null && !isInit){
            FX fx = FXHelper.getFX(new ResourceLocation("negorerouse:artemis"));
            // bind it to an entity
            new EntityEffect(fx, player.level(), player).start();
            isInit = true;
        }
    }
}
