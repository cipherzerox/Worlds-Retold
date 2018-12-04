package xenoform.hailstorm.base;

import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
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
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoform.hailstorm.util.ModelRegistry;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class BasicItemTool extends ItemTool implements ModelRegistry {

	protected String name;
	public Item.ToolMaterial material;
    private final Set<Block> effectiveBlocks;
	public int tooltype;
	protected float efficiency;
	protected float damageVsEntity;
	protected float attackSpeed;
	public String toolClass;

	public BasicItemTool(String name, Item.ToolMaterial material, Set<Block> effectiveBlocks, int tooltype, float attackDamageIn, float attackSpeedIn) {
		super(attackDamageIn, attackSpeedIn, material, effectiveBlocks);
		this.name = name;
		this.material = material;
		this.tooltype = tooltype;
		this.efficiency = 4.0F;
		this.maxStackSize = 1;
		this.effectiveBlocks = effectiveBlocks;
		this.setMaxDamage(material.getMaxUses());
		this.efficiency = material.getEfficiency();
		this.damageVsEntity = attackDamageIn + material.getAttackDamage();
		this.attackSpeed = attackSpeedIn;
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

	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		if (tooltype == 0) {
			Material material = state.getMaterial();
			return material != Material.SAND && material != Material.SNOW && material != Material.CLAY
					&& material != Material.GROUND ? super.getDestroySpeed(stack, state) : this.efficiency;
		}
		if (tooltype == 1) {
			Material material = state.getMaterial();
			return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK
					? super.getDestroySpeed(stack, state) : this.efficiency;
		}
		if (tooltype == 2) {
			Material material = state.getMaterial();
			return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE
					? super.getDestroySpeed(stack, state) : this.efficiency;
		}
		return 0;
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
}