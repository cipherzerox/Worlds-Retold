package xenoform.hailstorm.proxy;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import xenoform.hailstorm.MTrades;

public class ServerProxy {

	public void postInit(FMLPostInitializationEvent event) {
	}

	public void init(FMLInitializationEvent event)
    {
        VillagerRegistry.VillagerProfession cleric = ForgeRegistries.VILLAGER_PROFESSIONS.getValue(new ResourceLocation("minecraft:cleric"));
        if(cleric != null)
        {
            cleric.getCareer(1).addTrade(3, new MTrades());
            System.out.println("addtrade");
        }
	}

	public void preInit(FMLPreInitializationEvent e) {
	}
}
