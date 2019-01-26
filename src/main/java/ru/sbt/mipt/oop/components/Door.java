package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.action.Executer;

public class Door implements Executer {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void executeAction(Action action) {
        action.execute(this);
    }
}
