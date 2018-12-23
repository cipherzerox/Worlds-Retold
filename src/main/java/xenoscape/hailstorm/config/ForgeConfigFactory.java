package xenoscape.hailstorm.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import xenoscape.hailstorm.Hailstorm;
import xenoscape.hailstorm.init.MConfig;

public class ForgeConfigFactory implements IModGuiFactory {

	public static List<IConfigElement> getConfigElements(Configuration config) {
		ArrayList<String> childs = new ArrayList<>();
		for (String category : config.getCategoryNames()) {
			if (config.getCategory(category).parent == null)
				childs.add(category);
		}
		List<IConfigElement> list = Lists.newArrayList();
		for (String category : childs) {
			if (category.contains("\\."))
				continue;
			list.add((new ConfigElement(config.getCategory(category))));
		}
		return list;
	}
	
	public static List<IConfigElement> generate() {
		Configuration[] configs = new Configuration[] {MConfig.entity, MConfig.world_gen};
		List<IConfigElement> list = Lists.newArrayList();
		
		for(Configuration config : configs) {
			list.add(new DummyConfigElement.DummyCategoryElement(config.getConfigFile().getName(), "hailstorm.configfile." + config.getConfigFile().getName() + ".name", getConfigElements(config)));
		}
		return list;
	}
	
	public static class GuiConfigScreen extends GuiConfig {

		public GuiConfigScreen(GuiScreen parentScreen) {
			super(parentScreen, generate(), Hailstorm.MODID, Hailstorm.MODID, false, false, Hailstorm.NAME, null);
		}	
	}

	@Override
	public void initialize(Minecraft minecraftInstance) {}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	@Override
	public boolean hasConfigGui() {
		return true;
	}

	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen) {
		return new GuiConfigScreen(parentScreen);
	}
}