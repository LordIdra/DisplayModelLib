package org.metamechanists.displaymodellib.transformations.components;

import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

@FunctionalInterface
public interface TransformationMatrixComponent {
    void apply(@NotNull final Matrix4f matrix);
}
