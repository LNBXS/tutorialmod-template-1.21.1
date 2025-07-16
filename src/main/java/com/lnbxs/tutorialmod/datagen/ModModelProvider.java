package com.lnbxs.tutorialmod.datagen;

import com.lnbxs.tutorialmod.block.ModBlocks;
import com.lnbxs.tutorialmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
//这个方法用来生成方块状态模型
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ICE_ETHER_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ICE_ETHER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_ICE_ETHER_BLOCK);
//之前写的都是最简单的方块状态文件，也就是立方体的六个面都一样，所以这里使用registerSimpleCubeAll方法来注册我们的方块状态文件

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
//这个方法用来生成方块物品模型
        itemModelGenerator.register(ModItems.ICE_ETHER, Models.GENERATED);//这里使用register方法来注册物品模型文件，第一个参数是的物品，第二个参数是模型的渲染模式
        itemModelGenerator.register(ModItems.RAW_ICE_ETHER, Models.GENERATED);//这里的Models.GENERATED是一般物品的渲染模式，后面写到工具时，还将采用另外的模式
//其实在生成方块状态文件的同时，对应的物品模型也会生成，所以只需要写方块状态文件即可
//这里为了演示，写入了两个物品模型文件

    }
}
