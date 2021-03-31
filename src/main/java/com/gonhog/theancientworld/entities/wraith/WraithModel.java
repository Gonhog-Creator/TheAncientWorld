package com.gonhog.theancientworld.entities.wraith;

import com.gonhog.theancientworld.TheAncientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;

public class WraithModel extends AnimatedGeoModel<WraithEntity> {
    @Override
    public ResourceLocation getModelLocation(WraithEntity entity)
    {
        return new ResourceLocation(TheAncientWorld.MOD_ID, "geo/wraith.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(WraithEntity entity) {
        return new ResourceLocation(TheAncientWorld.MOD_ID, "textures/entities/wraith_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WraithEntity object)
    {
        return new ResourceLocation(TheAncientWorld.MOD_ID, "animations/wraith_animation.json");
    }

    @Override
    public void setLivingAnimations(WraithEntity entity, Integer uniqueID, AnimationEvent customPredicate)
    {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");
        LivingEntity entityIn = (LivingEntity) entity;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float)Math.PI / 300F));
        head.setRotationY(extraData.netHeadYaw * ((float)Math.PI / 180F));
    }


}
