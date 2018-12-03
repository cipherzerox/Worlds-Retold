package xenoform.hailstorm;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.Random;

public class MVillagerTrades implements EntityVillager.ITradeList {
	
	public static final VillagerRegistry.VillagerProfession priest = ForgeRegistries.VILLAGER_PROFESSIONS
			.getValue(new ResourceLocation("minecraft:priest"));

	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
		recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, random.nextInt(5) + 4), new ItemStack(MItems.CRYONITE, 1)));
	}

	public static void registerTrades() {
		priest.getCareer(1).addTrade(3, new MVillagerTrades());
	}
}
