package de.adrian.projectbee.form.cosmetic;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;
import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.data.cosmetic.CosmeticType;
import de.adrian.projectbee.data.messages.Messages;
import de.adrian.projectbee.utils.form.BaseSimpleForm;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CosmeticShopTypeForm extends BaseSimpleForm {

    private final ProjectBee PLUGIN;

    @Override
    public void openForm(Player player) {
        FormWindowSimple formWindowSimple = new FormWindowSimple(Messages.SHOP_COSMETIC_FORM_TITLE.format(), Messages.SHOP_COSMETIC_FORM_CONTENT.format());

        List<String> buttonLabels = PLUGIN.getCosmeticManager()
                .getAllCosmeticTypes()
                .stream()
                .map(type -> Messages.SHOP_COSMETIC_FORM_BUTTON.format(type.name(), type.getDescription()))
                .toList();

        addButtons(formWindowSimple, buttonLabels);

        player.showFormWindow(formWindowSimple);

        formWindowSimple.addHandler((p, i) -> {
            FormResponseSimple responseSimple = formWindowSimple.getResponse();
            if (!formWindowSimple.wasClosed()) {
                int clickedButtonId = responseSimple.getClickedButtonId();
                if (clickedButtonId >= 0 && clickedButtonId < buttonLabels.size()) {
                    CosmeticType selectedCosmeticType = PLUGIN.getCosmeticManager()
                            .getAllCosmeticTypes()
                            .get(clickedButtonId);

                    new CosmeticShopListForm(PLUGIN, selectedCosmeticType).openForm(player);
                }
            }
        });

    }
}
