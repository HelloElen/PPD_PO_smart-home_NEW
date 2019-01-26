package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.loaders.FileSmartHomeLoader;
import ru.sbt.mipt.oop.loaders.SmartHomeLoader;
import ru.sbt.mipt.oop.observers.EventsManager;
import ru.sbt.mipt.oop.remoteControl.RemoteControl;
import ru.sbt.mipt.oop.remoteControl.RemoteControlRealization;
import ru.sbt.mipt.oop.remoteControl.RemoteControlRegistry;

import java.io.IOException;


@Configuration
public class SmartHomeConfig {

    @Bean
    RemoteControlRegistry remoteControlRegistry() {return new RemoteControlRegistry();}

    @Bean
    RemoteControl controlPanel() throws IOException {
        return new RemoteControlRealization();
    }

    @Bean
    SmartHomeLoader smartHomeLoader() {
        return new FileSmartHomeLoader();
    }

    @Bean
    EventsManager eventsManager() {
        return new EventManagerAdapter(new SensorEventsManager());
    }

}