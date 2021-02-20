package com.gonhog.theancientworld.tools;

import com.gonhog.theancientworld.TheAncientWorld;
import com.gonhog.theancientworld.entities.quick_hit_arrow.QuickHitArrowEntity;
import com.gonhog.theancientworld.util.RegistryHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.NBTTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import javax.annotation.Nullable;
import java.util.List;

public class BowOfArrows extends BowItem {
    private static int arrows;
    private static int arrowsLeft;
    private static INBT arrowsleft;
    public static boolean arrowsRecharge;

    public BowOfArrows(Properties properties) {
        super(new Item.Properties()
                .group(TheAncientWorld.TAB)
                .maxStackSize(1)
                .rarity(Rarity.EPIC)
                .isImmuneToFire()
                );
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (arrowsRecharge) {
            arrowsLeft = 300;
            arrowsRecharge = false;
        }

        CompoundNBT nbt;
        if (stack.hasTag()) {
            nbt = stack.getTag();
        } else {
            nbt = new CompoundNBT();
        }

        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity) entityLiving;
            boolean flag = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = playerentity.findAmmo(stack);

            if (arrows == 0) {
                arrows = 1;
            }

            int i = this.getUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(Items.ARROW);
                }

                float f = getArrowVelocity(i);
                if (!((double) f < 0.1D)) {
                    boolean flag1 = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem) itemstack.getItem()).isInfinite(itemstack, stack, playerentity));
                    if (!worldIn.isRemote) {
                        System.out.println(arrowsLeft);
                        if (arrows <= arrowsLeft) {
                            for (int arrowShot = 1; arrowShot <= arrows; arrowShot++) {
                                QuickHitArrowEntity abstractarrowentity = createArrow(worldIn, itemstack, playerentity);
                                abstractarrowentity = customeArrow(abstractarrowentity);

                                abstractarrowentity.func_234612_a_(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F);
                                if (f == 1.0F) {
                                    abstractarrowentity.setIsCritical(true);
                                }

                                int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                                if (j > 0) {
                                    abstractarrowentity.setDamage(abstractarrowentity.getDamage() + (double) j * 0.5D + 0.5D);
                                }

                                int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
                                if (k > 0) {
                                    abstractarrowentity.setKnockbackStrength(k);
                                }

                                if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                                    abstractarrowentity.setFire(100);
                                }

                                stack.damageItem(1, playerentity, (p_220009_1_) -> {
                                    p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
                                });
                                if (flag1 || playerentity.abilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)) {
                                    abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                                }


                                worldIn.addEntity(abstractarrowentity);
                                arrowsLeft = arrowsLeft - 1;
                                assert nbt != null;
                                if (nbt.contains("Uses")) {
                                    nbt.put("Uses", arrowsLeft);
                                }

                                stack.setTag(nbt);
                            }
                            System.out.println(arrowsLeft);
                        }
                    }

                    worldIn.playSound((PlayerEntity) null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!flag1 && !playerentity.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            playerentity.inventory.deleteStack(itemstack);
                        }
                    }

                    playerentity.addStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (stack.hasTag()) {
            assert stack.getTag() != null;
            if (!stack.getTag().contains("Uses")) {
                tooltip.add(new StringTextComponent(Integer.toString(stack.getTag().getInt("Uses"))));
            }
        }
    }


    private QuickHitArrowEntity customeArrow(QuickHitArrowEntity arrow) {
        return arrow;
    }

    @SubscribeEvent
    public static void ArrowRecharge(TickEvent.WorldTickEvent event) {
        if (event.world.getDayTime() % 24000 == 1) {
            arrowsRecharge = true;
        }
    }

    @SubscribeEvent
    public static void BowOfArrow(FMLClientSetupEvent event) {
        ItemModelsProperties.registerProperty(RegistryHandler.BOW_OF_ARROWS.get(), new ResourceLocation("theancientworld:bow_of_arrows_pull"), (p_239429_0_, p_239429_1_, p_239429_2_) -> {
            if (p_239429_2_ == null) {
                return 0.0F;
            } else {
                return p_239429_2_.getActiveItemStack() != p_239429_0_ ? 0.0F : (float) (p_239429_0_.getUseDuration() - p_239429_2_.getItemInUseCount()) / 20.0F;
            }
        });
        ItemModelsProperties.registerProperty(RegistryHandler.BOW_OF_ARROWS.get(), new ResourceLocation("theancientworld:bow_of_arrows_pulling"), (p_239428_0_, p_239428_1_, p_239428_2_) -> {
            return p_239428_2_ != null && p_239428_2_.isHandActive() && p_239428_2_.getActiveItemStack() == p_239428_0_ ? 1.0F : 0.0F;
        });
    }

    public static void setArrows(int messageInt) {
        arrows = messageInt;
    }

    private QuickHitArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        return new QuickHitArrowEntity(worldIn, shooter);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isDamageable() {
        return false;
    }


}
