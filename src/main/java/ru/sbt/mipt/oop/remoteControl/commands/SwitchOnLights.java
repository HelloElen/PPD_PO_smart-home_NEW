package ru.sbt.mipt.oop.remoteControl.commands;

import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;

public class SwitchOnLights implements RemoteCommand {
    private SmartHome smartHome;

    public  SwitchOnLights (SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                light.setOn(true);
            }
        }
    }
}
