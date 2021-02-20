package com.gonhog.theancientworld.entities.satyr;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SatyrRender extends GeoEntityRenderer<SatyrEntity>
{
    public SatyrRender(EntityRendererManager renderManager)
    {
        super(renderManager, new SatyrModel());
        this.shadowSize = .5F;
    }
}

