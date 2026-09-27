package cacticrown.fadingdaylight.mixin;

import net.minecraft.world.item.Tiers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Tiers.class)
public class TiersMixin {

    @Inject(method = "getUses", at = @At("HEAD"), cancellable = true)
    private void fadingdaylight$overrideWoodDurability(CallbackInfoReturnable<Integer> cir) {
        Tiers tier = (Tiers) (Object) this;

        if (tier == Tiers.WOOD) {
            cir.setReturnValue(15);
        }
    }
}