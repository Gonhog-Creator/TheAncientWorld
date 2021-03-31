package com.gonhog.theancientworld.entities.astrid;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AstridRender extends GeoEntityRenderer<AstridEntity> {
    public AstridRender(EntityRendererManager renderManager)
    {
        super(renderManager, new AstridModel());
        this.shadowSize = .2F;
    }
}
