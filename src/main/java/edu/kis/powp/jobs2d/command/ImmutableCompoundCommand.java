package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.visitor.CommandVisitor;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class ImmutableCompoundCommand implements ICompoundCommand {
    private final List<DriverCommand> commands;
    private final String name;

    public ImmutableCompoundCommand(List<DriverCommand> commands, String name) {
        this.commands = Collections.unmodifiableList(commands);
        this.name = name;
    }

    @Override
    public void execute(Job2dDriver driver) {
        for (DriverCommand command : commands) {
            command.execute(driver);
        }
    }

    @Override
    public void accept(CommandVisitor commandVisitor) {
        commandVisitor.visit(this);
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return commands.iterator();
    }

    @Override
    public String toString() {
        return name;
    }

    public List<DriverCommand> getCommands() {
        return commands;
    }
}
