package xenoform.hailstorm.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import xenoform.hailstorm.entity.projectiles.scroll.EntityIceScrollProjectile;

public class BasicItemBlizzScroll extends BasicItem {
	protected String name;

	public BasicItemBlizzScroll(String name) {
		super(name);
		this.name = name;
		this.maxStackSize = 1;
        this.setMaxDamage(3);
		this.setCreativeTab(CreativeTabs.COMBAT);
	}

	@Override
	public int getEntityLifespan(ItemStack itemStack, World world) {
		return 24000;
	}

	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BLOCK;
	}

	public int getMaxItemUseDuration(ItemStack stack) {
		return 20;
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		playerIn.setActiveHand(handIn);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
	}

	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		float speed = 2F;
		entityLiving.playSound(SoundEvents.ENTITY_WITHER_AMBIENT, 1.0F, 0.5F);
		for (int i = 0; i < 360; i += 7.5) {
			EntityIceScrollProjectile ent = new EntityIceScrollProjectile(worldIn);
			ent.motionX = Math.sin((float) Math.toRadians(i)) * speed / 8;
			ent.motionZ = Math.cos((float) Math.toRadians(i)) * speed / 8;
			ent.motionY = 0F;
			ent.setPosition(entityLiving.posX + Math.sin(Math.toRadians(i)), entityLiving.posY + 1, entityLiving.posZ + Math.cos(Math.toRadians(i)));
			worldIn.spawnEntity(ent);
		}
		stack.damageItem(1, entityLiving);
		return stack;
	}
}