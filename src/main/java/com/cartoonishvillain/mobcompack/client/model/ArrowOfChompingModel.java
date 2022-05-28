package com.cartoonishvillain.mobcompack.client.model;
// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.cartoonishvillain.mobcompack.MobCompack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ArrowOfChompingModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MobCompack.MOD_ID, "arrowofchomping"), "main");
	private final ModelPart all;

	public ArrowOfChompingModel(ModelPart root) {
		this.all = root.getChild("all");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create().texOffs(16, -12).addBox(0.0F, -6.0F, -6.0F, 0.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 2.0F));

		PartDefinition shaft2_r1 = all.addOrReplaceChild("shaft2_r1", CubeListBuilder.create().texOffs(16, -12).addBox(0.0F, -2.5F, -6.0F, 0.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition arrowhead = all.addOrReplaceChild("arrowhead", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 0.0F));

		PartDefinition top = arrowhead.addOrReplaceChild("top", CubeListBuilder.create().texOffs(16, 8).addBox(-3.0F, -2.0F, -6.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(34, 18).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 16).addBox(-3.0F, -3.0F, -6.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, -6.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition bottom = arrowhead.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(24, 25).addBox(-3.0F, 1.0F, -6.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(34, 10).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 24).addBox(-3.0F, -0.9F, -5.9F, 6.0F, 2.0F, 6.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -3.5F, -6.0F, 0.5672F, 0.0F, 0.0F));

		PartDefinition cube_r1 = bottom.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(-4, 0).addBox(-2.0F, 0.0F, -4.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 48, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}