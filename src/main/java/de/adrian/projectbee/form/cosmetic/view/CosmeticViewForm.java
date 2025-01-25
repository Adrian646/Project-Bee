package de.adrian.projectbee.form.cosmetic.view;

import cn.nukkit.Player;
import cn.nukkit.form.window.FormWindowSimple;
import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.data.messages.Messages;
import de.adrian.projectbee.model.PlayerModel;
import de.adrian.projectbee.utils.form.BaseSimpleForm;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CosmeticViewForm extends BaseSimpleForm {

    private final ProjectBee PLUGIN;

    @Override
    public void openForm(Player player) {
        FormWindowSimple formWindowSimple = new FormWindowSimple(Messages.VIEW_COSMETIC_FORM_TITLE.format(), Messages.VIEW_COSMETIC_FORM_CONTENT.format());
        PlayerModel playerModel = PLUGIN.getPlayerManager().getPlayer(player.getUniqueId());

        List<String> buttonLabels = PLUGIN.getCosmeticManager()
                .getCosmeticsSortedByOwnership(player)
                .stream()
                .map(cosmetic -> {
                    if (playerModel.hasCosmetic(cosmetic)) {
                        return Messages.VIEW_COSMETIC_OWNED_FORM_BUTTON.format(cosmetic.getName(), cosmetic.getCosmeticType().getName());
                    } else {
                        return Messages.VIEW_COSMETIC_UNOWNED_FORM_BUTTON.format(cosmetic.getName(), cosmetic.getCosmeticType().getName());
                    }
                })
                .toList();

        addButtons(formWindowSimple, buttonLabels);

        player.showFormWindow(formWindowSimple);
    }
}
