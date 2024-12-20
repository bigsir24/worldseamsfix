package bigsir.worldseamsfix.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.option.Option;
import net.minecraft.client.option.RangeOption;
import net.minecraft.client.render.block.model.BlockModel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import bigsir.worldseamsfix.IOffset;
import bigsir.worldseamsfix.WorldSeamsFix;

import java.io.File;

@Mixin(value = GameSettings.class, remap = false)
public abstract class GameSettingsMixin {
	@Shadow
	@Final
	public Minecraft mc;
	@Unique
	public RangeOption offset;

	@Inject(method = "getDisplayString", at = @At("HEAD"), cancellable = true)
	public void translateValue(Option<?> option, CallbackInfoReturnable<String> cir){
		if(option == offset){
			cir.setReturnValue(String.valueOf(Math.pow(2, offset.value+1) / 1_048_576_0));
		}
	}

	@Inject(method = "<init>", at = @At(value = "NEW", target = "(Ljava/io/File;Ljava/lang/String;)Ljava/io/File;"))
	public void addOptions(Minecraft minecraft, File file, CallbackInfo ci){
		WorldSeamsFix.optionsInit((GameSettings) (Object)this);

		offset = WorldSeamsFix.offset;
	}

	@Inject(method = "optionChanged", at = @At("HEAD"))
	public void changeText(Option<?> option, CallbackInfo ci){
		if(option == offset){
			((IOffset) BlockModel.renderBlocks).worldseamsfix$setOffset(Math.pow(2, offset.value+1) / 1_048_576_0);
			mc.renderGlobal.loadRenderers();
		}
	}
}
