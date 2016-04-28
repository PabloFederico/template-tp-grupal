package ar.fiuba.tdd.tp.server.motor.factories;

import ar.fiuba.tdd.tp.server.motor.gamemethod.TemplateLoadOpenDoor2;
import ar.fiuba.tdd.tp.server.motor.games.Game;
import ar.fiuba.tdd.tp.server.motor.games.OpenDoor;

public class FactoryOpenDoor2 extends FactoryGames {

    @Override
    public Game create() {
        return new OpenDoor(new TemplateLoadOpenDoor2());
    }

    @Override
    public String getHelp() {
        return OpenDoor.getHelp();
    }
}
