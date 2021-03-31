package com.gonhog.theancientworld.entities.astrid;

import com.gonhog.theancientworld.TheAncientWorld;
import com.gonhog.theancientworld.entities.satyr.SatyrEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;

public class AstridModel extends AnimatedGeoModel<AstridEntity> {
    @Override
    public ResourceLocation getModelLocation(AstridEntity entity)
    {
        return new ResourceLocation(TheAncientWorld.MOD_ID, "geo/astrid.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(AstridEntity entity) {
        return new ResourceLocation(TheAncientWorld.MOD_ID, "textures/entities/astrid_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AstridEntity object)
    {
        return new ResourceLocation(TheAncientWorld.MOD_ID, "animations/astrid_animation.json");
    }

    @Override
    public void setLivingAnimations(AstridEntity entity, Integer uniqueID, AnimationEvent customPredicate)
    {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");
        LivingEntity entityIn = (LivingEntity) entity;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float)Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float)Math.PI / 180F));
    }
}
