package ar.fiuba.tdd.tp.engine.motor.entities;

import ar.fiuba.tdd.tp.engine.motor.EntityContainer;
import ar.fiuba.tdd.tp.engine.motor.uses.Unlockable;

public class Key extends PickableGameEntity {

    private static final String NAME = "key";
    private Unlockable target;

    public Key(Unlockable targetToUnlock, EntityContainer originContainer, EntityContainer destinationContainer) {
        super(NAME, originContainer, destinationContainer);
        this.target = targetToUnlock;
    }

    public Key(String custonName, Unlockable targetToUnlock, EntityContainer originContainer, EntityContainer destinationContainer) {
        super(custonName, originContainer, destinationContainer);
        this.target = targetToUnlock;
    }

    @Override
    public String pick() {
        target.unlock();
        return super.pick();
    }
}