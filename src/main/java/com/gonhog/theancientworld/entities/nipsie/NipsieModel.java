package com.gonhog.theancientworld.entities.nipsie;

import com.gonhog.theancientworld.TheAncientWorld;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import javax.annotation.Nullable;

public class NipsieModel extends AnimatedGeoModel<NipsieEntity> {

    @Override
    public ResourceLocation getModelLocation(NipsieEntity entity)
    {
        return new ResourceLocation(TheAncientWorld.MOD_ID, "geo/nipsie.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(NipsieEntity entity) {
        return new ResourceLocation(TheAncientWorld.MOD_ID, "textures/entities/nipsie.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(NipsieEntity object)
    {
        return new ResourceLocation(TheAncientWorld.MOD_ID, "animations/nipsie_animation.json");
    }
}
