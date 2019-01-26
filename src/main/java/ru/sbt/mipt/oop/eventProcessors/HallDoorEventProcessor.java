package ru.sbt.mipt.oop.eventProcessors;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.components.SmartHome;

import static ru.sbt.mipt.oop.sensors.SensorEventType.DOOR_CLOSED;

public class HallDoorEventProcessor implements EventProcessor {


    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {

        if (event.getType() != DOOR_CLOSED) return;
        // событие от двери
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (room.getName().equals("hall")) {
                        smartHome.switchOffLights();
                    }
                }
            }
        }
    }
}
