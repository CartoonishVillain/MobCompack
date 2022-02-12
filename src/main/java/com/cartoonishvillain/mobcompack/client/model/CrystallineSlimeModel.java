package com.cartoonishvillain.mobcompack.client.model;// Made with Blockbench 4.1.3
// Exported for Minecraft version 1.17 with Mojang mappings
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

public class CrystallineSlimeModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MobCompack.MOD_ID, "crystalineslime"), "main");
	private final ModelPart All;

	public CrystallineSlimeModel(ModelPart root) {
		this.All = root.getChild("All");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition All = partdefinition.addOrReplaceChild("All", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition inner = All.addOrReplaceChild("inner", CubeListBuilder.create().texOffs(0, 26).addBox(-5.0F, 13.0F, -4.0F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(40, 9).addBox(-1.0F, 19.0F, -4.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition eye1 = inner.addOrReplaceChild("eye1", CubeListBuilder.create().texOffs(39, 9).addBox(-1.0F, -1.0F, 0.1F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(39, 3).addBox(-1.0F, -1.0F, 0.1F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8F, 15.5F, -4.6F, 0.0F, 0.0F, 0.7854F));

		PartDefinition eye2 = inner.addOrReplaceChild("eye2", CubeListBuilder.create().texOffs(39, 9).mirror().addBox(-3.0F, -1.0F, -0.1F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(39, 3).mirror().addBox(-1.0F, -1.0F, -0.1F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.8F, 15.5F, -4.4F, 0.0F, 0.0F, -0.7854F));

		PartDefinition outer = All.addOrReplaceChild("outer", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, 11.0F, -6.0F, 13.0F, 13.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition crystal3 = outer.addOrReplaceChild("crystal3", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.6F, 11.6F, -2.3F, 0.0436F, 0.0F, -0.1745F));

		PartDefinition cube_r1 = crystal3.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(89, 31).addBox(-6.5F, -11.0F, 0.0F, 13.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r2 = crystal3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(89, 31).addBox(-6.5F, -11.0F, 0.0F, 13.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition crystal2 = outer.addOrReplaceChild("crystal2", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, 12.0F, 3.9F, -0.0873F, 0.0F, 0.0873F));

		PartDefinition cube_r3 = crystal2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(84, 42).addBox(-4.5F, -9.0F, 0.0F, 9.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r4 = crystal2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(84, 42).addBox(-4.5F, -9.0F, 0.0F, 9.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		All.render(poseStack, buffer, packedLight, packedOverlay);
	}
}