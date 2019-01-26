package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.action.Executer;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.sensors.CommandType;
import ru.sbt.mipt.oop.sensors.SensorCommand;
import ru.sbt.mipt.oop.sensors.SensorCommandExecutor;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Executer {
    Collection<Room> rooms;
    Alarm alarm;

    public SmartHome() {
        rooms = new ArrayList<>();
        alarm = new Alarm("1239");
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
        alarm = new Alarm("1239");
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void setRooms(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public void switchOffLights() {
        for (Room room : getRooms()) {
            for (Light light : room.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                SensorCommandExecutor.executeCommand(command);
            }
        }
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public void executeAction(Action action) {
        action.execute(this);
        for (Room room : rooms) {
            room.executeAction(action);
        }
    }

}
