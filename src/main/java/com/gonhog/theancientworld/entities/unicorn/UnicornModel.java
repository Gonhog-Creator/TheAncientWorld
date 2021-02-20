package com.gonhog.theancientworld.entities.unicorn;

import com.gonhog.theancientworld.TheAncientWorld;
import com.gonhog.theancientworld.entities.centaur.CentaurEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;

public class UnicornModel extends AnimatedGeoModel<UnicornEntity> {

	@Override
	public ResourceLocation getModelLocation(UnicornEntity entity)
	{
		return new ResourceLocation(TheAncientWorld.MOD_ID, "geo/unicorn.geo.json");
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(UnicornEntity entity) {
		return new ResourceLocation(TheAncientWorld.MOD_ID, "textures/entities/unicorn_white.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(UnicornEntity object)
	{
		return new ResourceLocation(TheAncientWorld.MOD_ID, "animations/unicorn_animation.json");
	}

	@Override
	public void setLivingAnimations(UnicornEntity entity, Integer uniqueID, AnimationEvent customPredicate)
	{
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("neck");
		LivingEntity entityIn = (LivingEntity) entity;
		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		head.setRotationX(extraData.headPitch * ((float)Math.PI / 20000F));
		head.setRotationY(extraData.netHeadYaw * ((float)Math.PI / 200F));
	}
}