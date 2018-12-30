package xenoscape.worldsretold.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigModules extends Configuration {

	private static final String CATEGORY_MODULES = "modules";

	public static boolean isHailstormEnabled;
	public static boolean isHeatwaveEnabled;

	public ConfigModules(File file) {
		super(file);
		reload();
	}

	public void reload() {
		this.load();

		isHailstormEnabled = getBoolean("Enable Hailstorm Module", CATEGORY_MODULES, true,
				"Adds new life and flora to the snow biomes of Minecraft.");
		isHeatwaveEnabled = getBoolean("Enable Heatwave Module", CATEGORY_MODULES, true,
				"Adds new life and flora to the desert biome of Minecraft.");

		this.save();
	}

	private void setCategoryLanguageKey(String category) {
		this.setCategoryLanguageKey(category, "worldsretold.config." + category + ".name");
	}
}