// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


public class slime extends EntityModel<Entity> {
	private final ModelRenderer inner;
	private final ModelRenderer eye1;
	private final ModelRenderer eye2;
	private final ModelRenderer outer;
	private final ModelRenderer crystal3;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer crystal2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;

	public slime() {
		textureWidth = 128;
		textureHeight = 128;

		inner = new ModelRenderer(this);
		inner.setRotationPoint(0.6F, -0.5F, 0.0F);
		inner.setTextureOffset(0, 26).addBox(-5.0F, 13.0F, -4.0F, 9.0F, 9.0F, 9.0F, 0.0F, false);
		inner.setTextureOffset(40, 9).addBox(-1.0F, 19.0F, -4.5F, 1.0F, 2.0F, 2.0F, 0.0F, false);

		eye1 = new ModelRenderer(this);
		eye1.setRotationPoint(-3.8F, 15.5F, -4.6F);
		inner.addChild(eye1);
		setRotationAngle(eye1, 0.0F, 0.0F, 0.7854F);
		eye1.setTextureOffset(39, 9).addBox(-1.0F, -1.0F, 0.1F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		eye1.setTextureOffset(39, 3).addBox(-1.0F, -1.0F, 0.1F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		eye2 = new ModelRenderer(this);
		eye2.setRotationPoint(2.8F, 15.5F, -4.4F);
		inner.addChild(eye2);
		setRotationAngle(eye2, 0.0F, 0.0F, -0.7854F);
		eye2.setTextureOffset(39, 9).addBox(-3.0F, -1.0F, -0.1F, 4.0F, 2.0F, 2.0F, 0.0F, true);
		eye2.setTextureOffset(39, 3).addBox(-1.0F, -1.0F, -0.1F, 2.0F, 4.0F, 2.0F, 0.0F, true);

		outer = new ModelRenderer(this);
		outer.setRotationPoint(0.0F, -1.0F, 0.0F);
		outer.setTextureOffset(0, 64).addBox(-7.0F, 11.0F, -6.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);

		crystal3 = new ModelRenderer(this);
		crystal3.setRotationPoint(-3.6F, 11.6F, -2.3F);
		outer.addChild(crystal3);
		setRotationAngle(crystal3, 0.0436F, 0.0F, -0.1745F);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		crystal3.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.7854F, 0.0F);
		cube_r1.setTextureOffset(89, 31).addBox(-6.5F, -11.0F, 0.0F, 13.0F, 11.0F, 0.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		crystal3.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, -0.7854F, 0.0F);
		cube_r2.setTextureOffset(89, 31).addBox(-6.5F, -11.0F, 0.0F, 13.0F, 11.0F, 0.0F, 0.0F, false);

		crystal2 = new ModelRenderer(this);
		crystal2.setRotationPoint(-0.5F, 12.0F, 3.9F);
		outer.addChild(crystal2);
		setRotationAngle(crystal2, -0.0873F, 0.0F, 0.0873F);
		

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		crystal2.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.7854F, 0.0F);
		cube_r3.setTextureOffset(84, 42).addBox(-4.5F, -9.0F, 0.0F, 9.0F, 9.0F, 0.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
		crystal2.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, -0.7854F, 0.0F);
		cube_r4.setTextureOffset(84, 42).addBox(-4.5F, -9.0F, 0.0F, 9.0F, 9.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		inner.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		outer.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}