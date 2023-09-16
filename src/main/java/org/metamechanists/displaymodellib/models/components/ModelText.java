package org.metamechanists.displaymodellib.models.components;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.TextDisplay;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3d;
import org.joml.Vector3f;
import org.metamechanists.displaymodellib.builders.TextDisplayBuilder;
import org.metamechanists.displaymodellib.transformations.TransformationMatrixBuilder;


@SuppressWarnings("unused")
@Getter
public class ModelText implements ModelComponent {
    private final TextDisplayBuilder main = new TextDisplayBuilder()
            .backgroundColor(Color.fromARGB(0, 0, 0, 0));

    private Vector3f location = new Vector3f();
    private Vector3f facing = new Vector3f(0, 0, 1);
    private Vector3f size = new Vector3f();
    private Vector3d rotation = new Vector3d();

    /**
     * @param location The center of the text
     */
    public ModelText location(@NotNull final Vector3f location) {
        this.location = location;
        return this;
    }
    /**
     * Sets the center of the text
     */
    public ModelText location(final float x, final float y, final float z) {
        return location(new Vector3f(x, y, z));
    }

    /**
     * Sets the orientation of the text (default is south AKA positive Z)
     * The player will only see the text when looking at it from this orientation
     */
    public ModelText facing(final @NotNull Vector3f facing) {
        this.facing = facing;
        return this;
    }
    /**
     * Sets the orientation of the text (default is south AKA positive Z)
     * The player will only see the text when looking at it from this orientation
     */
    public ModelText facing(final @NotNull BlockFace face) {
        return facing(face.getDirection().toVector3f());
    }

    /**
     * @param size The size of the text
     */
    public ModelText size(final float size) {
        this.size = new Vector3f(size);
        return this;
    }

    /**
     * @param rotation The rotation of the text in radians
     */
    public ModelText rotation(@NotNull final Vector3d rotation) {
        this.rotation = rotation;
        return this;
    }
    /**
     * Sets the rotation of the text in radians
     */
    public ModelText rotation(final double x, final double y, final double z) {
        return rotation(new Vector3d(x, y, z));
    }
    /**
     * @param rotationY The rotation of the text around the Y axis in radians
     */
    public ModelText rotation(final double rotationY) {
        return rotation(new Vector3d(0, rotationY, 0));
    }

    /**
     * @param color The background of the text (default is transparent)
     */
    public ModelText background(@NotNull final Color color) {
        main.backgroundColor(color);
        return this;
    }
    public ModelText text(@NotNull final String text) {
        main.text(text);
        return this;
    }
    public ModelText brightness(final int blockBrightness) {
        main.brightness(blockBrightness);
        return this;
    }
    public ModelText glow(@NotNull final Color color) {
        main.glow(color);
        return this;
    }

    public Matrix4f getMatrix() {
        return new TransformationMatrixBuilder()
                .lookAlong(facing)
                .translate(location)
                .rotate(rotation)
                .scale(new Vector3f(size))
                .buildForBlockDisplay();
    }
    @Override
    public TextDisplay build(@NotNull final Location origin) {
        return main.transformation(getMatrix()).build(origin);
    }
    @Override
    public TextDisplay build(@NotNull final Block block) {
        return build(block.getLocation());
    }
}
