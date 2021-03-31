package com.gonhog.theancientworld.entities.deer;

import com.gonhog.theancientworld.entities.astrid.AstridModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DeerRender extends GeoEntityRenderer<DeerEntity> {
    public DeerRender(EntityRendererManager renderManager)
    {
        super(renderManager, new DeerModel());
        this.shadowSize = .8F;
    }
}
