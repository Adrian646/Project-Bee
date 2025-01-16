package de.adrian.projectbee.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.window.FormWindowCustom;
import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.data.cosmetic.Cosmetic;
import de.adrian.projectbee.data.messages.Messages;
import de.adrian.projectbee.utils.BaseSimpleForm;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class GiveCosmeticForm extends BaseSimpleForm {

    private final ProjectBee PLUGIN;

    @Override
    public void openForm(Player player) {
        FormWindowCustom formWindowCustom = new FormWindowCustom(Messages.GIVE_COSMETIC_FORM_TITLE.format());

        List<String> options = new ArrayList<>();

        for (Cosmetic cosmetic : PLUGIN.getCosmeticManager().getAllCosmetics()) {
            options.add(cosmetic.getName());
        }

        formWindowCustom.addElement(new ElementDropdown(Messages.GIVE_COSMETIC_FORM_COSMETIC_DROPDOWN_TITLE.format(), options));

        player.showFormWindow(formWindowCustom);
    }
}
