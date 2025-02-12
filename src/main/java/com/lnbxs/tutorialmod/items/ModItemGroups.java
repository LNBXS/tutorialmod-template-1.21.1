package com.lnbxs.tutorialmod.items;

import com.lnbxs.tutorialmod.TutorialMod;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
//    private static RegistryKey<ItemGroup> registry(String id){
//        return RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(TutorialMod.MOD_ID, id));
//    }
//
//    public static final RegistryKey<ItemGroup> TUTORIAL_GROUP = registry("tutorial_group");
//    public static void registerModItemGroups() {
//        Registry.register(
//                Registries.ITEM_GROUP,
//                TUTORIAL_GROUP,
//                ItemGroup.create(ItemGroup.Row.BOTTOM,3)
//                        .displayName(Text.translatable("itemGroup.tutorial_group"))
//                        .icon(() -> new ItemStack(Items.IRON_INGOT))
//                        .entries((displayContext, entries) -> {
//                            entries.add(ModItems.ICE_ETHER);
//                            entries.add(Items.DIAMOND);
//                            entries.add(Items.OAK_WOOD);
//                        })
//                        .build()
//
//        );
//    }
    public static final ItemGroup TUTORIAL_GROUP = Registry.register(Registries.ITEM_GROUP,Identifier.of(TutorialMod.MOD_ID, "tutorial_group"),
        ItemGroup.create(null,-1)
                .displayName(Text.translatable("itemGroup.tutorial_group"))
                .icon(() -> new ItemStack(Items.IRON_INGOT))
                .entries((displayContext, entries) -> {
                    entries.add(ModItems.ICE_ETHER);
                    entries.add(Items.DIAMOND);
                    entries.add(Items.OAK_WOOD);
                })
                .build()
        );

    public static void registerModItemGroups() {
        TutorialMod.LOGGER.info("Registering Item Groups");
    }

}
