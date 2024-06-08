package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.ICommandManager;
import edu.kis.powp.jobs2d.command.visitor.CommandTransformationVisitor;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.transformations.RotateTransformation;
import edu.kis.powp.jobs2d.transformations.Transformation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommandRotateTest implements ActionListener {

    private final double degrees;

    public CommandRotateTest(double degrees) {
        this.degrees = degrees;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ICommandManager commandManager = CommandsFeature.getCommandManager();
        DriverCommand currentCommand = commandManager.getCurrentCommand();

        Transformation rotateTransformation = new RotateTransformation(this.degrees);
        CommandTransformationVisitor commandTransformationVisitor = new CommandTransformationVisitor(currentCommand.toString(), rotateTransformation);

        currentCommand.accept(commandTransformationVisitor);
        commandManager.setCurrentCommand(commandTransformationVisitor.getTransformedCommand());
    }
}
