package org.metamechanists.displaymodellib.transformations;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3d;
import org.joml.Vector3f;
import org.metamechanists.displaymodellib.transformations.components.LookAlong;
import org.metamechanists.displaymodellib.transformations.components.Rotation;
import org.metamechanists.displaymodellib.transformations.components.Scale;
import org.metamechanists.displaymodellib.transformations.components.TransformationMatrixComponent;
import org.metamechanists.displaymodellib.transformations.components.Translation;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * Builds a transformation matrix by combining {@link TransformationMatrixComponent}s
 */
@SuppressWarnings("unused")
public class TransformationMatrixBuilder {
    private static final Vector3f BLOCK_DISPLAY_ADJUSTMENT = new Vector3f(-0.5F);

    private final Deque<TransformationMatrixComponent> components = new ArrayDeque<>();

    /**
     * Represents a translation in X, Y, and Z.
     */
    public TransformationMatrixBuilder translate(final @NotNull Vector3f translation) {
        components.addLast(new Translation(translation));
        return this;
    }
    /**
     * Represents a translation in X, Y, and Z.
     */
    public TransformationMatrixBuilder translate(final float x, final float y, final float z) {
        translate(new Vector3f(x, y, z));
        return this;
    }

    /**
     * Represents a scale transformation in X, Y, and Z.
     */
    public TransformationMatrixBuilder scale(final @NotNull Vector3f scale) {
        components.addLast(new Scale(scale));
        return this;
    }
    /**
     * Represents a scale transformation in X, Y, and Z.
     */
    public TransformationMatrixBuilder scale(final float x, final float y, final float z) {
        scale(new Vector3f(x, y, z));
        return this;
    }

    /**
     * Represents a rotation in X, Y, and Z (angles in radians)
     * The rotation takes a Vector3d instead of a Vector3f because most rotations are in terms of Math.PI, which is a double
     */
    public TransformationMatrixBuilder rotate(final @NotNull Vector3d rotation) {
        components.addLast(new Rotation(rotation));
        return this;
    }
    /**
     * Represents a rotation in X, Y, and Z (angles in radians)
     */
    public TransformationMatrixBuilder rotate(final double x, final double y, final double z) {
        rotate(new Vector3d(x, y, z));
        return this;
    }

    /**
     * Represents a look-along transformation without any roll. To visualise what this transformation does, imagine a player in-game rotating their head.
     */
    public TransformationMatrixBuilder lookAlong(final @NotNull Vector3f direction) {
        components.addLast(new LookAlong(direction));
        return this;
    }
    /**
     * Represents a look-along transformation without any roll. To visualise what this transformation does, imagine a player in-game rotating their head.
     */
    public TransformationMatrixBuilder lookAlong(final @NotNull Location from, final @NotNull Location to) {
        components.addLast(new LookAlong(from, to));
        return this;
    }
    /**
     * Represents a look-along transformation without any roll. To visualise what this transformation does, imagine a player in-game rotating their head.
     */
    public TransformationMatrixBuilder lookAlong(final @NotNull Vector3f from, final @NotNull Vector3f to) {
        components.addLast(new LookAlong(from, to));
        return this;
    }
    /**
     * Represents a look-along transformation without any roll. To visualise what this transformation does, imagine a player in-game rotating their head.
     */
    public TransformationMatrixBuilder lookAlong(final @NotNull BlockFace face) {
        components.addLast(new LookAlong(face.getDirection().toVector3f()));
        return this;
    }

    private @NotNull Matrix4f build() {
        final Matrix4f matrix = new Matrix4f();

        // Apply each transformation in turn
        while (!components.isEmpty()) {
            components.removeFirst().apply(matrix);
        }

        return matrix;
    }

    /**
     * Adjusts the transformation so that the transformation acts on the center of the block display; otherwise it would act on a corner, which is usually less useful
     * @return The matrix representing the transformation formed by all the components
     */
    public @NotNull Matrix4f buildForBlockDisplay() {
        components.addLast(new Translation(BLOCK_DISPLAY_ADJUSTMENT));
        return build();
    }
    /**
     * Adjusts the transformation so that the transformation acts on the center of the block display; by default the Y-axis is shifted
     * @return The matrix representing the transformation formed by all the components
     */
    public @NotNull Matrix4f buildForItemDisplay() {
        return build();
    }
    /**
     * @return The matrix representing the transformation formed by all the components
     */
    public @NotNull Matrix4f buildForTextDisplay() {
        return build();
    }
}
