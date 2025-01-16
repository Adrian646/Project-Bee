package de.adrian.projectbee.form.handler;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;
import de.adrian.projectbee.ProjectBee;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GiveCosmeticFormHandler {

    private final ProjectBee PLUGIN;

    public void handleFormResponse(Player player, FormWindowSimple formWindowSimple) {
        if (formWindowSimple.wasClosed()) {
            return;
        }

        FormResponseSimple response = formWindowSimple.getResponse();

        if (response == null) {
            PLUGIN.getLogger().alert("Got invalid response!");
        }



    }

}
