package de.adrian.projectbee.form.admin.cosmetics.give;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;
import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.data.cosmetic.Cosmetic;
import de.adrian.projectbee.data.messages.Messages;
import de.adrian.projectbee.manager.PlayerManager;
import de.adrian.projectbee.utils.form.BaseSimpleForm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GiveCosmeticAcceptForm extends BaseSimpleForm {

    private final ProjectBee PLUGIN;

    private final Cosmetic cosmetic;

    private final Player target;

    @Override
    public void openForm(Player player) {
        FormWindowSimple formWindowSimple = new FormWindowSimple(Messages.GIVE_COSMETIC_FORM_TITLE.format(), Messages.GIVE_COSMETIC_ACCEPT_FORM_CONTENT.format(cosmetic.getName(), target.getName()));

        formWindowSimple.addButton(new ElementButton(Messages.YES.format()));
        formWindowSimple.addButton(new ElementButton(Messages.NO.format()));

        player.showFormWindow(formWindowSimple);

        formWindowSimple.addHandler((player1, i) -> {
            FormResponseSimple formResponseSimple = formWindowSimple.getResponse();
            if (!formWindowSimple.wasClosed()) {
                int clickedButtonId = formResponseSimple.getClickedButtonId();
                PlayerManager playerManager = PLUGIN.getPlayerManager();
                if (clickedButtonId == 0) {
                    playerManager.addPlayerCosmetic(target.getUniqueId(), cosmetic);
                    player.sendMessage(PLUGIN.getPrefix() + Messages.SUCCESSFULLY_GIVEN_COSMETIC.format(cosmetic.getName(), target.getName()));
                } else {
                    player.sendMessage(PLUGIN.getPrefix() +  Messages.COSMETIC_GIVING_CANCELLED.format());
                }
            }
        });
    }
}
