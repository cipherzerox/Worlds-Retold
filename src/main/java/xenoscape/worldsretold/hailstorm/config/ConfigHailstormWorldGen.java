package xenoscape.worldsretold.hailstorm.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigHailstormWorldGen extends Configuration {

	private static final String CATEGORY_DECORATIONS = "decorations";
	private static final String CATEGORY_STRUCTURES = "structures";

	// Decorations
	public static boolean areBouldersEnabled;
	public static boolean areFlowersEnabled;
	
	// Structures
	public static boolean isHailstormShrineEnabled;
	public static boolean isIceTowerEnabled;
	public static boolean isBlimpCampEnabled;
	public static boolean isIcyTavernEnabled;
	public static boolean isSnowTempleEnabled;
	public static boolean isMiningStationEnabled;
	public static boolean isRuinedTavernEnabled;
	public static boolean isRuinedTempleEnabled;

	public ConfigHailstormWorldGen(File file) {
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
		isIceTowerEnabled = getBoolean("Enable Ice Tower", CATEGORY_STRUCTURES, true,
				"An icy tower, inside awaits dormant guardians.");
		isBlimpCampEnabled = getBoolean("Enable Blimp Camp", CATEGORY_STRUCTURES, true,
				"An abandoned blimp camp... Somehow the blimp has not deflated.");
		isIcyTavernEnabled = getBoolean("Enable Icy Tavern", CATEGORY_STRUCTURES, true,
				"People used to live here- Wonder what happened to them.");
		isSnowTempleEnabled = getBoolean("Enable Ruined Temple", CATEGORY_STRUCTURES, true,
				". . .");
		isMiningStationEnabled = getBoolean("Enable Mining Station", CATEGORY_STRUCTURES, true,
				". . .");
		isRuinedTavernEnabled = getBoolean("Enable Ruined Tavern", CATEGORY_STRUCTURES, true,
				"A ruined hut, this one seems more damaged than the others.");
		isRuinedTempleEnabled = getBoolean("Enable Ruined Temple", CATEGORY_STRUCTURES, true,
				". . .");
		this.save();
	}

	private void setCategoryLanguageKey(String category) {
		this.setCategoryLanguageKey(category, "worldsretold.config." + category + ".name");
	}
}