package de.adrian.projectbee.form.cosmetic.shop;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;
import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.data.cosmetic.Cosmetic;
import de.adrian.projectbee.data.cosmetic.type.CosmeticType;
import de.adrian.projectbee.data.messages.Messages;
import de.adrian.projectbee.manager.PlayerManager;
import de.adrian.projectbee.model.PlayerModel;
import de.adrian.projectbee.utils.form.BaseSimpleForm;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CosmeticShopListForm extends BaseSimpleForm {

    private final ProjectBee PLUGIN;

    private final CosmeticType cosmeticType;

    @Override
    public void openForm(Player player) {
        FormWindowSimple formWindowSimple = new FormWindowSimple(Messages.SHOP_COSMETIC_FORM_TITLE.format(), cosmeticType.getDescription());
        PlayerManager playerManager = PLUGIN.getPlayerManager();
        PlayerModel playerModel = playerManager.getPlayer(player.getUniqueId());

        List<String> buttonLabels = PLUGIN.getCosmeticManager()
                .getAllCosmetics()
                .stream()
                .filter(cosmetic -> cosmetic.getCosmeticType() == cosmeticType && !(playerModel.hasCosmetic(cosmetic)))
                .map(cosmetic -> Messages.SHOP_COSMETIC_FORM_BUTTON.format(cosmetic.getName(), cosmetic.getPrice()))
                .toList();

        addButtons(formWindowSimple, buttonLabels);

        player.showFormWindow(formWindowSimple);

        formWindowSimple.addHandler((p, i) -> {
            FormResponseSimple responseSimple = formWindowSimple.getResponse();
            if (!formWindowSimple.wasClosed()) {
                int clickedButtonId = responseSimple.getClickedButtonId();
                if (clickedButtonId >= 0 && clickedButtonId < buttonLabels.size()) {
                    Cosmetic clickedCosmetic = PLUGIN.getCosmeticManager()
                            .getAllCosmetics()
                            .stream()
                            .filter(cosmetic -> cosmetic.getCosmeticType() == cosmeticType && !(playerModel.hasCosmetic(cosmetic)))
                            .toList()
                            .get(clickedButtonId);

                    if (!playerModel.hasCosmetic(clickedCosmetic)) {
                        if (playerModel.getCoins() >= clickedCosmetic.getPrice()) {
                            playerManager.addPlayerCosmetic(player.getUniqueId(), clickedCosmetic);
                            playerModel.setCoins(playerModel.getCoins() - clickedCosmetic.getPrice());
                            playerModel.getCurrencyScoreboard().updateTitle();
                            player.sendMessage(PLUGIN.getPrefix() + Messages.SUCCESSFULLY_PURCHASED.format(clickedCosmetic.getName()));
                        } else {
                            player.sendMessage(PLUGIN.getPrefix() + Messages.NOT_ENOUGH_MONEY.format(clickedCosmetic.getName()));
                        }
                    } else {
                        player.sendMessage(Messages.YOU_ALREADY_OWN_COSMETIC.format(clickedCosmetic.getName()));
                    }
                }
            }
        });
    }
}
