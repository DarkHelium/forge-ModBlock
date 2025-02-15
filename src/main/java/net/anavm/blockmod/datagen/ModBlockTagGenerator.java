package net.anavm.blockmod.datagen;

import net.anavm.blockmod.BlockMod;
import net.anavm.blockmod.block.ModBlocks;
import net.anavm.blockmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                @Nullable ExistingFileHelper existingFileHelper){
        super(output, lookupProvider, BlockMod.MODID, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider){
        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES).add(ModBlocks.TOPAZ_ORE.get()).addTag(
                Tags.Blocks.ORES);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.TOPAZ_BLOCK.get(),
                        ModBlocks.TOPAZ_ORE.get(), ModBlocks.DEEPSLATE_TOPAZ_ORE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.TOPAZ_BLOCK.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.TOPAZ_ORE.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.DEEPSLATE_TOPAZ_ORE.get());
    }
}
