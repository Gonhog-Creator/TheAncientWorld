package com.gonhog.theancientworld.entities.dirt_golem;

import com.gonhog.theancientworld.entities.nipsie.NipsieModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DirtGolemRender extends GeoEntityRenderer<DirtGolemEntity> {

    public DirtGolemRender(EntityRendererManager renderManager)
    {
        super(renderManager, new DirtGolemModel());
        this.shadowSize = 1F;
    }
}
