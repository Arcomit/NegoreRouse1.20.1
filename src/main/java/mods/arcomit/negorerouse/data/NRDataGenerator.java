package mods.arcomit.negorerouse.data;

import mods.arcomit.negorerouse.NegoreRouseMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NegoreRouseMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NRDataGenerator {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(event.includeClient(), new NRItemModelProvider(generator, existingFileHelper));
        generator.addProvider(event.includeClient(), new NRBlockStateProvider(generator, existingFileHelper));

    }
}
