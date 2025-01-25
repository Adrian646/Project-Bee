package de.adrian.projectbee.utils.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;

import java.util.List;

public abstract class BaseSimpleForm {

    public abstract void openForm(Player player);

    protected void addButtons(FormWindowSimple form, List<String> buttonLabels) {
        buttonLabels.forEach(s -> form.addButton(new ElementButton(s)));
    }
}
