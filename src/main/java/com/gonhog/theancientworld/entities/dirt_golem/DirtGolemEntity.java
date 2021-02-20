package com.gonhog.theancientworld.entities.dirt_golem;

import com.gonhog.theancientworld.entities.TAWGolem;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class DirtGolemEntity extends TAWGolem implements IAnimatable {

    public DirtGolemEntity(EntityType<? extends TAWGolem> type, World worldIn) {
        super(type, worldIn);
    }
    private final AnimationFactory factory = new AnimationFactory(this);

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, .4D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.2D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 100.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4D);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

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

    protected void registerData() {
        super.registerData();
    }

    public boolean func_230264_L__() {
        return this.isAlive() && !this.isChild();
    }

    //change this
    public void func_241841_a(ServerWorld serverWorld,  LightningBoltEntity bolt) {
        if (serverWorld.getDifficulty() != Difficulty.PEACEFUL) {
            ZombifiedPiglinEntity zombifiedpiglinentity = EntityType.ZOMBIFIED_PIGLIN.create(serverWorld);
            zombifiedpiglinentity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
            zombifiedpiglinentity.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, this.rotationPitch);
            zombifiedpiglinentity.setNoAI(this.isAIDisabled());
            zombifiedpiglinentity.setChild(this.isChild());
            if (this.hasCustomName()) {
                zombifiedpiglinentity.setCustomName(this.getCustomName());
                zombifiedpiglinentity.setCustomNameVisible(this.isCustomNameVisible());
            }

            zombifiedpiglinentity.enablePersistence();
            serverWorld.addEntity(zombifiedpiglinentity);
            this.remove();
        } else {
            super.func_241841_a(serverWorld, bolt);
        }

    }

    @Override
    public double getMountedYOffset() {
        return 2.4;
    }


    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }
}
