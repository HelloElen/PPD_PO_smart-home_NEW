package ru.sbt.mipt.oop.remoteControl;

import ru.sbt.mipt.oop.remoteControl.commands.RemoteCommand;

import java.rmi.Remote;
import java.util.HashMap;

public class RemoteControlRealization implements RemoteControl{
    final private HashMap<String, RemoteCommand> buttons;

    public RemoteControlRealization() {
        buttons = new HashMap<>();
    }

    public void setCommandOnButton(String buttonCode, RemoteCommand command) {
        buttons.put(buttonCode,command);
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        RemoteCommand command = buttons.get(buttonCode);
        if (command != null) command.execute();
    }
}
