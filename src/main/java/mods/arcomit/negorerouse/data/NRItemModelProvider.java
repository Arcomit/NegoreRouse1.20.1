package mods.arcomit.negorerouse.data;

import mods.arcomit.negorerouse.NegoreRouseMod;
import mods.arcomit.negorerouse.registry.BlockRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import static mods.arcomit.negorerouse.NegoreRouseMod.MODID;

public class NRItemModelProvider extends ItemModelProvider {

    public NRItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), NegoreRouseMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        generateDivinityItemModel("artemis");
        generateDivinityItemModel("chaos");
        generateDivinityItemModel("chronos");
        generateDivinityItemModel("erebus");
        generateDivinityItemModel("hercules");
        generateDivinityItemModel("protogenoi");
        generateDivinityItemModel("tartarus");
    }

    private void generateDivinityItemModel(String name) {
        generateSimpleItemModel(name + "_fragment");
        generateSimpleItemModel(name + "_soul");
        generateSimpleItemModel(name + "_grain");
        generateSimpleItemModel(name + "_ingot");
    }
    
    private void generateSimpleItemModel(String itemName) {
        singleTexture(itemName, new ResourceLocation("item/generated"),
                "layer0", new ResourceLocation(NegoreRouseMod.MODID, "item/" + itemName));
    }
}
