package com.gonhog.theancientworld.entities.centaur;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.horse.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class CentaurEntity extends AbstractHorseEntity implements IAnimatable {

    private static final DataParameter<Integer> CENTAUR_VARIANT = EntityDataManager.createKey(CentaurEntity.class, DataSerializers.VARINT);
    private final AnimationFactory factory = new AnimationFactory(this);

    private int tailSwing;
    private int tailCounter;

    public CentaurEntity(EntityType<? extends CentaurEntity> type, World worldIn) {
        super(type, worldIn);
        this.ignoreFrustumCheck = true;
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 8.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5);

    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 0.8D));
        this.goalSelector.addGoal(2, new RunAroundLikeCrazyGoal(this, 0.8D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.4D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.initExtraAI();
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
        if (this.tailCounter == 5 && !event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("tail_swing", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    public void tick() {
        super.tick();
        if (this.tailSwing > 100) {
            tailSwing = 0;
        } else if (this.tailSwing < 10) {
            this.tailCounter = 5;
            ++tailSwing;
        }
        if (this.tailSwing > 10) {
            this.tailCounter = 0;
        }
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(CENTAUR_VARIANT, 0);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("Variant", this.func_234241_eS_());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.func_234242_w_(compound.getInt("Variant"));
    }

    private void func_234242_w_(int p_234242_1_) {
        this.dataManager.set(CENTAUR_VARIANT, p_234242_1_);
    }

    private int func_234241_eS_() {
        return this.dataManager.get(CENTAUR_VARIANT);
    }

    private void func_234238_a_(CoatColors p_234238_1_, CoatTypes p_234238_2_) {
        this.func_234242_w_(p_234238_1_.getId() & 255 | p_234238_2_.getId() << 8 & '\uff00');
    }

    public CoatColors getRandomCoatColor() {
        return CoatColors.func_234254_a_(this.func_234241_eS_() & 255);
    }

    public CoatTypes func_234240_eM_() {
        return CoatTypes.func_234248_a_((this.func_234241_eS_() & '\uff00') >> 8);
    }

    protected SoundEvent getAmbientSound() {
        super.getAmbientSound();
        return SoundEvents.ENTITY_HORSE_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        super.getDeathSound();
        return SoundEvents.ENTITY_HORSE_DEATH;
    }

    @Nullable
    protected SoundEvent func_230274_fe_() {
        return SoundEvents.ENTITY_HORSE_EAT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        super.getHurtSound(damageSourceIn);
        return SoundEvents.ENTITY_HORSE_HURT;
    }

    protected SoundEvent getAngrySound() {
        super.getAngrySound();
        return SoundEvents.ENTITY_HORSE_ANGRY;
    }

    public ActionResultType func_230254_b_(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getHeldItem(p_230254_2_);
        if (!this.isChild()) {
            if (this.isTame() && p_230254_1_.isSecondaryUseActive()) {
                this.openGUI(p_230254_1_);
                return ActionResultType.func_233537_a_(this.world.isRemote);
            }

            if (this.isBeingRidden()) {
                return super.func_230254_b_(p_230254_1_, p_230254_2_);
            }
        }

        if (!itemstack.isEmpty()) {
            if (this.isBreedingItem(itemstack)) {
                return this.func_241395_b_(p_230254_1_, itemstack);
            }

            ActionResultType actionresulttype = itemstack.interactWithEntity(p_230254_1_, this, p_230254_2_);
            if (actionresulttype.isSuccessOrConsume()) {
                return actionresulttype;
            }

            if (!this.isTame()) {
                this.makeMad();
                return ActionResultType.func_233537_a_(this.world.isRemote);
            }

            boolean flag = !this.isChild() && !this.isHorseSaddled() && itemstack.getItem() == Items.SADDLE;
            if (this.isArmor(itemstack) || flag) {
                this.openGUI(p_230254_1_);
                return ActionResultType.func_233537_a_(this.world.isRemote);
            }
        }

        if (this.isChild()) {
            return super.func_230254_b_(p_230254_1_, p_230254_2_);
        } else {
            this.mountTo(p_230254_1_);
            return ActionResultType.func_233537_a_(this.world.isRemote);
        }
    }

    public boolean func_230276_fq_() {
        return true;
    }

    @Nullable
    public ILivingEntityData onInitialSpawn( IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        CoatColors coatcolors;
        if (spawnDataIn instanceof CentaurData) {
            coatcolors = ((CentaurData)spawnDataIn).variant;
        } else {
            coatcolors = Util.getRandomObject(CoatColors.values(), this.rand);
            spawnDataIn = new CentaurData(coatcolors);
        }

        this.func_234238_a_(coatcolors, Util.getRandomObject(CoatTypes.values(), this.rand));
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public static class CentaurData extends AgeableEntity.AgeableData {
        public final CoatColors variant;

        public CentaurData(CoatColors p_i231557_1_) {
            super(false);
            this.variant = p_i231557_1_;
        }
    }
}

