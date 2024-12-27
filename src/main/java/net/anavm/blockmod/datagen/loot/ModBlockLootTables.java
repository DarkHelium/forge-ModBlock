package net.anavm.blockmod.datagen.loot;

import net.anavm.blockmod.block.ModBlocks;
import net.anavm.blockmod.item.NewItem;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    /**
     * Note: For Minecraft 1.20.x, we pass a Set of block tags that require Silk Touch or something similar.
     *       If you don't have special flags to track, you can pass an empty set as shown.
     */
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        // 1) Blocks that simply drop themselves
        this.dropSelf(ModBlocks.TOPAZ_BLOCK.get());

        // 2) Ore blocks that drop multiple raw items if not mined with Silk Touch
        this.add(ModBlocks.TOPAZ_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.TOPAZ_ORE.get(), NewItem.RAW_TOPAZ.get()));
        this.add(ModBlocks.DEEPSLATE_TOPAZ_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.DEEPSLATE_TOPAZ_ORE.get(), NewItem.RAW_TOPAZ.get()));

        // If you have more custom blocks or variants, repeat the pattern above:
        // this.dropSelf(ModBlocks.SOME_OTHER_BLOCK.get());
        // this.add(ModBlocks.SOME_OTHER_ORE.get(),
        //         block -> createCopperLikeOreDrops(ModBlocks.SOME_OTHER_ORE.get(), NewItem.RAW_TOPAZ.get()));
    }

    /**
     * "Copper-like" ore drops means:
     * - If mined without Silk Touch, drop 2 to 5 items of RAW_TOPAZ (adjust as you see fit).
     * - Apply Fortune (BLOCK_FORTUNE) to increase the quantity.
     * - If mined WITH Silk Touch, drop the block itself (handled by createSilkTouchDispatchTable).
     */
    protected LootTable.Builder createCopperLikeOreDrops(Block block, Item rawItem) {
        return createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block,
                        LootItem.lootTableItem(rawItem)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                )
        );
    }

    /**
     * Tells the loot generator which blocks exist in our mod.
     * So it can generate valid loot tables for each known block.
     */
    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        // If you register blocks via a DeferredRegister<Block>, you can map over the entries:
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
