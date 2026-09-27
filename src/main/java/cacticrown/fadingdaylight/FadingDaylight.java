package cacticrown.fadingdaylight;

import cacticrown.fadingdaylight.mixin.AxeItemAccessor;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FadingDaylight implements ModInitializer {
	public static final String MOD_ID = "fading-daylight";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();

		PlayerBlockBreakEvents.BEFORE.register((level, player, pos, state, blockEntity) -> {
			if (player.isCreative()) return true;

			if (state.is(BlockTags.LOGS)) {
				ItemStack heldItem = player.getMainHandItem();

				if (heldItem.is(Items.FLINT)) {
					Block strippedBlock = AxeItemAccessor.getStrippables().get(state.getBlock());

					if (strippedBlock != null) {
						if (!level.isClientSide()) {
							BlockState strippedState = strippedBlock.defaultBlockState();
							if (state.hasProperty(RotatedPillarBlock.AXIS) && strippedState.hasProperty(RotatedPillarBlock.AXIS)) {
								strippedState = strippedState.setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
							}

							level.setBlock(pos, strippedState, 3);
							Block.popResource(level, pos, new ItemStack(Items.STICK, 1));
							level.playSound(null, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
						}

						return false;
					}
				}

				if (!heldItem.is(ItemTags.AXES)) {
					return false;
				}
			}

			return true;
		});

		LOGGER.info("Fading Daylight initialized successfully!");
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}