package bigsir.worldseamsfix.mixin;

import net.minecraft.client.render.RenderBlocks;
import net.minecraft.core.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import bigsir.worldseamsfix.IOffset;

@Mixin(value = RenderBlocks.class, remap = false)
public abstract class RenderBlocksMixin implements IOffset {
	@Unique
	private static double offset = 0.00009765625;

	@Override
	public void worldseamsfix$setOffset(double val) {
		offset = val;
	}

	@Redirect(method = {
		"renderTopFace",
		"renderBottomFace",
		"renderNorthFace",
		"renderSouthFace",
	}, at = @At(value = "FIELD", target = "Lnet/minecraft/core/block/Block;minX:D", ordinal = 5))
	private double fixTop1(Block instance){
		return instance.minX - offset;
	}

	@Redirect(method = {
		"renderSouthFace",
		"renderTopFace",
		"renderBottomFace",
		"renderNorthFace",
	}, at = @At(value = "FIELD", target = "Lnet/minecraft/core/block/Block;maxX:D", ordinal = 5))
	private double fixTop2(Block instance){
		return instance.maxX + offset;
	}

	@Redirect(method = {
		"renderTopFace",
		"renderBottomFace",
		"renderWestFace",
		"renderEastFace"
	}, at = @At(value = "FIELD", target = "Lnet/minecraft/core/block/Block;minZ:D", ordinal = 5))
	private double fixTop3(Block instance){
		return instance.minZ - offset;
	}
	@Redirect(method = {
		"renderTopFace",
		"renderBottomFace",
		"renderWestFace",
		"renderEastFace",
	}, at = @At(value = "FIELD", target = "Lnet/minecraft/core/block/Block;maxZ:D", ordinal = 5))
	private double fixTop4(Block instance){
		return instance.maxZ + offset;
	}

	@Redirect(method = {
		"renderNorthFace",
		"renderSouthFace",
		"renderWestFace",
		"renderEastFace"
	}, at = @At(value = "FIELD", target = "Lnet/minecraft/core/block/Block;minY:D", ordinal = 5))
	private double fixTop5(Block instance){
		return instance.minY - offset;
	}
	@Redirect(method = {
		"renderNorthFace",
		"renderSouthFace",
		"renderWestFace",
		"renderEastFace"
	}, at = @At(value = "FIELD", target = "Lnet/minecraft/core/block/Block;maxY:D", ordinal = 5))
	private double fixTop6(Block instance){
		return instance.maxY + offset;
	}
}
