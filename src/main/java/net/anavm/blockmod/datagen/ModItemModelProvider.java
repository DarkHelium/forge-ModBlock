package net.anavm.blockmod.datagen;

import net.anavm.blockmod.BlockMod;
import net.anavm.blockmod.item.NewItem;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper fileHelper){
        super(output, BlockMod.MODID, fileHelper);
    }
    @Override
    protected void registerModels(){
        simpleItem(NewItem.TOPAZ);
        simpleItem(NewItem.RAW_TOPAZ);
    }
    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture(
                        "layer0", new ResourceLocation(BlockMod.MODID, "item/"
                        + item.getId().getPath())
        );
    }
}
