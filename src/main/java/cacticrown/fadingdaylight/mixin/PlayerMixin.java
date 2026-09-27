package cacticrown.fadingdaylight.mixin;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {

    @Inject(method = "getDestroySpeed", at = @At("HEAD"), cancellable = true)
    private void fadingdaylight$preventPunchingLogs(BlockState state, CallbackInfoReturnable<Float> cir) {
        Player player = (Player) (Object) this;

        if (!player.isCreative() && state.is(BlockTags.LOGS)) {
            ItemStack heldItem = player.getMainHandItem();

            if (heldItem.is(Items.FLINT)) {
                if (AxeItemAccessor.getStrippables().containsKey(state.getBlock())) {
                    cir.setReturnValue(0.2F);
                } else {
                    cir.setReturnValue(0.0F);
                }
            } else if (!heldItem.is(ItemTags.AXES)) {
                cir.setReturnValue(0.0F);
            }
        }
    }
}