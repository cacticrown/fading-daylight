package cacticrown.fadingdaylight;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
        // Fried Egg
        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(Items.EGG),
                        RecipeCategory.FOOD,
                        ModItems.FRIED_EGG,
                        0.35f,
                        100
                )
                .unlockedBy("has_egg", has(Items.EGG))
                .save(exporter, FadingDaylight.id("fried_egg_from_smelting"));

        SimpleCookingRecipeBuilder.smoking(
                        Ingredient.of(Items.EGG),
                        RecipeCategory.FOOD,
                        ModItems.FRIED_EGG,
                        0.35f,
                        50
                )
                .unlockedBy("has_egg", has(Items.EGG))
                .save(exporter, FadingDaylight.id("fried_egg_from_smoking"));

        SimpleCookingRecipeBuilder.campfireCooking(
                        Ingredient.of(Items.EGG),
                        RecipeCategory.FOOD,
                        ModItems.FRIED_EGG,
                        0.35f,
                        300
                )
                .unlockedBy("has_egg", has(Items.EGG))
                .save(exporter, FadingDaylight.id("fried_egg_from_campfire_cooking"));

        // Wooden Club
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.WOODEN_CLUB)
                .define('S', Items.STICK)
                .pattern("S")
                .pattern("S")
                .unlockedBy("has_stick", has(Items.STICK))
                .save(exporter);

        // Bone Club
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.BONE_CLUB)
                .define('B', Items.BONE)
                .pattern("B")
                .pattern("B")
                .unlockedBy("has_bone", has(Items.BONE))
                .save(exporter);
    }
}