package org.metamechanists.displaymodellib.transformations.components;

import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Scale implements TransformationMatrixComponent {
    private final Vector3f scale;

    public Scale(@NotNull final Vector3f scale) {
        this.scale = scale;
    }

    @Override
    public void apply(@NotNull final Matrix4f matrix) {
        matrix.scale(scale);
    }
}
