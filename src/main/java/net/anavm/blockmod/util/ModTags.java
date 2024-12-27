package net.anavm.blockmod.util;
import net.anavm.blockmod.BlockMod;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static net.minecraft.tags.TagEntry.tag;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> METAL_DETECTOR_VALUABLES = tag("metal_detector_valuables");
        public static final TagKey<Block> NEEDS_TOPAZ_TOOL = tag("needs_topaz_tool");
        private static TagKey<Block> tag(String message){
            return BlockTags.create(new ResourceLocation(BlockMod.MODID, message));
        }
    }
    public static class Items{
        private static TagKey<Item> tag(String message){
            return ItemTags.create(new ResourceLocation(BlockMod.MODID, message));
        }
    }
}
