package cacticrown.fadingdaylight;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;

public class ModItems {
    public static Item register(Item item, String id) {
        ResourceLocation itemID = ResourceLocation.fromNamespaceAndPath(FadingDaylight.MOD_ID, id);

        return Registry.register(BuiltInRegistries.ITEM, itemID, item);
    }

    public static final Item FRIED_EGG = register(
        new Item(new Item.Properties().food(
            new FoodProperties.Builder()
                .nutrition(3)
                .saturationModifier(3.6f)
                .build()
        )),
        "fried_egg"
    );

    public static final Item WOODEN_CLUB = register(
        new ClubItem(
            Tiers.WOOD,
            new Item.Properties().attributes(SwordItem.createAttributes(Tiers.WOOD, 5, -3.2F))
        ),
        "wooden_club"
    );

    public static final Item BONE_CLUB = register(
        new ClubItem(
            BoneTier.INSTANCE,
            new Item.Properties().attributes(SwordItem.createAttributes(BoneTier.INSTANCE, 6, -3.0F))
        ),
        "bone_club"
    );

    public static void registerModItems() {
        FadingDaylight.LOGGER.info("Registering Mod Items for " + FadingDaylight.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(entries -> {
            entries.accept(FRIED_EGG);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(entries -> {
            entries.accept(WOODEN_CLUB);
            entries.accept(BONE_CLUB);
        });
    }
}