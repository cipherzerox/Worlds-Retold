package xenoscape.worldsretold.hailstorm.item;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xenoscape.worldsretold.basic.BasicBlock;

public class BasicBlockCritter extends BasicBlock {
	protected static String name;
	protected Block originalBlock;
	protected Random rand = new Random();

	public BasicBlockCritter(String name, Block originalBlockIn) {
		super(Material.CLAY, name, 0.5F);
		this.setCreativeTab(CreativeTabs.DECORATIONS);
		BasicBlockCritter.name = name;
		this.originalBlock = originalBlockIn;
	}

	public int quantityDropped(Random random) {
		return 0;
	}

	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(originalBlock);
	}

	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(originalBlock);
	}

	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
		if (!worldIn.isRemote && worldIn.getGameRules().getBoolean("doTileDrops") && rand.nextInt(3) == 0) {
			EntitySilverfish entitysilverfish = new EntitySilverfish(worldIn);
			entitysilverfish.setLocationAndAngles((double) pos.getX() + 0.5D, (double) pos.getY(),
					(double) pos.getZ() + 0.5D, 0.0F, 0.0F);
			worldIn.spawnEntity(entitysilverfish);
			entitysilverfish.spawnExplosionParticle();
		}
	}
}