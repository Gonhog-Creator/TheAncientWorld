package com.gonhog.theancientworld.entities.centaur;

import com.gonhog.theancientworld.TheAncientWorld;
import com.google.common.collect.Maps;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.horse.CoatColors;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;
import java.util.Map;

public class CentaurModel extends AnimatedGeoModel<CentaurEntity> {
    @Override
    public ResourceLocation getModelLocation(CentaurEntity entity)
    {
        return new ResourceLocation(TheAncientWorld.MOD_ID, "geo/centaur.geo.json");
    }

    private static final Map<CoatColors, ResourceLocation> randomCoatColor = Util.make(Maps.newEnumMap(CoatColors.class), (p_239384_0_) -> {
        p_239384_0_.put(CoatColors.WHITE, new ResourceLocation(TheAncientWorld.MOD_ID,"textures/entities/centaur_white.png"));
        p_239384_0_.put(CoatColors.CREAMY, new ResourceLocation(TheAncientWorld.MOD_ID,"textures/entities/centaur_creamy.png"));
        p_239384_0_.put(CoatColors.CHESTNUT, new ResourceLocation(TheAncientWorld.MOD_ID,"textures/entities/centaur_chestnut.png"));
        p_239384_0_.put(CoatColors.BROWN, new ResourceLocation(TheAncientWorld.MOD_ID,"textures/entities/centaur_brown.png"));
        p_239384_0_.put(CoatColors.BLACK, new ResourceLocation(TheAncientWorld.MOD_ID,"textures/entities/centaur_black.png"));
        p_239384_0_.put(CoatColors.GRAY, new ResourceLocation(TheAncientWorld.MOD_ID,"textures/entities/centaur_gray.png"));
        p_239384_0_.put(CoatColors.DARKBROWN, new ResourceLocation(TheAncientWorld.MOD_ID,"textures/entities/centaur_darkbrown.png"));
    });

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(CentaurEntity entity) {
        return randomCoatColor.get(entity.getRandomCoatColor());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CentaurEntity object)
    {
        return new ResourceLocation(TheAncientWorld.MOD_ID, "animations/centaur_animation.json");
    }

    @Override
    public void setLivingAnimations(CentaurEntity entity, Integer uniqueID, AnimationEvent customPredicate)
    {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");
        LivingEntity entityIn = (LivingEntity) entity;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float)Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float)Math.PI / 180F));
    }
}
