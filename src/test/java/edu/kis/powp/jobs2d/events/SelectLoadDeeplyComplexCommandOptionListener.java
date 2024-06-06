package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.ImmutableCompoundCommand;
import edu.kis.powp.jobs2d.command.builder.CompoundCommandBuilder;
import edu.kis.powp.jobs2d.command.factory.CompoundCommandRectangleFactory;
import edu.kis.powp.jobs2d.command.manager.CommandManager;
import edu.kis.powp.jobs2d.command.manager.ICommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectLoadDeeplyComplexCommandOptionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ImmutableCompoundCommand compoundCommand1 = CompoundCommandRectangleFactory.getRectangleAction("Rectangle command");
        CompoundCommandBuilder builder = new CompoundCommandBuilder();
        builder.setName("Triangle command")
                .addSetPosition(0, 100)
                .addOperateTo(200, 50)
                .addOperateTo(0, 0)
                .addOperateTo(0, 100);
        ImmutableCompoundCommand compoundCommand2 = builder.build();
        CompoundCommand deeplyCompoundCommand = new CompoundCommand("Deeply complex command");
        deeplyCompoundCommand.addCommand(compoundCommand1);
        deeplyCompoundCommand.addCommand(compoundCommand2);

        ICommandManager manager = CommandsFeature.getCommandManager();
        manager.setCurrentCommand(deeplyCompoundCommand.getCommands(), "Deeply complex command");
    }
}
