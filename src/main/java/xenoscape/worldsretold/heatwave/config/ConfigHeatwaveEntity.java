package xenoscape.worldsretold.heatwave.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHeatwaveEntity extends Configuration {

    private static final String CATEGORY_PASSIVE = "passive";
    private static final String CATEGORY_NEUTRAL = "neutral";
    private static final String CATEGORY_HOSTILE = "hostile";
    
    // Passive
    public static boolean isRoadrunnerEnabled;

    // Neutral
    public static boolean isCamelEnabled;
    public static boolean isScorpionEnabled;
    public static boolean isCobraEnabled;

    //Hostile
    public static boolean isAnubiteEnabled;
    public static boolean isCactorEnabled;
    public static boolean isFesterEnabled;
    public static boolean isMummyEnabled;

    public ConfigHeatwaveEntity(File file) {
        super(file);
        reload();
    }

    public void reload() {
        this.load();
        // Passive
        isRoadrunnerEnabled = getBoolean("Enable Roadrunner", CATEGORY_PASSIVE, true,
                "A fast moving bird that runs from you, and you have no ACME to use either.");

        // Neutral
        isCamelEnabled = getBoolean("Enable Camel", CATEGORY_NEUTRAL, true,
                "A humpy creature that likes to spit when angry.");
        isScorpionEnabled = getBoolean("Enable Scorpion", CATEGORY_NEUTRAL, true,
                "A venomous scorpion that you should definitely run away from.");
        isCobraEnabled = getBoolean("Enable Cobra", CATEGORY_NEUTRAL, true,
                "A desert snake that you probably shouldn't poke.");

        // Hostile
        isAnubiteEnabled = getBoolean("Enable Anubite", CATEGORY_HOSTILE, true,
                "A nasty dog headed guardian of the desert tombs, it dual weilds Khopeshs.");
        isCactorEnabled = getBoolean("Enable Cactor", CATEGORY_HOSTILE, true,
                "An evil cactus that waits until you get close, then lifts up and attacks you.");
        isFesterEnabled = getBoolean("Enable Fester", CATEGORY_HOSTILE, true,
                "A dried up skeleton that shoots hunger arrows at you, and sometimes hold a sword.");
        isMummyEnabled = getBoolean("Enable Mummy", CATEGORY_HOSTILE, true,
                "A fully-covered Husk. Much more classier and intelligent.");
        this.save();
    }

    private void setCategoryLanguageKey(String category) {
        this.setCategoryLanguageKey(category, "worldsretold.config." + category + ".name");
    }
}