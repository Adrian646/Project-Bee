package de.adrian.projectbee.data.cosmetic.type;

import de.adrian.projectbee.data.messages.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CosmeticType {
    HAT("Hats" ,Messages.HAT_DESCRIPTION.format()),
    BACKBLING("Backblings", Messages.BACKBLING_DESCRIPTION.format()),
    MOUNT("Mounts", Messages.MOUNT_DESCRIPTION.format());

    private final String name;
    private final String description;
}
