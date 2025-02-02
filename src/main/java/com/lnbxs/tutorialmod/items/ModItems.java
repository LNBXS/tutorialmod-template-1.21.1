package com.lnbxs.tutorialmod.items;

import com.lnbxs.tutorialmod.TutorialMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    private static Item registerItem(String id, Item item) {
        return Registry.register(Registries.ITEM,Identifier.of(TutorialMod.MOD_ID, id), item);
    }
    public static final Item ICE_ETHER = registerItem("ice_ether", new Item(new Item.Settings()));

    public static void registerModItems() {
        TutorialMod.LOGGER.info("Registering Items");
    }
}
