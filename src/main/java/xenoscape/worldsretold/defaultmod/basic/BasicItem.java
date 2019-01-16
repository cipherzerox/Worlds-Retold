package xenoscape.worldsretold.defaultmod.basic;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import xenoscape.worldsretold.defaultmod.util.ModelRegistry;
import xenoscape.worldsretold.heatwave.init.HeatwaveItems;

public class BasicItem extends Item implements ModelRegistry {

	protected String name;

	public BasicItem(String name) {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
	}

	@Override
	public BasicItem setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (itemstack.getItem() == HeatwaveItems.BANDAGE_CLEAN)
        {
        	if (!worldIn.isRemote && playerIn.getHealth() < playerIn.getMaxHealth())
        	{
                EntityPlayerMP entityplayermp = (EntityPlayerMP)playerIn;
                CriteriaTriggers.CONSUME_ITEM.trigger(entityplayermp, itemstack);
        		worldIn.playSound(null, playerIn.getPosition(), SoundEvents.ENTITY_LEASHKNOT_PLACE, playerIn.getSoundCategory(), 1F, 1.5F);
        		playerIn.heal(2F);
        		itemstack.shrink(1);
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        	}
        	else
        	{
                return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        	}

        }
        else
        {
            return super.onItemRightClick(worldIn, playerIn, handIn);
        }
    }
}