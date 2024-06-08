package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.manager.ICommandManager;
import edu.kis.powp.jobs2d.command.visitor.DeepCopyVisitor;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeepCopyVisitorTest implements ActionListener {
    private static final DeepCopyVisitor deepCopy = new DeepCopyVisitor();

    @Override
    public void actionPerformed(ActionEvent e) {
        ICommandManager manager = CommandsFeature.getCommandManager();
        manager.setCurrentCommand(deepCopy.getCopiedCommand());
    }

    public static DeepCopyVisitor getDeepCopy() {
        return deepCopy;
    }
}
