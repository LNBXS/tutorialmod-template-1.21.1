package com.lnbxs.tutorialmod;

import com.lnbxs.tutorialmod.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TutorialModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockLootTagsProvider::new);
		pack.addProvider(ModBlockTagsProvider::new);
		pack.addProvider(ModENUSLanProvider::new);
		pack.addProvider(ModItemTagsProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModZHCNLanProvider::new);

	}
}
