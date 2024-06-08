package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.ICommandManager;
import edu.kis.powp.jobs2d.command.visitor.CommandTransformationVisitor;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.transformations.ScaleTransformation;
import edu.kis.powp.jobs2d.transformations.Transformation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommandScaleTest implements ActionListener {

    private final double scale;

    public CommandScaleTest(double scale) {
        this.scale = scale;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ICommandManager commandManager = CommandsFeature.getCommandManager();
        DriverCommand currentCommand = commandManager.getCurrentCommand();

        Transformation scaleTransformation = new ScaleTransformation(this.scale);
        CommandTransformationVisitor commandScaleVisitor = new CommandTransformationVisitor(currentCommand.toString(), scaleTransformation);

        currentCommand.accept(commandScaleVisitor);
        commandManager.setCurrentCommand(commandScaleVisitor.getTransformedCommand());
    }
}
