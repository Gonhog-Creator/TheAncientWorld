package com.gonhog.theancientworld.entities.wood_golem;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class WoodGolemRender extends GeoEntityRenderer<WoodGolemEntity> {

    public WoodGolemRender(EntityRendererManager renderManager)
    {
        super(renderManager, new WoodGolemModel());
        this.shadowSize = .9F;
    }
}
