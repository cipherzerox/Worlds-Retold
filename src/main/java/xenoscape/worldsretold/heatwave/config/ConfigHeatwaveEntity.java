package xenoscape.worldsretold.heatwave.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHeatwaveEntity extends Configuration {

    private static final String CATEGORY_PASSIVE = "passive";
    private static final String CATEGORY_NEUTRAL = "neutral";
    private static final String CATEGORY_HOSTILE = "hostile";

    // Neutral
    public static boolean isCamelEnabled;
    public static boolean isScorpionEnabled;
    public static boolean isCobraEnabled;

    //Hostile
    public static boolean isMummyEnabled;

    public ConfigHeatwaveEntity(File file) {
        super(file);
        reload();
    }

    public void reload() {
        this.load();

        // Neutral
        isCamelEnabled = getBoolean("Enable Camel", CATEGORY_NEUTRAL, true,
                "A humpy creature that likes to spit when angry.");
        isScorpionEnabled = getBoolean("Enable Scorpion", CATEGORY_NEUTRAL, true,
                "A venomous scorpion that you should definitely run away from.");
        isCobraEnabled = getBoolean("Enable Cobra", CATEGORY_NEUTRAL, true,
                "A desert snake that you probably shouldn't poke.");

        // Hostile
        isMummyEnabled = getBoolean("Enable Mummy", CATEGORY_HOSTILE, true,
                "A fully-covered Husk. Much more classier and intelligent.");
        this.save();
    }

    private void setCategoryLanguageKey(String category) {
        this.setCategoryLanguageKey(category, "worldsretold.config." + category + ".name");
    }
}