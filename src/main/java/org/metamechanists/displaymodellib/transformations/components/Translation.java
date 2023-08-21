package org.metamechanists.displaymodellib.transformations.components;

import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Translation implements TransformationMatrixComponent {
    private final Vector3f translation;

    public Translation(@NotNull final Vector3f translation) {
        this.translation = translation;
    }

    @Override
    public void apply(@NotNull final Matrix4f matrix) {
        matrix.translate(translation);
    }
}
