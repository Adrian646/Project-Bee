package de.adrian.projectbee.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

public class PlayerModel {
    @Getter
    @Setter
    private UUID uuid;
    @Getter
    @Setter
    private int level;
    @Getter
    @Setter
    private int xp;

    private final Set<String> cosmetics;

    public PlayerModel(UUID uuid, int level, int xp, Set<String> cosmetics) {
        this.uuid = uuid;
        this.level = level;
        this.xp = xp;
        this.cosmetics = cosmetics;
    }

    public void addXP(int xp) {
        this.xp += xp;

        if (this.xp >= xpRequiredToLevelUp()) {
            levelUp();
        }
    }

    public int xpRequiredToLevelUp() {
        return (level + 1) * (level + 1);
    }

    public void levelUp() {
        int remainingXP = this.xp;
        int xpRequiredForNextLevel = xpRequiredToLevelUp();

        while (this.xp >= xpRequiredToLevelUp()) {
            this.level++;
            this.xp -= xpRequiredToLevelUp();
            System.out.println("Level: " + level + ", XP: " + xp);
        }

        if (remainingXP >= xpRequiredForNextLevel) {
            this.xp = remainingXP - xpRequiredForNextLevel;
        } else {
            this.xp = remainingXP;
        }
    }

    public void addCosmetic(String cosmeticId) {
        this.cosmetics.add(cosmeticId);
    }

    public void removeCosmetic(String cosmeticId) {
        this.cosmetics.remove(cosmeticId);
    }
}
