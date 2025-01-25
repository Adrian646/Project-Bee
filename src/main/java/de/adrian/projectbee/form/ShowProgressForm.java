package de.adrian.projectbee.form;

import cn.nukkit.Player;
import cn.nukkit.form.window.FormWindowSimple;
import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.data.messages.Messages;
import de.adrian.projectbee.model.PlayerModel;
import de.adrian.projectbee.utils.form.BaseSimpleForm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShowProgressForm extends BaseSimpleForm {

    private final ProjectBee PLUGIN;

    @Override
    public void openForm(Player player) {
        PlayerModel playerModel = PLUGIN.getPlayerManager().getPlayer(player.getUniqueId());

        FormWindowSimple formWindowSimple = new FormWindowSimple(
                Messages.PROGRESS_FORM_TITLE.format(),
                Messages.PROGRESS_FORM_CONTENT.format(playerModel.getLevel(), playerModel.getLevelProgress(), playerModel.getXp())
        );

        player.showFormWindow(formWindowSimple);
    }
}
