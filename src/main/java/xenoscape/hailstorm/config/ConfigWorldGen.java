package xenoscape.hailstorm.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigWorldGen extends Configuration {

	private static final String CATEGORY_DECORATIONS = "decorations";
	private static final String CATEGORY_STRUCTURES = "structures";

	// Decorations
	public static boolean areBouldersEnabled;
	public static boolean areFlowersEnabled;
	
	// Structures
	public static boolean isHailstormShrineEnabled;
	public static boolean isSentinelShackEnabled;

	public ConfigWorldGen(File file) {
		super(file);
		reload();
	}

	public void reload() {
		this.load();
		// Decorations
		areBouldersEnabled = getBoolean("Enable Boulders", CATEGORY_DECORATIONS, true,
				"Boulders that sometimes spawn in the snow biomes. Some are even infested.");
		areFlowersEnabled = getBoolean("Enable Flowers", CATEGORY_DECORATIONS, true,
				"The various flowers from the mod.");
		
		// Structures
		isHailstormShrineEnabled = getBoolean("Enable Hailstorm Shrine", CATEGORY_STRUCTURES, true,
				"A shrine of mysterious origin, that spawns in snowy biomes.");
		isSentinelShackEnabled = getBoolean("Enable Sentinel Shack (WIP)", CATEGORY_STRUCTURES, false,
				"A small shack that used to be occupied by Villagers.");
		this.save();
	}

	private void setCategoryLanguageKey(String category) {
		this.setCategoryLanguageKey(category, "hailstorm.config." + category + ".name");
	}
}