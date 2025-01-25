package de.adrian.projectbee.form.admin.cosmetics.give;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;
import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.data.cosmetic.Cosmetic;
import de.adrian.projectbee.data.cosmetic.type.CosmeticType;
import de.adrian.projectbee.data.messages.Messages;
import de.adrian.projectbee.utils.form.BaseSimpleForm;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GiveCosmeticListForm extends BaseSimpleForm {

    private final ProjectBee PLUGIN;

    private final CosmeticType cosmeticType;

    private final Player target;

    @Override
    public void openForm(Player player) {
        FormWindowSimple formWindowSimple = new FormWindowSimple(Messages.GIVE_COSMETIC_FORM_TITLE.format(), Messages.GIVE_COSMETIC_TYPE_FORM_CONTENT.format());

        List<String> buttonLabels = PLUGIN.getCosmeticManager()
                .getAllCosmetics()
                .stream()
                .filter(cosmetic -> cosmetic.getCosmeticType() == cosmeticType)
                .map(cosmetic -> Messages.GIVE_COSMETIC_FORM_BUTTON.format(cosmetic.getName(), cosmetic.getPrice()))
                .toList();

        addButtons(formWindowSimple, buttonLabels);

        player.showFormWindow(formWindowSimple);

        formWindowSimple.addHandler((player1, i) -> {
            FormResponseSimple responseSimple = formWindowSimple.getResponse();
            if (!formWindowSimple.wasClosed()) {
                int clickedButtonId = responseSimple.getClickedButtonId();
                if (clickedButtonId >= 0 && clickedButtonId < buttonLabels.size()) {
                    Cosmetic clickedCosmetic = PLUGIN.getCosmeticManager()
                            .getAllCosmetics()
                            .stream()
                            .filter(cosmetic -> cosmetic.getCosmeticType() == cosmeticType)
                            .toList()
                            .get(clickedButtonId);

                    new GiveCosmeticAcceptForm(PLUGIN, clickedCosmetic, target).openForm(player);
                }
            }
        });
    }
}
