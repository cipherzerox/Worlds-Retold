package xenoscape.worldsretold.hellfire.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHellfireEntity extends Configuration {

    private static final String CATEGORY_PASSIVE = "passive";
    private static final String CATEGORY_NEUTRAL = "neutral";
    private static final String CATEGORY_HOSTILE = "hostile";
    
    // Passive
    public static boolean isEnabled;

    // Neutral
    public static boolean isScorpionEnabled;

    //Hostile
    public static boolean isLivingFlameEnabled;

    public ConfigHellfireEntity(File file) {
        super(file);
        reload();
    }

    public void reload() {
        this.load();
        // Passive
        isEnabled = getBoolean("Enable Animal", CATEGORY_PASSIVE, true,
                "waiting for passive mob to be added");

        // Neutral
        isScorpionEnabled = getBoolean("Enable Hellfire Scorpion", CATEGORY_NEUTRAL, true,
                "A far more deadly cousin of the normal scorpion, it's venom burns like fire.");

        // Hostile
        isLivingFlameEnabled = getBoolean("Enable Living Flame", CATEGORY_HOSTILE, true,
                "A hostile fire that sits on netherrack, waiting for unsuspecting pastibyers, then either attempting to burn them, or shooting them with hellfire.");
        this.save();
    }

    private void setCategoryLanguageKey(String category) {
        this.setCategoryLanguageKey(category, "worldsretold.config." + category + ".name");
    }
}