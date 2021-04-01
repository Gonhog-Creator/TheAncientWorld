package com.gonhog.theancientworld.tools;

import com.gonhog.theancientworld.TheAncientWorld;
import com.gonhog.theancientworld.entities.quick_hit_arrow.QuickHitArrowEntity;
import com.gonhog.theancientworld.util.RegistryHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import javax.annotation.Nullable;
import java.util.List;

public class BowOfPlenty extends BowItem {
    public static boolean arrowsRecharge;

    public BowOfPlenty(Properties properties) {
        super(new Item.Properties()
                .group(TheAncientWorld.TAB)
                .maxStackSize(1)
                .rarity(Rarity.EPIC)
                .isImmuneToFire()
                );
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {

        CompoundNBT nbt = stack.getOrCreateTag();

        int getArrowsLeft = nbt.getInt("arrowsLeft");
        int getArrowsShooting = nbt.getInt("arrowsShooting");


        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity) entityLiving;
            ItemStack itemstack = playerentity.findAmmo(stack);

            int i = this.getUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, true);
            if (i < 0) return;

            if (itemstack.isEmpty()) {
                itemstack = new ItemStack(Items.ARROW);
            }

            float f = getArrowVelocity(i);
            if (!((double) f < 0.1D)) {
                if (!worldIn.isRemote) {
                    if (getArrowsShooting <= getArrowsLeft) {
                        int tempArrowCount = 0;
                        for (int arrowShot = 1; arrowShot <= getArrowsShooting; arrowShot++) {

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

                            abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.ALLOWED;

                            worldIn.addEntity(abstractarrowentity);
                            tempArrowCount = arrowShot;
                        }
                        nbt.putInt("arrowsLeft", getArrowsLeft - tempArrowCount);
                    }
                }

                worldIn.playSound((PlayerEntity) null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                playerentity.addStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (stack.hasTag()) {
            assert stack.getTag() != null;
            tooltip.add(new StringTextComponent(Integer.toString(stack.getTag().getInt("arrowsLeft"))));
        }
    }


    public void inventoryTick(ItemStack stack, World world, Entity entity, int i, boolean flag) {
        CompoundNBT nbt = stack.getOrCreateTag();
        if (!nbt.contains("arrowsLeft")) {
            nbt.putInt("arrowsLeft", 300);
        }
        if (!nbt.contains("arrowsShooting")) {
            nbt.putInt("arrowsShooting", 1);
        }

        int getArrowsLeft = nbt.getInt("arrowsLeft");
        int getArrowsShooting = nbt.getInt("arrowsShooting");

        if (arrowsRecharge && !(getArrowsLeft == 300)) {
            nbt.putInt("arrowsLeft", 300);
        }
        if (getArrowsShooting == 0) {
            nbt.putInt("arrowsShooting", 1);
        }
    }

    private QuickHitArrowEntity customeArrow(QuickHitArrowEntity arrow) {
        return arrow;
    }

    @SubscribeEvent
    public static void ArrowRecharge(TickEvent.WorldTickEvent event) {
        arrowsRecharge = event.world.getDayTime() % 24000 == 1;
    }

    public static void setArrows(int messageInt, ItemStack stack) {
        stack.getOrCreateTag().putInt("arrowsShooting", messageInt);
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
