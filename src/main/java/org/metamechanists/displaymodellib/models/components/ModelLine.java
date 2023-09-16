package org.metamechanists.displaymodellib.models.components;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.metamechanists.displaymodellib.builders.BlockDisplayBuilder;
import org.metamechanists.displaymodellib.transformations.TransformationMatrixBuilder;
import org.metamechanists.displaymodellib.transformations.TransformationUtils;


@SuppressWarnings("unused")
@Getter
public class ModelLine implements ModelComponent {
    private final BlockDisplayBuilder main = new BlockDisplayBuilder();

    private Vector3f from = new Vector3f();
    private Vector3f to = new Vector3f();
    private float thickness;
    private float extraLength;
    private double roll;

    /**
     * @param from The start point of the line
     */
    public ModelLine from(final Vector3f from) {
        this.from = from;
        return this;
    }
    /**
     * Sets the start point of the line
     */
    public ModelLine from(final float x, final float y, final float z) {
        return from(new Vector3f(x, y, z));
    }
    /**
     * @param from The end point of the line
     */
    public ModelLine to(final Vector3f to) {
        this.to = to;
        return this;
    }
    /**
     * Sets the end point of the line
     */
    public ModelLine to(final float x, final float y, final float z) {
        return to(new Vector3f(x, y, z));
    }
    /**
     * @param from How thick the line is from one side to the other
     */
    public ModelLine thickness(final float thickness) {
        this.thickness = thickness;
        return this;
    }
    /**
     * @param from How much further than the from/to locations the line should extend (each end has extraLength/2 added to it)
     */
    public ModelLine extraLength(final float extraLength) {
        this.extraLength = extraLength;
        return this;
    }

    /**
     * @param roll The angle by which the line should be rotated about its length
     */
    public ModelLine roll(final double roll) {
        this.roll = extraLength;
        return this;
    }

    /**
     * Overrides material
     */
    public ModelLine block(@NotNull final BlockData block) {
        main.blockData(block);
        return this;
    }
    public ModelLine material(@NotNull final Material material) {
        main.material(material);
        return this;
    }
    public ModelLine brightness(final int blockBrightness) {
        main.brightness(blockBrightness);
        return this;
    }
    public ModelLine glow(@NotNull final Color color) {
        main.glow(color);
        return this;
    }

    public Matrix4f getMatrix() {
        final Vector3f midpoint = TransformationUtils.getMidpoint(from, to);
        return new TransformationMatrixBuilder()
                .translate(midpoint)
                .lookAlong(from, to)
                .rotate(0, 0, roll)
                .scale(new Vector3f(thickness, thickness, from.distance(to) + extraLength))
                .buildForBlockDisplay();
    }
    @Override
    public BlockDisplay build(@NotNull final Location origin) {
        return main.transformation(getMatrix()).build(origin);
    }
    @Override
    public BlockDisplay build(@NotNull final Block block) {
        return build(block.getLocation());
    }
}
