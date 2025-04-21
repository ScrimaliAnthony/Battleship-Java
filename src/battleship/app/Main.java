package battleship.app;

import battleship.exceptions.*;
import battleship.model.Player;
import battleship.ui.Display;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        gameEngine.startGame();
    }
}
