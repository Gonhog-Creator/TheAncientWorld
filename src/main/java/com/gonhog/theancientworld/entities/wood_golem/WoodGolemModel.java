package com.gonhog.theancientworld.entities.wood_golem;

import com.gonhog.theancientworld.TheAncientWorld;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;

public class WoodGolemModel extends AnimatedGeoModel<WoodGolemEntity> {

    @Override
    public ResourceLocation getModelLocation(WoodGolemEntity entity)
    {
        return new ResourceLocation(TheAncientWorld.MOD_ID, "geo/wood_golem.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(WoodGolemEntity entity) {
        return new ResourceLocation(TheAncientWorld.MOD_ID, "textures/entities/wood_golem.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WoodGolemEntity object)
    {
        return new ResourceLocation(TheAncientWorld.MOD_ID, "animations/wood_golem_animation.json");
    }

    @Override
    public void setLivingAnimations(WoodGolemEntity entity, Integer uniqueID, AnimationEvent customPredicate)
    {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * (0.014F));
        head.setRotationY(extraData.netHeadYaw * (0.014F));

    }
}
