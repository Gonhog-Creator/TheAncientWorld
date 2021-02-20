    package com.gonhog.theancientworld.entities;


    import net.minecraft.entity.*;
    import net.minecraft.entity.ai.attributes.Attributes;
    import net.minecraft.entity.passive.AnimalEntity;
    import net.minecraft.entity.player.PlayerEntity;
    import net.minecraft.inventory.IInventory;
    import net.minecraft.inventory.IInventoryChangedListener;
    import net.minecraft.item.ItemStack;
    import net.minecraft.item.Items;
    import net.minecraft.potion.Effects;
    import net.minecraft.util.*;
    import net.minecraft.util.math.MathHelper;
    import net.minecraft.util.math.vector.Vector3d;
    import net.minecraft.world.World;
    import net.minecraft.world.server.ServerWorld;


    import javax.annotation.Nullable;
    import java.util.UUID;

    public class TAWGolem extends AnimalEntity implements IAngerable, IInventoryChangedListener, IJumpingMount, IEquipable {

    protected boolean horseJumping;
    protected float jumpPower;
    private boolean allowStandSliding;
    private UUID angerTarget;

    public TAWGolem(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
        this.stepHeight = 1.5F;
    }


    public ActionResultType func_230254_b_( PlayerEntity entity, Hand hand) {
        if (this.isHorseSaddled() && !this.isBeingRidden() && !entity.isSecondaryUseActive()) {
            if (!this.world.isRemote) {
                entity.startRiding(this);
            }

            return ActionResultType.func_233537_a_(this.world.isRemote);
        } else {
            ActionResultType actionresulttype = super.func_230254_b_(entity, hand);
            if (!actionresulttype.isSuccessOrConsume()) {
                ItemStack itemstack = entity.getHeldItem(hand);
                return itemstack.getItem() == Items.SADDLE ? itemstack.interactWithEntity(entity, this, hand) : ActionResultType.PASS;
            } else {
                return actionresulttype;
            }
        }
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity entity) {
        return null;
    }

    @Override
    public int getAngerTime() {
        return 0;
    }

    @Override
    public void setAngerTime(int time) {

    }

    @Nullable
    @Override
    public UUID getAngerTarget() {
        return this.angerTarget;
    }

    @Override
    public void setAngerTarget(@Nullable UUID target) {
        this.angerTarget = target;
    }

    @Override
    public void func_230258_H__() {
    }

    @Override
    public boolean func_230264_L__() {
        return false;
    }

    @Override
    public void func_230266_a_(@Nullable SoundCategory p_230266_1_) {

    }

    @Override
    public boolean canBePushed() {
        return !this.isBeingRidden();
    }

    @Override
    public boolean isHorseSaddled() {
        return true;
    }

    @Override
    public void setJumpPower(int jumpPowerIn) {
        if (this.isHorseSaddled()) {
            if (jumpPowerIn < 0) {
                jumpPowerIn = 0;
            } else {
                this.allowStandSliding = true;
            }

            if (jumpPowerIn >= 90) {
                this.jumpPower = 1.0F;
            } else {
                this.jumpPower = 0.4F + 0.4F * (float)jumpPowerIn / 90.0F;
            }

        }
    }

    @Override
    public boolean canJump() {
        return this.isHorseSaddled();
    }

    @Override
    public void handleStartJump(int jumpPower) {
        this.allowStandSliding = true;
    }

    @Override
    public void handleStopJump() {

    }

    @Override
    public void onInventoryChanged(IInventory invBasic) {

    }

    @Override
    public boolean shouldRiderSit() {
        return true;
    }

    @Override
    public boolean canBeRiddenInWater(Entity rider) {
        return false;
    }

    @Nullable
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    public boolean canBeSteered() {
            return this.getControllingPassenger() instanceof LivingEntity;
        }


    public void travel(Vector3d travelVector) {
        if (this.isAlive()) {
            if (this.isBeingRidden() && this.canBeSteered() && this.isHorseSaddled()) {
                LivingEntity livingentity = (LivingEntity) this.getControllingPassenger();
                assert livingentity != null;

                //handles rotation of entity in accordance of controlling passenger
                this.rotationYaw = livingentity.rotationYaw;
                this.prevRotationYaw = this.rotationYaw;
                this.rotationPitch = livingentity.rotationPitch * 0.5F;
                this.setRotation(this.rotationYaw, this.rotationPitch);
                this.renderYawOffset = this.rotationYaw;
                this.rotationYawHead = this.renderYawOffset;

                //controls movement of entity in accordance of passenger
                float golemStrafeSpeed = livingentity.moveStrafing * 0.5F;
                float golemForwardSpeed = livingentity.moveForward/2;

                //controls jumping
                if (this.jumpPower > 0.0F && !this.isHorseJumping() && this.onGround) {
                    double jumpHeight = this.getGolemJumpStrength() * (double)this.jumpPower * (double)this.getJumpFactor();
                    double jumpStrength;
                    if (this.isPotionActive(Effects.JUMP_BOOST)) {
                        jumpStrength = jumpHeight + (double)((float)(this.getActivePotionEffect(Effects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
                    } else {
                        jumpStrength = jumpHeight;
                    }

                    Vector3d vector3d = this.getMotion();
                    this.setMotion(vector3d.x, jumpStrength, vector3d.z);
                    this.setHorseJumping(true);
                    this.isAirBorne = true;
                    net.minecraftforge.common.ForgeHooks.onLivingJump(this);
                    if (golemForwardSpeed > 0.0F) {
                        float f2 = MathHelper.sin(this.rotationYaw * ((float)Math.PI / 180F));
                        float f3 = MathHelper.cos(this.rotationYaw * ((float)Math.PI / 180F));
                        this.setMotion(this.getMotion().add((double)(-0.4F * f2 * this.jumpPower), 0.0D, (double)(0.4F * f3 * this.jumpPower)));
                    }
                    this.jumpPower = 0.0F;
                }

                this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

                //controls movement
                if (this.canPassengerSteer()) {
                    this.setAIMoveSpeed((float)this.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    super.travel(new Vector3d( golemStrafeSpeed, travelVector.y, golemForwardSpeed));
                } else if (livingentity instanceof PlayerEntity) {
                    this.setMotion(Vector3d.ZERO);
                }

                if (this.onGround) {
                    this.jumpPower = 0.0F;
                    this.setHorseJumping(false);
                }

                this.func_233629_a_(this, false);

            } else {
                this.jumpMovementFactor = 0.02F;
                super.travel(travelVector);
            }
        }
    }

    private double getGolemJumpStrength() {
        return .7;
    }

    public boolean isHorseJumping() {
        return this.horseJumping;
    }

    public void setHorseJumping(boolean jumping) {
        this.horseJumping = jumping;
    }

}

