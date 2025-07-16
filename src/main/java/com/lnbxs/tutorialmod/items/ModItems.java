package com.lnbxs.tutorialmod.items;

import com.lnbxs.tutorialmod.TutorialMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    private static Item registerItem(String id, Item item) {
        return Registry.register(Registries.ITEM,Identifier.of(TutorialMod.MOD_ID, id), item);
    }
    public static final Item ICE_ETHER = registerItem("ice_ether", new Item(new Item.Settings()));
    public static final Item RAW_ICE_ETHER = registerItem("raw_ice_ether", new Item(new Item.Settings()));
    public static final Item ICE_ETHER_ORE = registerItem("ice_ether_ore", new Item(new Item.Settings()));

    private static void addItemToIG(FabricItemGroupEntries fabricItemGroupEntries){
        fabricItemGroupEntries.add(ICE_ETHER);
        fabricItemGroupEntries.add(RAW_ICE_ETHER);
        fabricItemGroupEntries.add(ICE_ETHER_ORE);
    }

//    private static void addItemToIG2(FabricItemGroupEntries fabricItemGroupEntries){
//    }

    public static void registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemToIG);
//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemToIG2);
        TutorialMod.LOGGER.info("Registering Items");
    }
}
