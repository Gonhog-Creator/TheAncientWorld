package com.gonhog.theancientworld.entities.unicorn;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;


public class UnicornRender extends GeoEntityRenderer<UnicornEntity>
{
    public UnicornRender(EntityRendererManager renderManager)
    {
        super(renderManager, new UnicornModel());
        this.shadowSize = 0.7F;
    }
}
