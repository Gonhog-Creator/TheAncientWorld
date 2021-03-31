package com.gonhog.theancientworld.entities.astrid;

import com.gonhog.theancientworld.util.RegistryHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AstridEntity extends ParrotEntity implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);
    public AstridEntity(EntityType<? extends ParrotEntity> type, World worldIn) {
        super(type, worldIn);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (event.isMoving() && limbSwingAmount > 0.1F) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("flying", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public boolean isTamed() {
        return false;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, PlayerEntity.class, 20.0F, 3D, 2D));
        this.goalSelector.addGoal(9, new AvoidEntityGoal<>(this, PlayerEntity.class, 20.0F, 3D, 2D));
        this.goalSelector.addGoal(2, new PanicGoal(this, .8D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomFlyingGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                .createMutableAttribute(Attributes.FLYING_SPEED, 5D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5D);

    }
}
