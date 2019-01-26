package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.alarm.AlarmStateEnum;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.remoteControl.RemoteControlRealization;
import ru.sbt.mipt.oop.remoteControl.commands.ActivateAlarm;
import ru.sbt.mipt.oop.remoteControl.commands.CloseHallDoor;
import ru.sbt.mipt.oop.remoteControl.commands.SwitchOffLights;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class RemoteControlRealizationTest {
    Room kitchen = new Room(Arrays.asList(new Light("1", false), new Light("2", true)),
            Arrays.asList(new Door(false, "1")),
            "kitchen");
    Room bathroom = new Room(Arrays.asList(new Light("3", true)),
            Arrays.asList(new Door(false, "2")),
            "bathroom");
    Room bedroom = new Room(Arrays.asList(new Light("4", false), new Light("5", false), new Light("6", false)),
            Arrays.asList(new Door(true, "3")),
            "bedroom");
    Room hall = new Room(Arrays.asList(new Light("7", false), new Light("8", false), new Light("9", false)),
            Arrays.asList(new Door(false, "4")),
            "hall");
    SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));

    @Test
    public void testSetCommandCloseHallDoorOnButton3 (){
        RemoteControlRealization remoteControlRealization = new RemoteControlRealization();
        remoteControlRealization.setCommandOnButton("3", new CloseHallDoor(smartHome));

        remoteControlRealization.onButtonPressed("3");
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (room.getName().equals("hall")) {
                    boolean actual = door.isOpen();
                    boolean expected = false;
                    assertEquals(expected, actual);
                }
            }
        }
    }

    @Test
    public void testSetCommandActivateAlarmOnButton1 (){
        RemoteControlRealization remoteControlRealization = new RemoteControlRealization();
        remoteControlRealization.setCommandOnButton("1", new ActivateAlarm(smartHome));

        remoteControlRealization.onButtonPressed("1");
        AlarmStateEnum actual = smartHome.getAlarm().getState();
        AlarmStateEnum expected = AlarmStateEnum.ON;
        assertEquals(expected, actual);
    }

    @Test
    public void testSetCommandSwitchOffLightsOnButtonC (){
        RemoteControlRealization remoteControlRealization = new RemoteControlRealization();
        remoteControlRealization.setCommandOnButton("C", new SwitchOffLights(smartHome));

        remoteControlRealization.onButtonPressed("C");
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                boolean actual = light.isOn();
                boolean expected = false;
                assertEquals(expected, actual);
            }
        }
    }
}
