package com.gonhog.theancientworld.entities.centaur;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CentaurRender extends GeoEntityRenderer<CentaurEntity>
{
    public CentaurRender(EntityRendererManager renderManager)
    {
        super(renderManager, new CentaurModel());
        this.shadowSize = .7F;
    }
}
