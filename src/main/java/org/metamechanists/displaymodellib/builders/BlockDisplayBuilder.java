package org.metamechanists.displaymodellib.builders;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display.Brightness;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

@SuppressWarnings("unused")
public class BlockDisplayBuilder implements DisplayBuilder {
    private Material material;
    private BlockData blockData;
    private Matrix4f transformation;
    private Color glowColor;
    private Integer brightness;
    private Float viewRange;

    @Override
    public BlockDisplay build(@NotNull final Location location) {
        return location.getWorld().spawn(location, BlockDisplay.class, display -> {
            if (material != null) {
                display.setBlock(material.createBlockData());
            }
            if (blockData != null) {
                display.setBlock(blockData);
            }
            if (transformation != null) {
                display.setTransformationMatrix(transformation);
            }
            if (glowColor != null) {
                display.setGlowing(true);
                display.setGlowColorOverride(glowColor);
            }
            if (brightness != null) {
                display.setBrightness(new Brightness(brightness, 0));
            }
            if (viewRange != null) {
                display.setViewRange(viewRange);
            }
            display.setDisplayWidth(0);
            display.setDisplayHeight(0);
        });
    }

    public BlockDisplayBuilder material(final Material material) {
        this.material = material;
        return this;
    }
    public BlockDisplayBuilder blockData(final BlockData blockData) {
        // Overrides material
        this.blockData = blockData;
        return this;
    }
    public BlockDisplayBuilder transformation(final Matrix4f transformation) {
        this.transformation = transformation;
        return this;
    }
    public BlockDisplayBuilder brightness(final int brightness) {
        this.brightness = brightness;
        return this;
    }
    public BlockDisplayBuilder glow(final Color glowColor) {
        this.glowColor = glowColor;
        return this;
    }
    public BlockDisplayBuilder viewRange(final float viewRange) {
        this.viewRange = viewRange;
        return this;
    }
}