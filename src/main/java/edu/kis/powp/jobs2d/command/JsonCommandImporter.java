package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.command.builder.CompoundCommandBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonCommandImporter implements CommandImporter {

    @Override
    public DriverCommand importCommands(String commands) throws JSONException, IllegalArgumentException {

        JSONObject jsonObject = new JSONObject(commands);

        String name = jsonObject.getString("name");
        JSONArray commandsArray = jsonObject.getJSONArray("commands");

        CompoundCommandBuilder builder = new CompoundCommandBuilder().setName(name);

        for (int i = 0; i < commandsArray.length(); i++) {
            JSONObject commandObject = commandsArray.getJSONObject(i);
            DriverCommand command = createCommandFromJson(commandObject);
            builder.addCommand(command);
        }

        return builder.build();
    }

    private DriverCommand createCommandFromJson(JSONObject jsonObject) throws JSONException, IllegalArgumentException {
        String commandType = jsonObject.getString("command");
        int x = jsonObject.getInt("x");
        int y = jsonObject.getInt("y");

        switch (commandType) {
            case "setPosition":
                return new SetPositionCommand(x, y);
            case "operateTo":
                return new OperateToCommand(x, y);
            default:
                throw new IllegalArgumentException("Invalid command type: " + commandType);
        }
    }
}