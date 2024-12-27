package net.anavm.blockmod.item;

import net.anavm.blockmod.BlockMod;
import net.anavm.blockmod.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier TOPAZ = TierSortingRegistry.registerTier(
            new ForgeTier(2, 800, 6f, 4f, 16,
                    ModTags.Blocks.NEEDS_TOPAZ_TOOL, ()-> Ingredient.of(NewItem.TOPAZ.get())),
                    new ResourceLocation(BlockMod.MODID, "topaz"), List.of(Tiers.DIAMOND), List.of());
}
