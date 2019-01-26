package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.alarm.AlarmStateEnum;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.eventProcessors.Decorator;
import ru.sbt.mipt.oop.eventProcessors.DoorEventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class DecoratorTest {
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
    public void testStartRing(){
        Decorator decorator = new Decorator(new DoorEventProcessor());
        smartHome.getAlarm().switchOn();
        decorator.processEvent(smartHome, new SensorEvent(SensorEventType.DOOR_CLOSED, "3"));
        AlarmStateEnum actual = smartHome.getAlarm().getState();
        AlarmStateEnum expected = AlarmStateEnum.RING;
        assertEquals(expected, actual);
    }
}
