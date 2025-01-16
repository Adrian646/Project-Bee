package de.adrian.projectbee.manager;

import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.command.SetLevelCommand;
import de.adrian.projectbee.command.ShowProgressCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProgressManager {

    private final ProjectBee PLUGIN;

    public void registerCommands() {
        this.PLUGIN.registerCommands(
                new SetLevelCommand(this.PLUGIN),
                new ShowProgressCommand(this.PLUGIN)
        );
    }

}
