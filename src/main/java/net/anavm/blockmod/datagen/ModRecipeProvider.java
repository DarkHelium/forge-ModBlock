package net.anavm.blockmod.datagen;

import net.anavm.blockmod.BlockMod; // Replace or adjust as needed
import net.anavm.blockmod.block.ModBlocks;
import net.anavm.blockmod.item.NewItem;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    private static final List<ItemLike> TOPAZ_SMELTABLES = List.of(
            NewItem.RAW_TOPAZ.get(),
            ModBlocks.TOPAZ_ORE.get(),
            ModBlocks.DEEPSLATE_TOPAZ_ORE.get()
    );

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput writer) {
        oreSmelting(writer, TOPAZ_SMELTABLES, RecipeCategory.MISC, NewItem.TOPAZ.get(), 0.25f, 200, "topaz");
        oreBlasting(writer, TOPAZ_SMELTABLES, RecipeCategory.MISC, NewItem.TOPAZ.get(), 0.25f, 100, "topaz");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TOPAZ_BLOCK.get())
                .pattern("TTT")
                .pattern("TTT")
                .pattern("TTT")
                .define('T', NewItem.TOPAZ.get())
                .unlockedBy("has_item", has(NewItem.TOPAZ.get()))
                .save(writer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, NewItem.TOPAZ.get(), 9)
                .requires(ModBlocks.TOPAZ_BLOCK.get())
                .unlockedBy("has_item", has(ModBlocks.TOPAZ_BLOCK.get()))
                .save(writer);
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput consumer,
                                                                       RecipeSerializer<T> serializer,
                                                                       ItemLike ingredient,
                                                                       RecipeCategory category,
                                                                       ItemLike result,
                                                                       float experience,
                                                                       int cookingTime,
                                                                       String group,
                                                                       String recipeName,
                                                                       AbstractCookingRecipe.Factory<T> factory) {
        // Create a recipe for the given ingredient
        SimpleCookingRecipeBuilder.generic(Ingredient.of(ingredient), category, result, experience, cookingTime, serializer, factory)
                .group(group)
                .unlockedBy("has_item", has(ingredient))
                .save(consumer, new ResourceLocation(BlockMod.MODID, getItemName(result) + recipeName + "_" + getItemName(ingredient)));
    }

}
