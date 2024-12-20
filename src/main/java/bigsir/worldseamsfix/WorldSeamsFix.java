package bigsir.worldseamsfix;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.gui.options.components.OptionsCategory;
import net.minecraft.client.gui.options.components.ToggleableOptionComponent;
import net.minecraft.client.gui.options.data.OptionsPage;
import net.minecraft.client.gui.options.data.OptionsPages;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.option.RangeOption;
import net.minecraft.core.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.ClientStartEntrypoint;


public class WorldSeamsFix implements ModInitializer, ClientStartEntrypoint {
    public static final String MOD_ID = "worldseamsfix";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        LOGGER.info("World Seams Fix initialized.");
    }

	public static OptionsPage optionsPage;
	public static RangeOption offset;

	public static void optionsInit(GameSettings settings){
		offset = new RangeOption(settings, MOD_ID + ".offset", 9, 15);
	}

	@Override
	public void afterClientStart() {
		optionsPage = new OptionsPage(MOD_ID + ".title", Block.bedrock.getDefaultStack());
		OptionsPages.register(optionsPage);

		optionsPage.withComponent(
			new OptionsCategory(MOD_ID + ".category")
				.withComponent(new ToggleableOptionComponent<>(offset))
		);
	}

	@Override
	public void beforeClientStart() {

	}
}
