package com.gonhog.theancientworld.entities.quick_hit_arrow;

import com.gonhog.theancientworld.util.RegistryHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class QuickHitArrowEntity extends AbstractArrowEntity {



    protected QuickHitArrowEntity(EntityType<? extends AbstractArrowEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    public QuickHitArrowEntity(World worldIn, LivingEntity shooter) {
        super(RegistryHandler.QUICK_HIT_ARROW.get(), shooter, worldIn);
    }

    public QuickHitArrowEntity(EntityType<QuickHitArrowEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack((IItemProvider) RegistryHandler.QUICK_HIT_ARROW.get());
    }

    @Override
    protected void arrowHit(LivingEntity living) {
        living.hurtResistantTime = 0;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
