package com.gonhog.theancientworld.entities.satyr;

import com.gonhog.theancientworld.TheAncientWorld;
import com.gonhog.theancientworld.entities.centaur.CentaurEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;

public class SatyrModel extends AnimatedGeoModel<SatyrEntity> {

	@Override
	public ResourceLocation getModelLocation(SatyrEntity entity)
	{
		return new ResourceLocation(TheAncientWorld.MOD_ID, "geo/satyr.geo.json");
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(SatyrEntity entity) {
		return new ResourceLocation(TheAncientWorld.MOD_ID, "textures/entities/satyr_brown.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(SatyrEntity object)
	{
		return new ResourceLocation(TheAncientWorld.MOD_ID, "animations/satyr_animation.json");
	}

	@Override
	public void setLivingAnimations(SatyrEntity entity, Integer uniqueID, AnimationEvent customPredicate)
	{
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("head");
		LivingEntity entityIn = (LivingEntity) entity;
		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		head.setRotationX(extraData.headPitch * ((float)Math.PI / 180F));
		head.setRotationY(extraData.netHeadYaw * ((float)Math.PI / 180F));
	}
}