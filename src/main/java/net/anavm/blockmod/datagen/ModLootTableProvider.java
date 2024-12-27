package net.anavm.blockmod.datagen;

import net.anavm.blockmod.datagen.loot.ModBlockLootTables;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class ModLootTableProvider {
    public static LootTableProvider create(PackOutput output) {
        // The second parameter (Set.of()) is for existing loot tables that might be replaced.
        // The List.of() portion tells the provider to generate block loot tables using ModBlockLootTables.
        return new LootTableProvider(
                output,
                Set.of(),
                List.of(new LootTableProvider.SubProviderEntry(
                        ModBlockLootTables::new,
                        LootContextParamSets.BLOCK
                ))
        );
    }
}
