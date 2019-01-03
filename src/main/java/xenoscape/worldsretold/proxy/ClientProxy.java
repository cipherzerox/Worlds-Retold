package xenoscape.worldsretold.proxy;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoscape.worldsretold.config.ConfigModules;
import xenoscape.worldsretold.hailstorm.entity.hostile.blizzard.EntityBlizzard;
import xenoscape.worldsretold.hailstorm.entity.hostile.blizzard.RenderBlizzard;
import xenoscape.worldsretold.hailstorm.entity.hostile.guardsman.EntityGuardsman;
import xenoscape.worldsretold.hailstorm.entity.hostile.guardsman.RenderGuardsman;
import xenoscape.worldsretold.hailstorm.entity.hostile.roller.EntitySnowRoller;
import xenoscape.worldsretold.hailstorm.entity.hostile.roller.RenderSnowRoller;
import xenoscape.worldsretold.hailstorm.entity.neutral.automaton.EntityAutomaton;
import xenoscape.worldsretold.hailstorm.entity.neutral.automaton.RenderAutomaton;
import xenoscape.worldsretold.hailstorm.entity.neutral.sentinel.EntitySentinel;
import xenoscape.worldsretold.hailstorm.entity.neutral.sentinel.RenderSentinel;
import xenoscape.worldsretold.hailstorm.entity.passive.nix.EntityNix;
import xenoscape.worldsretold.hailstorm.entity.passive.nix.RenderNix;
import xenoscape.worldsretold.hailstorm.entity.passive.penguin.EntityPenguin;
import xenoscape.worldsretold.hailstorm.entity.passive.penguin.RenderPenguin;
import xenoscape.worldsretold.hailstorm.entity.projectiles.black_arrow.EntityBlackArrow;
import xenoscape.worldsretold.hailstorm.entity.projectiles.black_arrow.RenderBlackArrow;
import xenoscape.worldsretold.hailstorm.entity.projectiles.egg.EntityPenguinEgg;
import xenoscape.worldsretold.hailstorm.entity.projectiles.egg.RenderPenguinEgg;
import xenoscape.worldsretold.hailstorm.entity.projectiles.frost_shot.EntityFrostShot;
import xenoscape.worldsretold.hailstorm.entity.projectiles.frost_shot.RenderFrostShot;
import xenoscape.worldsretold.hailstorm.entity.projectiles.hail.EntityHail;
import xenoscape.worldsretold.hailstorm.entity.projectiles.hail.RenderHail;
import xenoscape.worldsretold.hailstorm.entity.projectiles.scroll.EntityIceScrollProjectile;
import xenoscape.worldsretold.hailstorm.entity.projectiles.scroll.RenderIceScrollProjectile;
import xenoscape.worldsretold.hailstorm.init.HailstormBlocks;
import xenoscape.worldsretold.hailstorm.init.HailstormClientEvents;
import xenoscape.worldsretold.heatwave.entity.neutral.camel.EntityCamel;
import xenoscape.worldsretold.heatwave.entity.neutral.camel.RenderCamel;
import xenoscape.worldsretold.heatwave.entity.neutral.scorpion.EntityScorpion;
import xenoscape.worldsretold.heatwave.entity.neutral.scorpion.RenderScorpion;

public class ClientProxy extends ServerProxy {

	@Override
	protected void registerEventListeners() {
		super.registerEventListeners();
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void preInit(final FMLPreInitializationEvent event) {
		super.preInit(event);
		if (ConfigModules.isHailstormEnabled == true) {
			preInitHailstorm(event);
            MinecraftForge.EVENT_BUS.register(new HailstormClientEvents());
        }
		if (ConfigModules.isHeatwaveEnabled == true) {
			preInitHeatwave(event);
        }
	}

	public void preInitHailstorm(final FMLPreInitializationEvent event) {
		// Passive
		RenderingRegistry.registerEntityRenderingHandler(EntityPenguin.class, RenderPenguin.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityNix.class, RenderNix.FACTORY);

		// Neutral
		RenderingRegistry.registerEntityRenderingHandler(EntityAutomaton.class, RenderAutomaton.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntitySentinel.class, RenderSentinel.FACTORY);

		// Hostile
		RenderingRegistry.registerEntityRenderingHandler(EntityGuardsman.class, RenderGuardsman.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBlizzard.class, RenderBlizzard.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntitySnowRoller.class, RenderSnowRoller.FACTORY);

		// Projectile
		RenderingRegistry.registerEntityRenderingHandler(EntityBlackArrow.class, RenderBlackArrow.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHail.class, RenderHail.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityIceScrollProjectile.class,
				RenderIceScrollProjectile.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityFrostShot.class, RenderFrostShot.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPenguinEgg.class, RenderPenguinEgg.FACTORY);
	}
	
	public void preInitHeatwave(final FMLPreInitializationEvent event) {
		// Passive

		// Neutral
		RenderingRegistry.registerEntityRenderingHandler(EntityCamel.class, RenderCamel.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityScorpion.class, RenderScorpion.FACTORY);

		// Hostile

		// Projectile
	}

	@Override
	public void init(final FMLInitializationEvent event) {
		super.init(event);
		if (ConfigModules.isHailstormEnabled == true) {
			initHailstorm(event);
		}
	}

	public void initHailstorm(final FMLInitializationEvent event) {
		regColoursHailstorm();
	}

	@SideOnly(Side.CLIENT)
	public static void regColoursHailstorm() {
		FMLClientHandler.instance().getClient().getBlockColors().registerBlockColorHandler(new IBlockColor() {
			@Override
			public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
				return worldIn != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos)
						: ColorizerGrass.getGrassColor(0.5D, 1.0D);
			}
		}, HailstormBlocks.ARCTIC_WILLOW, HailstormBlocks.BOREAL_ORCHID);
	}

	@Override
	public void postInit(final FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}
