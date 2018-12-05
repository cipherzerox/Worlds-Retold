package xenoform.hailstorm.base;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import xenoform.hailstorm.util.ModelRegistry;

import java.util.Random;

public class BasicBlockOre extends Block implements ModelRegistry {

	protected static String name;
	protected Item ore;

	public BasicBlockOre(Material material, String name, float hardness, String tool, int level, Item ore) {
		super(material);
		this.name = name;
		this.ore = ore;
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
		setHarvestLevel(tool, level);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ore;
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public BasicBlockOre setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
	@Override
	public BasicBlockOre setResistance(float resistance) {
		this.blockResistance = resistance * 3.0F;
		return this;
	}

	@Override
	public BasicBlockOre setSoundType(SoundType tile) {
		this.blockSoundType = tile;
		return this;
	}
}