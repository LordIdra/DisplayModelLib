package org.metamechanists.displaymodellib.models;

import dev.sefiraat.sefilib.entity.display.DisplayGroup;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.metamechanists.displaymodellib.models.components.ModelComponent;

import java.util.HashMap;
import java.util.Map;


/**
 * Builder class that allows you to construct a model using components
 */
public class ModelBuilder {
    private final Map<String, ModelComponent> components = new HashMap<>();

    public ModelBuilder add(@NotNull final String name, @NotNull final ModelComponent component) {
        components.put(name, component);
        return this;
    }

    /**
     * Creates all the components and adds them to a displaygroup
     * The components are build with all coordinates relative to 'center'
     * @param center The center location of the model
     * @return The display group containing all the components
     */
    public DisplayGroup buildAtLocation(@NotNull final Location center) {
        final DisplayGroup group = new DisplayGroup(center.clone(), 0, 0);
        components.forEach((name, component) -> group.addDisplay(name, component.build(center.clone())));
        return group;
    }
    /**
     * Creates all the components and adds them to a displaygroup
     * The components are build with all coordinates relative to 'blockLocation'
     * @param blockLocation The block location of the model (this will be converted to the center of the block and used to build the model)
     * @return The display group containing all the components
     */
    public DisplayGroup buildAtBlockCenter(@NotNull final Location blockLocation) {
        final DisplayGroup group = new DisplayGroup(blockLocation.clone(), 0, 0);
        components.forEach((name, component) -> group.addDisplay(name, component.build(blockLocation.clone().add(0.5, 0.5, 0.5))));
        return group;
    }
}
