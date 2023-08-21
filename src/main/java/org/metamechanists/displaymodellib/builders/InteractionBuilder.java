package org.metamechanists.displaymodellib.builders;

import org.bukkit.Location;
import org.bukkit.entity.Interaction;
import org.jetbrains.annotations.NotNull;


public class InteractionBuilder {
    private Float width;
    private Float height;

    public Interaction build(@NotNull final Location location) {
        final Location finalLocation = location.clone();

        // Account for Mojang deciding to center the entity on Y but not X and Z for some reason
        if (height != null) {
            finalLocation.subtract(0, height/2, 0);
        }

        return location.getWorld().spawn(finalLocation, Interaction.class, interaction -> {
            if (width != null) {
                interaction.setInteractionWidth(width);
            }
            if (height != null) {
                interaction.setInteractionHeight(height);
            }
        });
    }

    public InteractionBuilder width(final float width) {
        this.width = width;
        return this;
    }
    public InteractionBuilder height(final float height) {
        this.height = height;
        return this;
    }
}
