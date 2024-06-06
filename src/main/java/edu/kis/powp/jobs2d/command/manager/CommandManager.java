package edu.kis.powp.jobs2d.command.manager;

import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.builder.CompoundCommandBuilder;
import edu.kis.powp.observer.Publisher;

/**
 * Command Manager.
 */
public class CommandManager implements ICommandManager {
    private DriverCommand currentCommand = null;

    private Publisher changePublisher = new Publisher();

    /**
     * Set current command.
     *
     * @param commandList Set the command as current.
     */
    public synchronized void setCurrentCommand(DriverCommand commandList) {
        this.currentCommand = commandList;
        changePublisher.notifyObservers();
    }

    /**
     * Set current command.
     *
     * @param commandList list of commands representing a compound command.
     * @param name        name of the command.
     */
    public synchronized void setCurrentCommand(List<DriverCommand> commandList, String name) {
        CompoundCommandBuilder builder = new CompoundCommandBuilder().setName(name);
        for (DriverCommand command : commandList) {
            builder.addCommand(command);
        }
        this.currentCommand = builder.build();
        changePublisher.notifyObservers();
    }

    @Override
    public synchronized void runCommand(Job2dDriver driver) {
        this.currentCommand.execute(driver);
    }

    /**
     * Return current command.
     *
     * @return Current command.
     */
    public synchronized DriverCommand getCurrentCommand() {
        return currentCommand;
    }

    public synchronized void clearCurrentCommand() {
        currentCommand = null;
    }

    public synchronized String getCurrentCommandString() {
        if (getCurrentCommand() == null) {
            return "No command loaded";
        } else
            return getCurrentCommand().toString();
    }

    public Publisher getChangePublisher() {
        return changePublisher;
    }


}
