package xenoscape.worldsretold.defaultmod.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigDefaultMisc extends Configuration {

    private static final String CATEGORY_MISC = "miscellaneous";

    public static boolean isLoginMessageEnabled;

    public ConfigDefaultMisc(File file) {
        super(file);
        reload();
    }

    public void reload() {
        this.load();
        isLoginMessageEnabled = getBoolean("Enable Login Message", CATEGORY_MISC, true,
                "The message that pops up on chance, upon joining the world.");
        this.save();
    }

    private void setCategoryLanguageKey(String category) {
        this.setCategoryLanguageKey(category, "worldsretold.config." + category + ".name");
    }
}