package cacticrown.fadingdaylight;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
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
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(Items.EGG),
            RecipeCategory.FOOD,
            ModItems.FRIED_EGG,
            0.35f,
            200
        )
        .unlockedBy("has_egg", has(Items.EGG))
        .save(exporter, FadingDaylight.id("fried_egg_from_smelting"));

        SimpleCookingRecipeBuilder.smoking(
            Ingredient.of(Items.EGG),
            RecipeCategory.FOOD,
            ModItems.FRIED_EGG,
            0.35f,
            100
        )
        .unlockedBy("has_egg", has(Items.EGG))
        .save(exporter, FadingDaylight.id("fried_egg_from_smoking"));
    }
}