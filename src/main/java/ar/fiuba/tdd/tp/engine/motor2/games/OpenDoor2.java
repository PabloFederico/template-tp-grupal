package ar.fiuba.tdd.tp.engine.motor2.games;

import ar.fiuba.tdd.tp.engine.motor2.*;

import java.util.HashMap;

public class OpenDoor2 implements GameBuilder {
    @SuppressWarnings("CPD-START")

    @Override
    public Game build() {
        State boxStates = new State();
        boxStates.setState("open", false);
        boxStates.setState("visible", true);
        boxStates.setModifier("open", (HashMap<String, Container> components, HashMap<String, Boolean> states) -> {
                states.put("open", true);
                components.get("Key").changeStatus("visible");
                return "The box is open";
            });

        Container box = new Container("Box");
        box.setState(boxStates);

        State keyStates = new State();
        keyStates.setState("picked", false);
        keyStates.setState("visible", false);
        keyStates.setLamdaModifierByCommandAndState("pick", "picked", "key picked");
        keyStates.setLamdaModifierByCommandAndState("visible", "visible", "");

        Container key = new Container("Key");
        key.setState(keyStates);
        key.setDependencies(new ContainerDependant(key, "visible", "which key?"));
        box.setComponent(key);

        State doorState = new State();
        doorState.setState("open", false);
        doorState.setState("visible", true);
        doorState.setLamdaModifierByCommandAndState("open", "open", "The door is open");


        Container door = new Container("Door");
        door.setState(doorState);
        door.setDependencies(new ContainerDependant(key, "picked", "Ey! Where do you go?! Room is locked"));

        CommonCommandFactory commonCommandFactory = new CommonCommandFactory();
        Command lookAt = commonCommandFactory.getLook("look at");
        lookAt.setComponent(box);
        lookAt.setComponent(key);
        lookAt.setComponent(door);

        Command openBox = new Command("open box");
        openBox.setComponent(box);
        openBox.setExecutableCommand((HashMap<String, Container> components) -> components.get("Box").changeStatus("open"));

        Command pickKey = new Command("pick key");
        pickKey.setComponent(key);
        pickKey.setExecutable("Key", "pick");

        Command openDoor = new Command("open door");
        openDoor.setComponent(door);
        openDoor.setExecutable("Door", "open");

        Command help = commonCommandFactory.help("help", "A door locked.. where can you find a key?");

        Game gameOpenDoor2 = new Game();
        gameOpenDoor2.setExecutableCommands(help);
        gameOpenDoor2.setExecutableCommands(lookAt);
        gameOpenDoor2.setExecutableCommands(openBox);
        gameOpenDoor2.setExecutableCommands(pickKey);
        gameOpenDoor2.setExecutableCommands(openDoor);
        gameOpenDoor2.setCommandWin(door, "open");

        return gameOpenDoor2;
    }
}
