package de.adrian.projectbee.data.cosmetic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Cosmetic {
    private final String id;
    private final String name;
    private final CosmeticType type;
}
