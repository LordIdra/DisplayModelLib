package org.metamechanists.displaymodellib.models.components;

import lombok.Getter;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3d;
import org.joml.Vector3f;
import org.metamechanists.displaymodellib.builders.BlockDisplayBuilder;
import org.metamechanists.displaymodellib.transformations.TransformationMatrixBuilder;


/**
 * Represents a diamond (no not that kind of diamond... this is a rotated cube)
 */
@SuppressWarnings("unused")
@Getter
public class ModelDiamond implements ModelComponent {
    public static final Vector3d ROTATION = new Vector3d(-0.955, 0.785, 0);

    private final BlockDisplayBuilder main = new BlockDisplayBuilder();

    private Vector3f location = new Vector3f();
    private Vector3f facing = new Vector3f(0, 0, 1);
    private Vector3f size = new Vector3f();

    /**
     * @param location The center of the diamond
     */
    public ModelDiamond location(@NotNull final Vector3f location) {
        this.location = location;
        return this;
    }
    /**
     * Sets the center of the diamond
     */
    public ModelDiamond location(final float x, final float y, final float z) {
        return location(new Vector3f(x, y, z));
    }

    /**
     * Sets the starting orientation of the diamond (default is south AKA positive Z)
     * This is useful eg to align a model with the direction a player is looking
     */
    public ModelDiamond facing(final @NotNull Vector3f facing) {
        this.facing = facing;
        return this;
    }
    /**
     * Sets the starting orientation of the diamond (default is south AKA positive Z)
     * This is useful eg to align a model with the direction a player is looking
     */
    public ModelDiamond facing(final @NotNull BlockFace face) {
        return facing(face.getDirection().toVector3f());
    }

    /**
     * @param size The size of the cuboid (ie: the distance from one side to the other) on each axis
     */
    public ModelDiamond size(final float size) {
        // The scale() function takes the scale as the side from one face to the next
        // But we actually want the size to be inputted as the distance from one corner to the opposite corner
        // We can accomplish this with basic pythagoras
        this.size = new Vector3f((float) Math.sqrt(2 * Math.pow(size/2, 2)));
        return this;
    }

    /**
     * Overrides material
     */
    public ModelDiamond block(@NotNull final BlockData block) {
        main.blockData(block);
        return this;
    }
    public ModelDiamond material(@NotNull final Material material) {
        main.material(material);
        return this;
    }
    public ModelDiamond brightness(final int blockBrightness) {
        main.brightness(blockBrightness);
        return this;
    }
    public ModelDiamond glow(@NotNull final Color color) {
        main.glow(color);
        return this;
    }

    public Matrix4f getMatrix() {
        return new TransformationMatrixBuilder()
                .lookAlong(facing)
                .translate(location)
                .rotate(ROTATION)
                .scale(size)
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
