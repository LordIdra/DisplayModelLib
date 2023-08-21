package org.metamechanists.displaymodellib.transformations;

import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class TransformationUtils {
    private final List<BlockFace> AXIS = new ArrayList<>(List.of(
            BlockFace.NORTH,
            BlockFace.EAST,
            BlockFace.SOUTH,
            BlockFace.WEST
    ));

    /**
     * Rounds yaw to a cardinal direction (ie: rounds to 0, pi/2, pi, 3pi/2, etc radians)
     */
    @SuppressWarnings("MagicNumber")
    public double yawToCardinalDirection(final float yaw) {
        return -Math.round(yaw / 90.0F) * (Math.PI/2);
    }
    /**
     * Rounds yaw to the nearest {@link BlockFace}
     */
    @SuppressWarnings("MagicNumber")
    public @NotNull BlockFace yawToFace(final float yaw) {
        return AXIS.get(Math.round(yaw / 90.0F) & 0x3);
    }

    /**
     * Takes an initial radius and rotates it around all three axes. The initial vector is (0, 0, radius)
     */
    public Vector3f rotatedRadius(final float radius, final float x, final float y, final float z) {
        return new Vector3f(0, 0, radius).rotateX(x).rotateY(y).rotateZ(z);
    }
    /**
     * Takes an initial radius and rotates it around only the Y-axis (the most common rotation type)
     */
    public Vector3f rotatedRadius(final float radius, final double y) {
        return new Vector3f(0, 0, radius).rotateY((float) y);
    }

    public @NotNull Vector3f getDisplacement(final Location from, @NotNull final Location to) {
        return to.clone().subtract(from).toVector().toVector3f();
    }
    public @NotNull Vector3f getDisplacement(final Vector3f from, @NotNull final Vector3f to) {
        return new Vector3f(to).sub(from);
    }

    public @NotNull Vector3f getDirection(@NotNull final Location from, @NotNull final Location to) {
        return getDisplacement(from, to).normalize();
    }
    public @NotNull Vector3f getDirection(@NotNull final Vector3f from, @NotNull final Vector3f to) {
        return getDisplacement(from, to).normalize();
    }

    public @NotNull Location getMidpoint(@NotNull final Location from, @NotNull final Location to) {
        return from.clone().add(to).multiply(0.5);
    }
    public @NotNull Vector3f getMidpoint(@NotNull final Vector3f from, @NotNull final Vector3f to) {
        return new Vector3f(from).add(to).mul(0.5F);
    }
}
