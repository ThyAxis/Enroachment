package net.thyaxis.enroachment.effects.beam;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import com.mojang.math.*;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.thyaxis.enroachment.Enroachment;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

import static com.mojang.blaze3d.vertex.VertexFormat.Mode.QUADS;
import static com.mojang.blaze3d.vertex.VertexFormat.Mode.TRIANGLES;
import static team.lodestar.lodestone.handlers.RenderHandler.DELAYED_RENDER;
import static team.lodestar.lodestone.registry.client.LodestoneRenderTypeRegistry.NO_CULL;
import static team.lodestar.lodestone.registry.client.LodestoneRenderTypeRegistry.TEXTURE;

public class Beam {
    public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(Enroachment.MODID, "textures/vfx/sphere/black.png");

    @SubscribeEvent
    public static void renderLevelStageEvent(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_SKY) return;
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        if (player != null) {
            Camera camera = event.getCamera();
            Vec3 cameraPos = ((Camera) camera).getPosition();
            Vec3 renderPos = new Vec3(1, -59, 19);

            float startWidth = 4.0f;
            float endWidth = 2.0f;
            float length = 10.0f;
            float height = 4.0f;

            PoseStack poseStack = event.getPoseStack();
            poseStack.pushPose();
            Vec3 position = renderPos.subtract(cameraPos);
            poseStack.translate(position.x, position.y, position.z);
            VFXBuilders.WorldVFXBuilder builder = VFXBuilders.createWorld().setPosColorTexLightmapDefaultFormat();
            VertexConsumer vertexConsumer = DELAYED_RENDER.getBuffer(TEXTURE.applyWithModifier(TEXTURE_LOCATION, b -> b.replaceVertexFormat(TRIANGLES).setCullState(NO_CULL)));

            renderGradientCuboid(vertexConsumer, poseStack, startWidth, endWidth, length, height);

            poseStack.popPose();
        }
    }

    private static void renderGradientCuboid(VertexConsumer vertexConsumer, PoseStack poseStack, float startWidth, float endWidth, float length, float height) {
        float halfStartWidth = startWidth / 2.0f;
        float halfEndWidth = endWidth / 2.0f;

        // Define vertices for the cuboid
        Vec3[] vertices = {
                new Vec3(-halfStartWidth, 0, 0),
                new Vec3(halfStartWidth, 0, 0),
                new Vec3(halfEndWidth, height, length),
                new Vec3(-halfEndWidth, height, length),

                new Vec3(-halfStartWidth, height, 0),
                new Vec3(halfStartWidth, height, 0),
                new Vec3(halfEndWidth, 0, length),
                new Vec3(-halfEndWidth, 0, length)
        };

        // Draw faces of the cuboid
        // Front face
        addVertex(vertexConsumer, poseStack, vertices[0], 0, 0, 1.0f, 0, 0, 1);
        addVertex(vertexConsumer, poseStack, vertices[1], 1, 0, 1.0f, 0, 0, 1);
        addVertex(vertexConsumer, poseStack, vertices[2], 1, 1, 1.0f, 0, 0, 1);
        addVertex(vertexConsumer, poseStack, vertices[3], 0, 1, 1.0f, 0, 0, 1);

        // Back face
        addVertex(vertexConsumer, poseStack, vertices[5], 1, 0, 0, 0, -1, 0);
        addVertex(vertexConsumer, poseStack, vertices[4], 0, 0, 0, 0, -1, 0);
        addVertex(vertexConsumer, poseStack, vertices[7], 0, 1, 0, 0, -1, 0);
        addVertex(vertexConsumer, poseStack, vertices[6], 1, 1, 0, 0, -1, 0);

        // Left face
        addVertex(vertexConsumer, poseStack, vertices[0], 0, 0, 0, 1, 0, 0);
        addVertex(vertexConsumer, poseStack, vertices[3], 1, 0, 0, 1, 0, 0);
        addVertex(vertexConsumer, poseStack, vertices[7], 1, 1, 0, 1, 0, 0);
        addVertex(vertexConsumer, poseStack, vertices[4], 0, 1, 0, 1, 0, 0);

        // Right face
        addVertex(vertexConsumer, poseStack, vertices[1], 1, 0, 1, 0, 0, -1);
        addVertex(vertexConsumer, poseStack, vertices[2], 0, 0, 1, 0, 0, -1);
        addVertex(vertexConsumer, poseStack, vertices[6], 0, 1, 1, 0, 0, -1);
        addVertex(vertexConsumer, poseStack, vertices[5], 1, 1, 1, 0, 0, -1);

        // Top face
        addVertex(vertexConsumer, poseStack, vertices[4], 0, 0, 1, 1, 0, 0);
        addVertex(vertexConsumer, poseStack, vertices[5], 1, 0, 1, 1, 0, 0);
        addVertex(vertexConsumer, poseStack, vertices[2], 1, 1, 1, 1, 0, 0);
        addVertex(vertexConsumer, poseStack, vertices[3], 0, 1, 1, 1, 0, 0);

        // Bottom face
        addVertex(vertexConsumer, poseStack, vertices[0], 0, 0, 1, -1, 0, 0);
        addVertex(vertexConsumer, poseStack, vertices[1], 1, 0, 1, -1, 0, 0);
        addVertex(vertexConsumer, poseStack, vertices[6], 1, 1, 1, -1, 0, 0);
        addVertex(vertexConsumer, poseStack, vertices[7], 0, 1, 1, -1, 0, 0);
    }

    private static void addVertex(VertexConsumer vertexConsumer, PoseStack poseStack, Vec3 pos, float u, float v, float red, float green, float blue, float alpha) {
        vertexConsumer.vertex(poseStack.last().pose(), (float) pos.x, (float) pos.y, (float) pos.z)
                .color(red, green, blue, alpha)
                .uv(u, v)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(15728880)
                .normal(poseStack.last().normal(), 0, 1, 0)
                .endVertex();
    }
}
