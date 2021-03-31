package com.gonhog.theancientworld.entities.deer;

import com.gonhog.theancientworld.TheAncientWorld;
import com.gonhog.theancientworld.entities.astrid.AstridEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;
import java.util.Random;

public class DeerModel extends AnimatedGeoModel<DeerEntity> {
    @Override
    public ResourceLocation getModelLocation(DeerEntity entity)
    {
        return new ResourceLocation(TheAncientWorld.MOD_ID, "geo/deer.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(DeerEntity entity) {
        if (entity.getGender().equals("male")) {
            return new ResourceLocation(TheAncientWorld.MOD_ID, "textures/entities/deer_male_texture.png");
        } else {
            return new ResourceLocation(TheAncientWorld.MOD_ID, "textures/entities/deer_female_texture.png");
        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DeerEntity object)
    {
        return new ResourceLocation(TheAncientWorld.MOD_ID, "animations/deer_animation.json");
    }

    @Override
    public void setLivingAnimations(DeerEntity entity, Integer uniqueID, AnimationEvent customPredicate)
    {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("neck");
        LivingEntity entityIn = (LivingEntity) entity;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float)Math.PI / 20000F));
        head.setRotationY(extraData.netHeadYaw * ((float)Math.PI / 200F));
    }
}
