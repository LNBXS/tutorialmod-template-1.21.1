package com.lnbxs.tutorialmod.datagen;

import com.lnbxs.tutorialmod.block.ModBlocks;
import com.lnbxs.tutorialmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTagsProvider extends FabricBlockLootTableProvider {
    public ModBlockLootTagsProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        addDrop(ModBlocks.ICE_ETHER_BLOCK);
        addDrop(ModBlocks.RAW_ICE_ETHER_BLOCK);
        //addDrop(ModBlocks.ICE_ETHER_ORE,oreDrops(ModBlocks.ICE_ETHER_ORE, ModItems.RAW_ICE_ETHER));
        //上面这行代码是一般矿石的掉落，只会掉落一个矿石，下面这行代码是会掉多个掉落物的矿石的掉落，这里用下面这个方法。
        addDrop(ModBlocks.ICE_ETHER_ORE, copperOreDrops(ModBlocks.ICE_ETHER_ORE, ModItems.ICE_ETHER));
        //但如果使用这个方法，你就需要写一个copperOreDrops方法，如下：
    }

    public LootTable.Builder copperOreDrops(Block drop, Item dropItem) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(
                drop,
                (LootPoolEntry.Builder<?>) this.applyExplosionDecay(
                        drop,
                        ItemEntry.builder(dropItem)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F)))
                                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }
    //其实也不算重新写，只是把ItemEntry.builder(dropItem)改成了dropItem，然后在copperOreDrops后面的括号里添加了Item dropItem参数。
}