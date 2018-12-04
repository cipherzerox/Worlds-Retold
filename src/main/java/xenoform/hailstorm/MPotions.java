package xenoform.hailstorm;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import xenoform.hailstorm.base.BasicBlock;
import xenoform.hailstorm.potions.PotionFreezing;

public class MPotions {

	public static final Potion FREEZING = new PotionFreezing("freezing", true, 13035007, 0, 0)
			.registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED,
					PotionFreezing.MODIFIER_UUID.toString(), -0.25D, 1);

	public static final PotionType NORMAL_FREEZING = new PotionType("freezing",
			new PotionEffect[] { new PotionEffect(FREEZING, 700) }).setRegistryName("freezing");
	public static final PotionType LONG_FREEZING = new PotionType("freezing",
			new PotionEffect[] { new PotionEffect(FREEZING, 1400) }).setRegistryName("long_freezing");

	public static void registerPotions() {
		registerPotion(NORMAL_FREEZING, LONG_FREEZING, FREEZING);
		registerPotionRecipes();
	}

	private static void registerPotion(PotionType defaultPotion, PotionType longPotion, Potion effect) {
		ForgeRegistries.POTIONS.register(effect);
		ForgeRegistries.POTION_TYPES.register(defaultPotion);
		ForgeRegistries.POTION_TYPES.register(longPotion);
	}

	private static void registerPotionRecipes() {
		PotionHelper.addMix(PotionTypes.AWKWARD, MItems.CRYONITE, NORMAL_FREEZING);
		PotionHelper.addMix(NORMAL_FREEZING, Items.REDSTONE, LONG_FREEZING);
	}
}