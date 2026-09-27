package cacticrown.fadingdaylight;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FadingDaylight implements ModInitializer {
	public static final String MOD_ID = "fading-daylight";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS)
				.register((itemGroup) -> itemGroup.accept(ModItems.FRIED_EGG));

		LOGGER.info("Hello Fabric world!");
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}