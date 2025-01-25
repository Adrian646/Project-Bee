package de.adrian.projectbee.model;

import de.adrian.projectbee.data.cosmetic.Cosmetic;
import de.adrian.projectbee.score.CurrencyScoreboard;
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
    @Getter
    @Setter
    private int coins;
    @Getter
    @Setter
    private CurrencyScoreboard currencyScoreboard;

    private final Set<Cosmetic> cosmetics;

    public PlayerModel(UUID uuid, int level, int xp, int coins, CurrencyScoreboard currencyScoreboard, Set<Cosmetic> cosmetics) {
        this.uuid = uuid;
        this.level = level;
        this.xp = xp;
        this.coins = coins;
        this.currencyScoreboard = currencyScoreboard;
        this.cosmetics = cosmetics;
    }

    public void addXP(int xp) {
        this.xp += xp;

        while (this.xp >= xpRequiredToLevelUp()) {
            levelUp();
        }
    }

    public int xpRequiredToLevelUp() {
        if (this.level <= 10) {
            return (int) (50 * Math.pow(this.level + 1, 1.2));
        } else {
            return (int) (100 * Math.pow(this.level, 1.4));
        }
    }

    public int coinsRewardForLevelUp() {
        return (int) (100 * Math.pow(1.1, this.level));
    }

    public void levelUp() {
        this.xp -= xpRequiredToLevelUp();
        this.level++;
        int reward = coinsRewardForLevelUp();
        this.coins += coinsRewardForLevelUp();
        System.out.println("Level Up! New Level: " + this.level + ". Coins Rewarded: " + reward);
        this.currencyScoreboard.updateTitle();
    }

    public double getLevelProgress() {
        return (double) this.xp / xpRequiredToLevelUp();
    }

    public void addCosmetic(Cosmetic cosmetic) {
        this.cosmetics.add(cosmetic);
        cosmetics.forEach(cosmetic1 -> System.out.println(cosmetic1.toString()));
    }

    public void removeCosmetic(Cosmetic cosmetic) {
        this.cosmetics.remove(cosmetic);
    }
}
