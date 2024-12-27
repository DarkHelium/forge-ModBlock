package net.anavm.blockmod.item;

import net.anavm.blockmod.BlockMod;
import net.anavm.blockmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BlockMod.MODID);
    public static final RegistryObject<CreativeModeTab> BLOCK_TAB = CREATIVE_MODE_TABS.register("block_tab",
            ()->CreativeModeTab.builder().icon(()->new ItemStack(NewItem.TOPAZ.get()))
            .title(Component.translatable("creativetab.block_tab"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(NewItem.TOPAZ.get());
                output.accept(NewItem.RAW_TOPAZ.get());

                output.accept(ModBlocks.TOPAZ_BLOCK.get());
                output.accept(ModBlocks.TOPAZ_ORE.get());
                output.accept(ModBlocks.DEEPSLATE_TOPAZ_ORE.get());
            })
            .build());
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
