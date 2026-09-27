package cacticrown.fadingdaylight.mixin;

import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FoodData.class)
public class FoodDataMixin {

	@ModifyVariable(method = "addExhaustion", at = @At("HEAD"), argsOnly = true)
	private float doubleExhaustion(float exhaustion) {
		return exhaustion * 2;
	}
}