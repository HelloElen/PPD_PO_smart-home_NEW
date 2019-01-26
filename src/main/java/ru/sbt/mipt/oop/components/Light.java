package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.action.Executer;

public class Light implements Executer {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getId() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public void executeAction(Action action) {
        action.execute(this);
    }
}
