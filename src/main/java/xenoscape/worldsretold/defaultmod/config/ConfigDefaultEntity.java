package xenoscape.worldsretold.defaultmod.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigDefaultEntity extends Configuration {

    private static final String CATEGORY_NEUTRAL = "neutral";

    // Neutral
    public static boolean isAutomatonEnabled;

    public ConfigDefaultEntity(File file) {
        super(file);
        reload();
    }

    public void reload() {
        this.load();
        // Neutral
        isAutomatonEnabled = getBoolean("Enable Automaton", CATEGORY_NEUTRAL, true,
                "A strange, almost lifelike statue that spawns in dungeons.");
        this.save();
    }

    private void setCategoryLanguageKey(String category) {
        this.setCategoryLanguageKey(category, "worldsretold.config." + category + ".name");
    }
}