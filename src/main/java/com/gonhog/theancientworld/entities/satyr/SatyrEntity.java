package com.gonhog.theancientworld.entities.satyr;

import com.gonhog.theancientworld.TheAncientWorld;
import com.gonhog.theancientworld.util.RegistryHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.core.jmx.Server;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;


public class SatyrEntity extends CowEntity implements IAnimatable {

    public SatyrEntity(EntityType<? extends CowEntity> type, World worldIn) {
        super(type, worldIn);
    }
    private final AnimationFactory factory = new AnimationFactory(this);

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.25D, Ingredient.fromItems(RegistryHandler.RAW_SATYR_MEAT.get()), false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 4.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {

        if (event.isMoving() && limbSwingAmount > .75F) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("running", true));
            return PlayState.CONTINUE;
        }
        if (event.isMoving() && limbSwingAmount > 0.1F) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walking", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        super.getHurtSound(damageSourceIn);
        return SoundEvents.ENTITY_GENERIC_HURT;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SHEEP_AMBIENT;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    public static boolean canSpawnAt(EntityType<SatyrEntity> entityType, IServerWorld world, SpawnReason reason, BlockPos pos, Random random) {
        if(canSpawnOn(entityType, world,reason,pos,random)) {
            BlockState blockState = world.getBlockState(pos);
            ServerWorld serverWorld = world.getWorld();
            BlockPos blockpos = pos.down();

            return world.getBlockState(blockpos).isIn(RegistryHandler.SATYR_AIR.get());

            //if (serverWorld.func_241827_a(SectionPos.from(pos), RegistryHandler.FABLEHAVEN.get()).findAny().isPresent()) {
            //return blockState.isIn(RegistryHandler.ADAMANT_BLOCK.get());
            //}
        }
        return world.getBlockState(pos.down()).isIn(RegistryHandler.SATYR_AIR.get());
        //return false;
    }
}


