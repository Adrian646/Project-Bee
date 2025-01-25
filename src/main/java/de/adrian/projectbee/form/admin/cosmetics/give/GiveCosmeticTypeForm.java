package de.adrian.projectbee.form.admin.cosmetics.give;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;
import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.data.cosmetic.CosmeticType;
import de.adrian.projectbee.data.messages.Messages;
import de.adrian.projectbee.utils.form.BaseSimpleForm;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GiveCosmeticTypeForm extends BaseSimpleForm {

    private final ProjectBee PLUGIN;

    private final Player target;

    @Override
    public void openForm(Player player) {
        FormWindowSimple formWindowSimple = new FormWindowSimple(Messages.GIVE_COSMETIC_FORM_TITLE.format(), Messages.GIVE_COSMETIC_TYPE_FORM_CONTENT.format());

        List<String> buttonLabels = PLUGIN.getCosmeticManager()
                .getAllCosmeticTypes()
                .stream()
                .map(CosmeticType::getName)
                .toList();

        addButtons(formWindowSimple, buttonLabels);

        player.showFormWindow(formWindowSimple);

        formWindowSimple.addHandler((player1, i) -> {
            FormResponseSimple responseSimple = formWindowSimple.getResponse();
            if (!formWindowSimple.wasClosed()) {
                int clickedButtonId = responseSimple.getClickedButtonId();
                if (clickedButtonId >= 0 && clickedButtonId < buttonLabels.size()) {
                    CosmeticType selectedCosmeticType = PLUGIN.getCosmeticManager()
                            .getAllCosmeticTypes()
                            .get(clickedButtonId);

                    new GiveCosmeticListForm(PLUGIN, selectedCosmeticType, target).openForm(player);
                }
            }
        });
    }
}
