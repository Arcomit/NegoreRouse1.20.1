package mods.arcomit.negorerouse.data;

import mods.arcomit.negorerouse.registry.BlockRegistry;
import mods.arcomit.negorerouse.utils.RegistryObjectGetter;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import static mods.arcomit.negorerouse.NegoreRouseMod.MODID;

public class NRBlockStateProvider extends BlockStateProvider {
    public NRBlockStateProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
        super(gen.getPackOutput(), MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        generateDivinityItemModel("artemis");
        generateDivinityItemModel("chaos");
        generateDivinityItemModel("chronos");
        generateDivinityItemModel("erebus");
        generateDivinityItemModel("hercules");
        generateDivinityItemModel("protogenoi");
        generateDivinityItemModel("tartarus");
    }

    private void generateDivinityItemModel(String name) {
        simpleBlock(name + "_block");
        simpleBlock(name + "_glory");
    }

    private void simpleBlock(String name) {
        super.simpleBlockWithItem(RegistryObjectGetter.getBlockByName(MODID,name), models().cubeAll(name,
                modLoc("block/" + name)));
    }
}
