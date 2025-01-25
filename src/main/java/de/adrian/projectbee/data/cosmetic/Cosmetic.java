package de.adrian.projectbee.data.cosmetic;

import de.adrian.projectbee.data.cosmetic.type.CosmeticType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Cosmetic {
    private final int id;
    private final String name;
    private final int price;
    private final CosmeticType cosmeticType;
}
