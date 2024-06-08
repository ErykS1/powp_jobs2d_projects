package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.ICommandManager;
import edu.kis.powp.jobs2d.command.visitor.CommandTransformationVisitor;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.transformations.Transformation;
import edu.kis.powp.jobs2d.transformations.VerticalFlipTransformation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommandVerticalFlipTest implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ICommandManager commandManager = CommandsFeature.getCommandManager();
        DriverCommand currentCommand = commandManager.getCurrentCommand();

        Transformation verticalFlipTransformation = new VerticalFlipTransformation();
        CommandTransformationVisitor commandTransformationVisitor = new CommandTransformationVisitor(currentCommand.toString(), verticalFlipTransformation);

        currentCommand.accept(commandTransformationVisitor);
        commandManager.setCurrentCommand(commandTransformationVisitor.getTransformedCommand());
    }
}
