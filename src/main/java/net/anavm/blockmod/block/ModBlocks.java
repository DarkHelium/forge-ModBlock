package net.anavm.blockmod.block;

import net.anavm.blockmod.BlockMod;
import net.anavm.blockmod.item.NewItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.StoneButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BlockMod.MODID);

    // ---------------------------------------------------------------
    //  Base Blocks
    // ---------------------------------------------------------------
    public static final RegistryObject<Block> TOPAZ_BLOCK = registerBlock("topaz_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(2.2f)));

    public static final RegistryObject<Block> TOPAZ_ORE = registerBlock("topaz_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(3, 6),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                            .strength(3.5f)
                            .requiresCorrectToolForDrops()
            ));

    public static final RegistryObject<Block> DEEPSLATE_TOPAZ_ORE = registerBlock("deepslate_topaz_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 6),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                            .strength(4.5f)
                            .requiresCorrectToolForDrops()
            ));

    // ---------------------------------------------------------------
    //  New Topaz Variants
    // ---------------------------------------------------------------
    public static final RegistryObject<Block> TOPAZ_STAIRS = registerBlock("topaz_stairs",
            () -> new StairBlock(
                    // Must be a Supplier<BlockState>
                    () -> TOPAZ_BLOCK.get().defaultBlockState(),
                    // Copy properties from IRON_BLOCK or whatever fits best
                    BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                            .strength(2.2f)
                            .requiresCorrectToolForDrops()
            ));

    public static final RegistryObject<Block> TOPAZ_SLAB = registerBlock("topaz_slab",
            () -> new SlabBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                            .strength(2.2f)
                            .requiresCorrectToolForDrops()
            ));

    public static final RegistryObject<Block> TOPAZ_DOOR = registerBlock("topaz_door",
            // 1.20 Door constructor commonly requires a BlockSetType for sounds & more
            () -> new DoorBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR)
                            .strength(2.2f)
                            .requiresCorrectToolForDrops(),
                    BlockSetType.IRON
            ));

    public static final RegistryObject<Block> TOPAZ_BUTTON = registerBlock("topaz_button",
            // StoneButtonBlock => behaves like a stone button (hard, needs pickaxe, etc.)
            () -> new ButtonBlock(
                    BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON)
                            .strength(2.2f)
                            .requiresCorrectToolForDrops()
            ));

    // ---------------------------------------------------------------
    //  Helper Methods
    // ---------------------------------------------------------------
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> registeredBlock = BLOCKS.register(name, block);
        registerBlockItem(name, registeredBlock);
        return registeredBlock;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return NewItem.ITEMS.register(name, () ->
                new BlockItem(block.get(), new Item.Properties())
        );
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
