package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.backends.lwjgl.LwjglBackend;
import sk.tuke.kpi.oop.game.scenarios.EscapeRoom;
import sk.tuke.kpi.oop.game.scenarios.MissionImpossible;

public class Main {

    public static void main(String[] args) {


        WindowSetup windowSetup = new WindowSetup("Project Ellen", 1336, 960);
        Game game = new GameApplication(windowSetup, new LwjglBackend());
        game.getInput().onKeyPressed(Input.Key.ESCAPE, () -> game.stop());
        Scene scene = new World("escape-room", "maps/escape-room.tmx", new EscapeRoom.Factory());
        //MissionImpossible missionImpossible = new MissionImpossible();
        EscapeRoom escapeRoom = new EscapeRoom();
        game.addScene(scene);
        scene.addListener(escapeRoom);
        game.start();

    }
}
