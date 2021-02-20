package com.gonhog.theancientworld.entities.quick_hit_arrow;

import com.gonhog.theancientworld.TheAncientWorld;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class QuickHitArrowRender extends ArrowRenderer<QuickHitArrowEntity> {

    private static final ResourceLocation QUICK_HIT_ARROW_TEXTURE = new ResourceLocation(TheAncientWorld.MOD_ID,
            "textures/entities/projectiles/quick_hit_arrow.png");

    public QuickHitArrowRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getEntityTexture(QuickHitArrowEntity entity) {
        return QUICK_HIT_ARROW_TEXTURE;
    }
}
