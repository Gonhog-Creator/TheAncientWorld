package com.gonhog.theancientworld.entities.wraith;

import com.gonhog.theancientworld.entities.deer.DeerEntity;
import com.gonhog.theancientworld.entities.deer.DeerModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class WraithRender extends GeoEntityRenderer<WraithEntity>{
    public WraithRender(EntityRendererManager renderManager)
    {
        super(renderManager, new WraithModel());
        this.shadowSize = .6F;
    }

    @Override
    public RenderType getRenderType(WraithEntity animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.getEntityTranslucent(textureLocation);
    }
}
