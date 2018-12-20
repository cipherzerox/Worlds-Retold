package xenoscape.hailstorm.base;

import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xenoscape.hailstorm.main.MPotions;
import xenoscape.hailstorm.util.ModelRegistry;
=======
import xenoscape.hailstorm.main.MPotions;
import xenoscape.hailstorm.util.ModelRegistry;
>>>>>>> 2f34effc0ad705294dd03b18a13038d17d9b8376:src/main/java/xenoscape/hailstorm/base/BasicItemTool.java

import java.util.Set;

public class BasicItemTool extends ItemTool implements ModelRegistry {

	protected String name;
	public Item.ToolMaterial material;
	private final Set<Block> effectiveBlocks;
	public int tooltype;
	protected float efficiency;
	protected float damageVsEntity;
	protected float attackSpeed;
	protected int effect;

	public BasicItemTool(String name, Item.ToolMaterial material, Set<Block> effectiveBlocks, int tooltype,
			float attackDamageIn, float attackSpeedIn, int effect) {
		super(attackDamageIn, attackSpeedIn, material, effectiveBlocks);
		this.name = name;
		this.material = material;
		this.tooltype = tooltype;
		this.effectiveBlocks = effectiveBlocks;
		this.efficiency = material.getEfficiency();
		this.damageVsEntity = attackDamageIn + material.getAttackDamage();
		this.attackSpeed = attackSpeedIn;
		this.effect = effect;
		this.setCreativeTab(CreativeTabs.TOOLS);
		setUnlocalizedName(name);
		setRegistryName(name);
		if (this.tooltype == 0) {
			toolClass = "shovel";
		}
		if (this.tooltype == 1) {
			toolClass = "pickaxe";
		}
		if (this.tooltype == 2) {
			toolClass = "axe";
		}
	}

	@Override
	public BasicItemTool setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
					new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", (double) this.damageVsEntity, 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
					new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", (double) this.attackSpeed, 0));
		}

		return multimap;
	}

	public boolean canHarvestBlock(IBlockState blockIn) {
		if (tooltype == 1) {
			Block block = blockIn.getBlock();
			return block == Blocks.SNOW_LAYER || block == Blocks.SNOW;
		}
		return true;
	}

	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (tooltype == 1) {
			ItemStack itemstack = player.getHeldItem(hand);

			if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
				return EnumActionResult.FAIL;
			} else {
				IBlockState iblockstate = worldIn.getBlockState(pos);
				Block block = iblockstate.getBlock();

				if (facing != EnumFacing.DOWN && worldIn.getBlockState(pos.up()).getMaterial() == Material.AIR
						&& block == Blocks.GRASS) {
					IBlockState iblockstate1 = Blocks.GRASS_PATH.getDefaultState();
					worldIn.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);

					if (!worldIn.isRemote) {
						worldIn.setBlockState(pos, iblockstate1, 11);
						itemstack.damageItem(1, player);
					}

					return EnumActionResult.SUCCESS;
				} else {
					return EnumActionResult.PASS;
				}
			}
		}
		return null;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		for (String type : getToolClasses(stack)) {
			if (state.getBlock().isToolEffective(type, state))
				return efficiency;
		}
		return this.effectiveBlocks.contains(state.getBlock()) ? this.efficiency : 1.0F;
	}

	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(1, attacker);
		if (this.effect == 1) {
			target.addPotionEffect(new PotionEffect(MPotions.FREEZING, 100, 0));
		}
		return true;
	}

	@javax.annotation.Nullable
	private String toolClass;

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass,
			@javax.annotation.Nullable net.minecraft.entity.player.EntityPlayer player,
			@javax.annotation.Nullable IBlockState blockState) {
		int level = super.getHarvestLevel(stack, toolClass, player, blockState);
		if (level == -1 && toolClass.equals(this.toolClass)) {
			return this.toolMaterial.getHarvestLevel();
		} else {
			return level;
		}
	}

	@Override
	public Set<String> getToolClasses(ItemStack stack) {
		return toolClass != null ? com.google.common.collect.ImmutableSet.of(toolClass) : super.getToolClasses(stack);
	}
}
