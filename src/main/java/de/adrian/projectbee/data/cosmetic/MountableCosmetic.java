package de.adrian.projectbee.data.cosmetic;

import de.adrian.projectbee.data.cosmetic.type.CosmeticType;
import lombok.Getter;

@Getter
public class MountableCosmetic extends Cosmetic {

    private final String entityIdentifier;

    public MountableCosmetic(int id, String name, int price, CosmeticType cosmeticType, String entityIdentifier) {
        super(id, name, price, cosmeticType);
        if (cosmeticType != CosmeticType.MOUNT) {
            throw new IllegalArgumentException("MountableCosmetic needs to hava the CosmeticType MOUNT");
        }
        this.entityIdentifier = entityIdentifier;
    }
}
