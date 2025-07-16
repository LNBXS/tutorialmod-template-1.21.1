package com.lnbxs.tutorialmod.datagen;

import com.lnbxs.tutorialmod.TutorialMod;
import com.lnbxs.tutorialmod.block.ModBlocks;
import com.lnbxs.tutorialmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.lnbxs.tutorialmod.items.ModItems.RAW_ICE_ETHER;

public class ModRecipeProvider extends FabricRecipeProvider {

    private static final List<ItemConvertible> ICE_ETHER = List.of(RAW_ICE_ETHER, Items.ICE);

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
//这里写配方文件
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.ICE_ETHER,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_ETHER_BLOCK);
        //先前我们写的1个方块合成9个物品的配方及其逆配方，现在只需要上面两行代码即可实现
        /*
        * 这里使用offerReversibleCompactingRecipes方法来添加配方，
        * 第一个是exporter,
        * 第二个是配方的类型，是后者（Block/Item）合成该参数的后一个产物（Item/Block）的分类
        * 第三个是合成的物品（或方块）
        * 第四个是配方的类型，是前者（Item/Block）合成该参数的后一个产物（Block/Item）的分类
        * 第五个是合成的方块（或物品）
         */
//熔炉/高炉配方
        offerSmelting(exporter, ICE_ETHER, RecipeCategory.MISC, ModItems.ICE_ETHER, 0.7f, 200,"ice ether");//熔炉
        offerBlasting(exporter, ICE_ETHER, RecipeCategory.MISC, ModItems.ICE_ETHER, 0.7f, 100,"ice ether");//高炉
        /*这里它们两个的参数本质上是一样的，
        * 第一个和上面的合成配方一样
        * 第二个参数是我们的List，
        * 第三个参数是配方的分类，第四个参数是配方的产物，
        * 第五个参数是熔炼经验，第六个参数是熔炼时间，
        * 第七个参数是配方所属的组*/

//营火/烟熏炉配方
   // offerCampfireCooking(exporter, ICE_ETHER_LIST, RecipeCategory.MISC, ModItems.ICE_ETHER, 0.35f, 600,"ice ether");
        //这里使用offerCampfireCooking方法来添加营火/烟熏炉配方，
        //但使用这个方法需要提供一个List作为输入，
        //所以就需要在下面定义一个List(就是下面那串被注释掉的)
        //如果你觉得太复杂了，可以直接使用offerFoodCookingRecipe方法，如下
        offerFoodCookingRecipe(exporter, "ice ether", RecipeSerializer.CAMPFIRE_COOKING,CampfireCookingRecipe::new,
                600, ModItems.RAW_ICE_ETHER, ModItems.ICE_ETHER, 0.35f);
        /*与上面的方法相比，这个方法可以直接指定食物名称，不需要提供List作为输入，而且只是将其中的形参换成了具体的实参而已
        *但是，如果你有大量的配方需要添加，我的建议还是提炼个方法出来
        *不然每次都要写一大堆，不仅麻烦，而且容易出错*/


//有序合成与无序合成的
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.SUGAR,3)
                .pattern("###")
                .input('#', Ingredient.ofItems(Items.BEETROOT))
                .criterion("has item", RecipeProvider.conditionsFromItem(Items.BEETROOT))
                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID, "beetroot_to_sugar"));
        /*这里使用ShapedRecipeJsonBuilder来创建有序合成配方，
        * pattern是合成表格，这里只有一行，完整的九宫格，是像下面这样的：
        * .pattern("###"）
        * .pattern("###"）
        * .pattern("###"）
        * input是输入，也就是你九宫格中单字符对应的物品，说明白点就是这个字符所对应的物品，这里使用#来代表BEETROOT
        * 如果有多个物品，可以多写几个input，用任意字符来表示这些物品，比如！表示物品A，@表示物品B，以此类推（但不能超过9个）；
        * criterion是条件，这个属于隐形进度，也就是不同于“石器时代”、“获得升级”这种有类似于任务书一样可见进度
        * 配方在游戏中是按照你游戏时触发的条件解锁的，比如你入水了，会解锁和船有关系的配方；获得了原木，则会解锁和木头相关的配方等等，
        * 注意：criterion在数据生成中是必须写的！！！！！！！！！！！
        * offerTo是输出，里面的Identifier是配方的命名空间和id，命名空间不写的话是默认在minecraft下的      */


        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS,ModBlocks.ICE_ETHER_ORE)
                .input(ModItems.RAW_ICE_ETHER)
                .input(Items.STONE)
                .criterion("has item", RecipeProvider.conditionsFromItem(ModItems.RAW_ICE_ETHER))
                .criterion("has item", RecipeProvider.conditionsFromItem(Items.STONE))
                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID, "ice_ether_ore"));
        /*这里使用ShapelessRecipeJsonBuilder来创建无序合成配方
        * input和上面一样，一样不能超过9个
        * criterion和上面一样，不过这里有两个条件，和有序合成一样，也是必须写的.
        * 不过写一个也行，写多个也行，这里的话，只要有一个条件满足，那么这个配方就会解锁；
        * offerTo也一样。                                                        */


    }

//    public static void offerCampfireCooking(RecipeExporter exporter, List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group){
//        RecipeProvider.offerMultipleOptions(exporter, RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new,
//                inputs, category, output, experience, cookingTime, group, "_fire_campfire_cooking");
//    }

}

