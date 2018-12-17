package xenoform.hailstorm.base;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStoneBrick;
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

public class BasicBlockCritter extends BasicBlock {
	public static final PropertyEnum<BasicBlockCritter.EnumType> VARIANT = PropertyEnum.<BasicBlockCritter.EnumType>create(
			"variant", BasicBlockCritter.EnumType.class);
	protected static String name;

	public BasicBlockCritter() {
		super(Material.CLAY, name, 0.75F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BasicBlockCritter.EnumType.STONE));
		this.setHardness(0.0F);
		this.setCreativeTab(CreativeTabs.DECORATIONS);
		BasicBlock.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
	}
	
	@Override
	public BasicBlock setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random random) {
		return 0;
	}

	public static boolean canContainSilverfish(IBlockState blockState) {
		Block block = blockState.getBlock();
		return blockState == Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.STONE)
				|| block == Blocks.COBBLESTONE || block == Blocks.STONEBRICK;
	}

	protected ItemStack getSilkTouchDrop(IBlockState state) {
		switch ((BasicBlockCritter.EnumType) state.getValue(VARIANT)) {
		case COBBLESTONE:
			return new ItemStack(Blocks.COBBLESTONE);
		case STONEBRICK:
			return new ItemStack(Blocks.STONEBRICK);
		case MOSSY_STONEBRICK:
			return new ItemStack(Blocks.STONEBRICK, 1, BlockStoneBrick.EnumType.MOSSY.getMetadata());
		case CRACKED_STONEBRICK:
			return new ItemStack(Blocks.STONEBRICK, 1, BlockStoneBrick.EnumType.CRACKED.getMetadata());
		case CHISELED_STONEBRICK:
			return new ItemStack(Blocks.STONEBRICK, 1, BlockStoneBrick.EnumType.CHISELED.getMetadata());
		default:
			return new ItemStack(Blocks.STONE);
		}
	}

	/**
	 * Spawns this Block's drops into the World as EntityItems.
	 */
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
		if (!worldIn.isRemote && worldIn.getGameRules().getBoolean("doTileDrops")) {
			EntitySilverfish entitysilverfish = new EntitySilverfish(worldIn);
			entitysilverfish.setLocationAndAngles((double) pos.getX() + 0.5D, (double) pos.getY(),
					(double) pos.getZ() + 0.5D, 0.0F, 0.0F);
			worldIn.spawnEntity(entitysilverfish);
			entitysilverfish.spawnExplosionParticle();
		}
	}

	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(this, 1, state.getBlock().getMetaFromState(state));
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood
	 * returns 4 blocks)
	 */
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (BasicBlockCritter.EnumType blocksilverfish$enumtype : BasicBlockCritter.EnumType.values()) {
			items.add(new ItemStack(this, 1, blocksilverfish$enumtype.getMetadata()));
		}
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, BasicBlockCritter.EnumType.byMetadata(meta));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state) {
		return ((BasicBlockCritter.EnumType) state.getValue(VARIANT)).getMetadata();
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

	public static enum EnumType implements IStringSerializable {
		STONE(0, "stone") {
			public IBlockState getModelBlock() {
				return Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.STONE);
			}
		},
		COBBLESTONE(1, "cobblestone", "cobble") {
			public IBlockState getModelBlock() {
				return Blocks.COBBLESTONE.getDefaultState();
			}
		},
		STONEBRICK(2, "stone_brick", "brick") {
			public IBlockState getModelBlock() {
				return Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT,
						BlockStoneBrick.EnumType.DEFAULT);
			}
		},
		MOSSY_STONEBRICK(3, "mossy_brick", "mossybrick") {
			public IBlockState getModelBlock() {
				return Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT,
						BlockStoneBrick.EnumType.MOSSY);
			}
		},
		CRACKED_STONEBRICK(4, "cracked_brick", "crackedbrick") {
			public IBlockState getModelBlock() {
				return Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT,
						BlockStoneBrick.EnumType.CRACKED);
			}
		},
		CHISELED_STONEBRICK(5, "chiseled_brick", "chiseledbrick") {
			public IBlockState getModelBlock() {
				return Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT,
						BlockStoneBrick.EnumType.CHISELED);
			}
		};

		private static final BasicBlockCritter.EnumType[] META_LOOKUP = new BasicBlockCritter.EnumType[values().length];
		private final int meta;
		private final String name;
		private final String unlocalizedName;

		private EnumType(int meta, String name) {
			this(meta, name, name);
		}

		private EnumType(int meta, String name, String unlocalizedName) {
			this.meta = meta;
			this.name = name;
			this.unlocalizedName = unlocalizedName;
		}

		public int getMetadata() {
			return this.meta;
		}

		public String toString() {
			return this.name;
		}

		public static BasicBlockCritter.EnumType byMetadata(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}

			return META_LOOKUP[meta];
		}

		public String getName() {
			return this.name;
		}

		public String getUnlocalizedName() {
			return this.unlocalizedName;
		}

		public abstract IBlockState getModelBlock();

		public static BasicBlockCritter.EnumType forModelBlock(IBlockState model) {
			for (BasicBlockCritter.EnumType blocksilverfish$enumtype : values()) {
				if (model == blocksilverfish$enumtype.getModelBlock()) {
					return blocksilverfish$enumtype;
				}
			}

			return STONE;
		}

		static {
			for (BasicBlockCritter.EnumType blocksilverfish$enumtype : values()) {
				META_LOOKUP[blocksilverfish$enumtype.getMetadata()] = blocksilverfish$enumtype;
			}
		}
	}
}