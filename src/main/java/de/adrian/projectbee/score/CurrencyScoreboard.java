package de.adrian.projectbee.score;

import cn.nukkit.Player;
import cn.nukkit.scheduler.NukkitRunnable;
import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.manager.PlayerManager;

import java.util.LinkedList;
import java.util.Queue;

public class CurrencyScoreboard {

    private static final int SIDEBAR_DELAY = 1;
    private static final int QUEUE_PROCESS_DELAY = 25;

    private final Player player;
    private final Queue<String[]> titleQueue = new LinkedList<>();
    private boolean isSending = false;

    private int oldCoinAmount = 0;
    private int oldLevel = 0;

    public CurrencyScoreboard(Player player) {
        this.player = player;
        updatePlayerData();
        sendInitialTitles();
        startQueueProcessor();
    }

    private void updatePlayerData() {
        PlayerManager playerManager = ProjectBee.getProjectBee().getPlayerManager();
        this.oldCoinAmount = playerManager.getPlayer(player.getUniqueId()).getCoins();
        this.oldLevel = playerManager.getPlayer(player.getUniqueId()).getLevel();
    }

    private void sendInitialTitles() {
        addTitlesToQueue(formatCoinsTitle(oldCoinAmount), formatLevelTitle(oldLevel));
    }

    public void updateTitle() {
        PlayerManager playerManager = ProjectBee.getProjectBee().getPlayerManager();
        int currentCoinAmount = playerManager.getPlayer(player.getUniqueId()).getCoins();
        int currentLevel = playerManager.getPlayer(player.getUniqueId()).getLevel();

        boolean coinChanged = currentCoinAmount != oldCoinAmount;
        boolean levelChanged = currentLevel != oldLevel;

        if (coinChanged) oldCoinAmount = currentCoinAmount;
        if (levelChanged) oldLevel = currentLevel;

        if (coinChanged || levelChanged) {
            addTitlesToQueue(
                    coinChanged ? formatCoinsTitle(currentCoinAmount) : null,
                    levelChanged ? formatLevelTitle(currentLevel) : null
            );
        }
    }

    private void addTitlesToQueue(String sidebar0, String sidebar1) {
        String[] titles = new String[2];
        titles[0] = (sidebar0 != null) ? sidebar0 : formatCoinsTitle(oldCoinAmount);
        titles[1] = (sidebar1 != null) ? sidebar1 : formatLevelTitle(oldLevel);
        titleQueue.offer(titles);
    }

    private void startQueueProcessor() {
        new NukkitRunnable() {
            @Override
            public void run() {
                processTitleQueue();
            }
        }.runTaskTimer(ProjectBee.getProjectBee(), 0, 20);
    }

    private void processTitleQueue() {
        if (isSending || titleQueue.isEmpty()) {
            return;
        }
        sendNextTitles();
    }

    private void sendNextTitles() {
        if (!titleQueue.isEmpty() && player != null && player.isOnline()) {
            isSending = true;
            String[] titles = titleQueue.poll();
            if (titles != null && titles.length == 2) {
                if (titles[0] != null) {
                    player.sendTitle(sidebar_0(titles[0]));
                }

                if (titles[1] != null) {
                    new NukkitRunnable() {
                        @Override
                        public void run() {
                            player.sendTitle(sidebar_1(titles[1]));
                        }
                    }.runTaskLater(ProjectBee.getProjectBee(), SIDEBAR_DELAY);
                }
            }

            new NukkitRunnable() {
                @Override
                public void run() {
                    isSending = false;
                }
            }.runTaskLater(ProjectBee.getProjectBee(), QUEUE_PROCESS_DELAY);
        }
    }

    private String formatCoinsTitle(int coins) {
        return "\uE101 §6Coins: §e" + coins;
    }

    private String formatLevelTitle(int level) {
        return "\uE100 §aLevel: §b" + level;
    }

    private String sidebar_0(String text) {
        return "sidebar_0" + text;
    }

    private String sidebar_1(String text) {
        return "sidebar_1" + text;
    }
}