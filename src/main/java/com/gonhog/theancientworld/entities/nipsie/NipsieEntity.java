package com.gonhog.theancientworld.entities.nipsie;

import com.gonhog.theancientworld.util.RegistryHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class NipsieEntity extends VillagerEntity implements IAnimatable {

    public NipsieEntity(EntityType<? extends VillagerEntity> type, World worldIn) {
        super(type, worldIn);
    }
    private final AnimationFactory factory = new AnimationFactory(this);

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, .4D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.2D));
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

        if (event.isMoving() && limbSwingAmount > .35F) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("running", true));
            return PlayState.CONTINUE;
        }
        if (event.isMoving() && limbSwingAmount > 0.001F) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walking", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }



    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    protected SoundEvent getHurtSound( DamageSource damageSourceIn) {
        super.getHurtSound(damageSourceIn);
        return SoundEvents.ENTITY_GENERIC_HURT;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }
}
