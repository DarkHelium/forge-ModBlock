package net.anavm.blockmod.datagen;

import net.anavm.blockmod.BlockMod;
import net.anavm.blockmod.item.NewItem;
import net.anavm.blockmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_,
                               CompletableFuture<HolderLookup.Provider> p_275729_,
                               CompletableFuture<TagLookup<Block>> p_275322_,
                               @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, BlockMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // Example: Add Topaz item to a tag named "blockmod:topaz_items"

        // If you want to use a Forge convention like "forge:gems/topaz",
        // just replace the ResourceLocation in ModTags with "forge:gems/topaz".
    }
}
