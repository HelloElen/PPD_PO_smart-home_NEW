package ru.sbt.mipt.oop.remoteControl.commands;

import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;

public class CloseHallDoor implements RemoteCommand {

    private SmartHome smartHome;

    public CloseHallDoor (SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (room.getName().equals("hall")) {
                    door.setOpen(false);
                }
            }
        }
    }
}
