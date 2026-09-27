package cacticrown.fadingdaylight;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class ModItems {
    public static Item register(Item item, String id) {
        ResourceLocation itemID = ResourceLocation.fromNamespaceAndPath(FadingDaylight.MOD_ID, id);

        Item registeredItem = Registry.register(BuiltInRegistries.ITEM, itemID, item);

        return registeredItem;
    }

    public static final Item FRIED_EGG = register(
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationModifier(0.6f)
                            .build()
            )),
            "fried_egg"
    );

    public static void registerModItems() {
        FadingDaylight.LOGGER.info("Registering Mod Items for " + FadingDaylight.MOD_ID);
    }
}