package com.gonhog.theancientworld.entities.nipsie;

import com.gonhog.theancientworld.entities.centaur.CentaurEntity;
import com.gonhog.theancientworld.entities.centaur.CentaurModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class NipsieRender extends GeoEntityRenderer<NipsieEntity>
{
    public NipsieRender(EntityRendererManager renderManager)
    {
        super(renderManager, new NipsieModel());
        this.shadowSize = .1F;
    }
}

