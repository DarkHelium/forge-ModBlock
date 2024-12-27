package net.anavm.blockmod.block;

import net.anavm.blockmod.BlockMod;
import net.anavm.blockmod.item.NewItem;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BlockMod.MODID);

    public static final RegistryObject<Block> TOPAZ_BLOCK = registerBlock("topaz_block",
            ()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).destroyTime(2.2f)));

    public static final RegistryObject<Block> TOPAZ_ORE = registerBlock("topaz_ore",
            ()->new DropExperienceBlock(UniformInt.of(3, 6), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3.5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DEEPSLATE_TOPAZ_ORE = registerBlock("deepslate_topaz_ore",
            ()->new DropExperienceBlock(UniformInt.of(4, 6), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(4.5f).requiresCorrectToolForDrops()));

    private static <T extends Block> RegistryObject<T> registerBlock
            (String name, Supplier<T> block){
        RegistryObject<T> to = BLOCKS.register(name, block);
        registerBlockItem(name, to);
        return to;
    }

    private static <T extends Block> RegistryObject<Item>
    registerBlockItem(String name, RegistryObject<T> block){
        return NewItem.ITEMS.register(name, ()-> new BlockItem(block.get(),
                new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
