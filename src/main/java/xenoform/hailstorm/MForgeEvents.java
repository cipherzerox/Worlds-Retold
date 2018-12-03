package xenoform.hailstorm;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntitySkeletonHorse;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.potion.Potion;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xenoform.hailstorm.base.BasicItemUniqueWeapon;
import xenoform.hailstorm.packets.PacketUniqueAttack;
import xenoform.hailstorm.render.FreezingRenderer.LayerFrozen;
import xenoform.hailstorm.util.RenderHelper;

import java.util.Random;

public class MForgeEvents {

	private static float partialTick;

	public static float getPartialTick() {
		return partialTick;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onPreRenderEntityLivingBase(final RenderLivingEvent.Pre event) {
		final EntityLivingBase player = event.getEntity();
		if (player.isPotionActive(MPotions.FREEZING)
				&& !RenderHelper.doesRendererHaveLayer((RenderLivingBase<?>) event.getRenderer(),
						(Class<? extends LayerRenderer<?>>) LayerFrozen.class, false)) {
			event.getRenderer().addLayer(
					(LayerRenderer) new LayerFrozen((RenderLivingBase<EntityLivingBase>) event.getRenderer()));
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onTartheusWeaponUse(final MouseEvent event) {
		if (event.getButton() == 0 && event.isButtonstate()) {
			final Minecraft mc = Minecraft.getMinecraft();
			final EntityPlayer thePlayer = mc.player;
			final RayTraceResult mov = Hailstorm.proxy.getMouseOver(4.5f);
			if (mov != null && mov.entityHit != null && mov.entityHit != thePlayer
					&& thePlayer.getHeldItemMainhand().getItem() instanceof BasicItemUniqueWeapon) {
				thePlayer.attackTargetEntityWithCurrentItem(mov.entityHit);
				Hailstorm.network.sendToServer(new PacketUniqueAttack(mov.entityHit.getEntityId()));
			}
		}
	}

	public static void attackwithUniqueWeapon(final EntityPlayer player, final Entity targetEntity) {
		if (!net.minecraftforge.common.ForgeHooks.onPlayerAttackTarget(player, targetEntity))
			return;
		if (targetEntity.canBeAttackedWithItem()) {
			if (!targetEntity.hitByEntity(player)) {
				float f = (float) player.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
				float f1;

				if (targetEntity instanceof EntityLivingBase) {
					f1 = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(),
							((EntityLivingBase) targetEntity).getCreatureAttribute());
				} else {
					f1 = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(),
							EnumCreatureAttribute.UNDEFINED);
				}

				float f2 = player.getCooledAttackStrength(0.5F);
				f = f * (0.1F + f2 * f2 * 0.9F);
				// Vanilla--- f = f * (0.2F + f2 * f2 * 0.8F);
				f1 = f1 * f2;
				player.resetCooldown();

				if (f > 0.0F || f1 > 0.0F) {
					boolean flag = f2 > 0.9F;
					boolean flag1 = false;
					int i = 0;
					i = i + EnchantmentHelper.getKnockbackModifier(player);

					if (player.isSprinting() && flag) {
						player.world.playSound(null, player.posX, player.posY, player.posZ,
								SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, player.getSoundCategory(), 1.0F, 1.0F);
						++i;
						flag1 = true;
					}

					boolean flag2 = flag && player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder()
							&& !player.isInWater() && !player.isPotionActive(MobEffects.BLINDNESS) && !player.isRiding()
							&& targetEntity instanceof EntityLivingBase;
					flag2 = flag2 && !player.isSprinting();

					net.minecraftforge.event.entity.player.CriticalHitEvent hitResult = net.minecraftforge.common.ForgeHooks
							.getCriticalHit(player, targetEntity, flag2, flag2 ? 1.5F : 1.0F);
					flag2 = hitResult != null;
					if (flag2) {
						f *= hitResult.getDamageModifier();
					}

					f = f + f1;
					boolean flag3 = false;
					double d0 = (double) (player.distanceWalkedModified - player.prevDistanceWalkedModified);

					if (flag && !flag2 && !flag1 && player.onGround && d0 < (double) player.getAIMoveSpeed()) {
						ItemStack itemstack = player.getHeldItem(EnumHand.MAIN_HAND);

						if (itemstack.getItem() instanceof BasicItemUniqueWeapon) {
							flag3 = true;
						}
					}

					float f4 = 0.0F;
					boolean flag4 = false;
					int j = EnchantmentHelper.getFireAspectModifier(player);

					if (targetEntity instanceof EntityLivingBase) {
						f4 = ((EntityLivingBase) targetEntity).getHealth();

						if (j > 0 && !targetEntity.isBurning()) {
							flag4 = true;
							targetEntity.setFire(1);
						}
					}

					double d1 = targetEntity.motionX;
					double d2 = targetEntity.motionY;
					double d3 = targetEntity.motionZ;
					boolean flag5 = targetEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), f);

					if (flag5) {
						if (i > 0) {
							if (targetEntity instanceof EntityLivingBase) {
								((EntityLivingBase) targetEntity).knockBack(player, (float) i * 0.5F,
										(double) MathHelper.sin(player.rotationYaw * 0.017453292F),
										(double) (-MathHelper.cos(player.rotationYaw * 0.017453292F)));
							} else {
								targetEntity.addVelocity(
										(double) (-MathHelper.sin(player.rotationYaw * 0.017453292F) * (float) i
												* 0.5F),
										0.1D, (double) (MathHelper.cos(player.rotationYaw * 0.017453292F) * (float) i
												* 0.5F));
							}

							player.motionX *= 0.6D;
							player.motionZ *= 0.6D;
							player.setSprinting(false);
						}

						if (flag3) {
							float f3 = 1.0F + EnchantmentHelper.getSweepingDamageRatio(player) * f;

							for (EntityLivingBase entitylivingbase : player.world.getEntitiesWithinAABB(
									EntityLivingBase.class,
									targetEntity.getEntityBoundingBox().grow(1.0D, 0.25D, 1.0D))) {
								if (entitylivingbase != player && entitylivingbase != targetEntity
										&& !player.isOnSameTeam(entitylivingbase)
										&& player.getDistanceSq(entitylivingbase) < 9.0D) {
									entitylivingbase.knockBack(player, 0.4F,
											(double) MathHelper.sin(player.rotationYaw * 0.017453292F),
											(double) (-MathHelper.cos(player.rotationYaw * 0.017453292F)));
									entitylivingbase.attackEntityFrom(DamageSource.causePlayerDamage(player), f3);
								}
							}

							// Hammer Explosion at full combat bar.
							player.world.createExplosion(null, targetEntity.posX, targetEntity.posY, targetEntity.posZ,
									0.5f, false);

							player.world.playSound(null, player.posX, player.posY, player.posZ,
									SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, player.getSoundCategory(), 1.0F, 1.0F);
							player.spawnSweepParticles();
						}

						if (targetEntity instanceof EntityPlayerMP && targetEntity.velocityChanged) {
							((EntityPlayerMP) targetEntity).connection
									.sendPacket(new SPacketEntityVelocity(targetEntity));
							targetEntity.velocityChanged = false;
							targetEntity.motionX = d1;
							targetEntity.motionY = d2;
							targetEntity.motionZ = d3;
						}

						if (flag2) {
							player.world.playSound(null, player.posX, player.posY, player.posZ,
									SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, player.getSoundCategory(), 1.0F, 1.0F);
							player.onCriticalHit(targetEntity);
						}

						if (!flag2 && !flag3) {
							if (flag) {
								player.world.playSound(null, player.posX, player.posY, player.posZ,
										SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, player.getSoundCategory(), 1.0F, 1.0F);
							} else {
								player.world.playSound(null, player.posX, player.posY, player.posZ,
										SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, player.getSoundCategory(), 1.0F, 1.0F);
							}
						}

						if (f1 > 0.0F) {
							player.onEnchantmentCritical(targetEntity);
						}

						player.setLastAttackedEntity(targetEntity);

						if (targetEntity instanceof EntityLivingBase) {
							EnchantmentHelper.applyThornEnchantments((EntityLivingBase) targetEntity, player);
						}

						EnchantmentHelper.applyArthropodEnchantments(player, targetEntity);
						ItemStack itemstack1 = player.getHeldItemMainhand();
						Entity entity = targetEntity;

						if (targetEntity instanceof MultiPartEntityPart) {
							IEntityMultiPart ientitymultipart = ((MultiPartEntityPart) targetEntity).parent;

							if (ientitymultipart instanceof EntityLivingBase) {
								entity = (EntityLivingBase) ientitymultipart;
							}
						}

						if (!itemstack1.isEmpty() && entity instanceof EntityLivingBase) {
							ItemStack beforeHitCopy = itemstack1.copy();
							itemstack1.hitEntity((EntityLivingBase) entity, player);

							if (itemstack1.isEmpty()) {
								net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, beforeHitCopy,
										EnumHand.MAIN_HAND);
								player.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
							}
						}

						if (targetEntity instanceof EntityLivingBase) {
							float f5 = f4 - ((EntityLivingBase) targetEntity).getHealth();
							player.addStat(StatList.DAMAGE_DEALT, Math.round(f5 * 10.0F));

							if (j > 0) {
								targetEntity.setFire(j * 4);
							}

							if (player.world instanceof WorldServer && f5 > 2.0F) {
								int k = (int) ((double) f5 * 0.5D);
								((WorldServer) player.world).spawnParticle(EnumParticleTypes.DAMAGE_INDICATOR,
										targetEntity.posX, targetEntity.posY + (double) (targetEntity.height * 0.5F),
										targetEntity.posZ, k, 0.1D, 0.0D, 0.1D, 0.2D);
							}
						}

						player.addExhaustion(0.1F);
					} else {
						player.world.playSound(null, player.posX, player.posY, player.posZ,
								SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, player.getSoundCategory(), 1.0F, 1.0F);

						if (flag4) {
							targetEntity.extinguish();
						}
					}
				}
			}
		}
	}
}