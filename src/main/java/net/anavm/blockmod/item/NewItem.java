package net.anavm.blockmod.item;

import net.anavm.blockmod.BlockMod;
import net.anavm.blockmod.item.custom.MetalDetectorItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NewItem {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BlockMod.MODID);
    public static final RegistryObject<Item> TOPAZ = ITEMS.register("topaz",
            ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_TOPAZ = ITEMS.register("raw_topaz",
            ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> TOPAZ_SWORD = ITEMS.register("topaz_sword",
            ()->new SwordItem(ModToolTiers.TOPAZ, 6, 2, new Item.Properties()));
    public static final RegistryObject<Item> TOPAZ_PICKAXE = ITEMS.register("topaz_pickaxe",
            ()->new PickaxeItem(ModToolTiers.TOPAZ, 1, 1, new Item.Properties()));
    public static final RegistryObject<Item> TOPAZ_AXE = ITEMS.register("topaz_axe",
            ()->new AxeItem(ModToolTiers.TOPAZ, 8, 1, new Item.Properties()));
    public static final RegistryObject<Item> TOPAZ_SHOVEL = ITEMS.register("topaz_shovel",
            ()->new ShovelItem(ModToolTiers.TOPAZ, 1, 1, new Item.Properties()));
    public static final RegistryObject<Item> TOPAZ_HOE = ITEMS.register("topaz_hoe",
            ()->new HoeItem(ModToolTiers.TOPAZ, 1, 1, new Item.Properties()));
    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(100)));
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
