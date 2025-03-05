package dev.imb11.shields.client;

import com.github.crimsondawn45.fabricshieldlib.lib.event.ShieldSetModelCallback;
import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricBannerShieldItem;
import com.mojang.serialization.MapCodec;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

//? if <1.21.3 {
/*import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
*///?} else {
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldModelRenderer;
//?}

public class ShieldsClient implements ClientModInitializer {
    @ApiStatus.Internal
    public static ArrayList<Material> REGISTERED_MATERIALS = new ArrayList<>();

    public static void registerDynamicShield(String id, FabricBannerShieldItem shieldItem) {
        ResourceLocation resourceLocation = ResourceLocation.tryBuild("shields", id);
        ModelLayerLocation modelLayer = new ModelLayerLocation(resourceLocation, "main");

        AtomicReference<ShieldModel> modelShield = new AtomicReference<>();
        Material shieldBase = new Material(TextureAtlas.LOCATION_BLOCKS, ResourceLocation.tryBuild("shields", "entity/" + id + "_base"));
        Material shieldBaseNoPattern = new Material(TextureAtlas.LOCATION_BLOCKS, ResourceLocation.tryBuild("shields", "entity/" + id + "_base_nopattern"));

        REGISTERED_MATERIALS.addAll(List.of(shieldBase, shieldBaseNoPattern));

        EntityModelLayerRegistry.registerModelLayer(modelLayer, ShieldModel::createLayer);

        ShieldSetModelCallback.EVENT.register((loader) -> {
            modelShield.set(new ShieldModel(loader.bakeLayer(modelLayer)));
            return InteractionResult.PASS;
        });

        //? if <1.21.3 {
        /*BuiltinItemRendererRegistry.INSTANCE.register(shieldItem, (stack, mode, matrices, vertexConsumers, light, overlay) -> {
            if (modelShield.get() == null) return;
            com.github.crimsondawn45.fabricshieldlib.initializers.FabricShieldLibClient.renderBanner(stack, matrices, vertexConsumers, light, overlay, modelShield.get(), shieldBase, shieldBaseNoPattern);
        });
        *///?} else {
        SpecialModelRenderers.ID_MAPPER.put(resourceLocation, MapCodec.unit(new FabricShieldModelRenderer.Unbaked()));
        //?}
    }

    @Override
    public void onInitializeClient() {
    }
}
