package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.eventProcessors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class HallDoorEventProcessorTest {

    @Test
    public void testLightsOff() {
        Collection<Door> doorList = new ArrayList<>(Arrays.asList(new Door(false, "1")));
        Collection<Light> lightsList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Light light = new Light(String.valueOf(i), true);
            lightsList.add(light);
        }

        SmartHome smartHome = new SmartHome(new ArrayList<>(Arrays.asList(new Room(lightsList, doorList, "hall"))));
        SensorEvent sensorEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");

        HallDoorEventProcessor hallDoorEventProcessor = new HallDoorEventProcessor();
        hallDoorEventProcessor.processEvent(smartHome, sensorEvent);

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                boolean actual = light.isOn();
                boolean expected = false;
                assertEquals(expected, actual);
            }
        }

    }

}