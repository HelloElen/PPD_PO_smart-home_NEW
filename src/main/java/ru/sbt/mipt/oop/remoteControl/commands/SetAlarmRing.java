package ru.sbt.mipt.oop.remoteControl.commands;

import ru.sbt.mipt.oop.components.SmartHome;

public class SetAlarmRing implements RemoteCommand {
    private SmartHome smartHome;

    public SetAlarmRing (SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.getAlarm().startRing();
    }
}
