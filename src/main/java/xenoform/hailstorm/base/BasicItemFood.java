package xenoform.hailstorm.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import xenoform.hailstorm.util.ModelRegistry;

public class BasicItemFood extends ItemFood implements ModelRegistry {

	public final int itemUseDuration;
	private final int healAmount;
	private final float saturationModifier;
	private final boolean isWolfsFavoriteMeat;
	private float effectProb;
	private Potion effect;
	private int duration;
	private int amp;
	public EnumAction action;
	protected String name;

	public BasicItemFood(String name, int itemUseDuration, int amount, float saturation, boolean isWolfFood,
			EnumAction action) {
		super(amount, saturation, isWolfFood);
		this.name = name;
		this.itemUseDuration = itemUseDuration;
		this.healAmount = amount;
		this.saturationModifier = saturation;
		this.isWolfsFavoriteMeat = isWolfFood;
		this.action = action;
		this.setCreativeTab(CreativeTabs.FOOD);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
	}

	public BasicItemFood(String name, int itemUseDuration, int amount, float saturation, boolean isWolfFood,
			Potion effect, int duration, int amp, float effectProb, EnumAction action) {
		super(amount, saturation, isWolfFood);
		this.name = name;
		this.itemUseDuration = itemUseDuration;
		this.healAmount = amount;
		this.saturationModifier = saturation;
		this.isWolfsFavoriteMeat = isWolfFood;
		this.effect = effect;
		this.duration = duration;
		this.amp = amp;
		this.effectProb = effectProb;
		this.action = action;
		this.setCreativeTab(CreativeTabs.FOOD);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
	}

	@Override
	public BasicItemFood setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return itemUseDuration;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return action;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) entityLiving;
			entityplayer.getFoodStats().addStats(this, stack);
			worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ,
					SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F,
					worldIn.rand.nextFloat() * 0.1F + 0.9F);
			this.onFoodEaten(stack, worldIn, entityplayer);
			entityplayer.addStat(StatList.getObjectUseStats(this));
		}
		stack.shrink(1);
		return stack;
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		if (!worldIn.isRemote && this.effect != null && worldIn.rand.nextFloat() < this.effectProb) {
			player.addPotionEffect(new PotionEffect(effect, duration, amp));
		}
	}
}