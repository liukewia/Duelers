package controller;

import models.comperessedData.CompressedGame;
import models.comperessedData.CompressedTroop;
import models.game.GameActions;
import models.game.availableActions.AvailableActions;

import java.util.ArrayList;


public class GameController implements GameActions {
    private static GameController ourInstance;
    private CompressedGame currentGame;
    private AvailableActions availableActions = new AvailableActions();

    private GameController() {
    }

    public static GameController getInstance() {
        if (ourInstance == null) {
            ourInstance = new GameController();
        }
        return ourInstance;
    }

    public void calculateAvailableActions() {
        availableActions.calculate(currentGame);
    }

    public CompressedGame getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(CompressedGame currentGame) {
        this.currentGame = currentGame;
        currentGame.getPlayerOne().setTroops(currentGame.getGameMap().getPlayerTroop(1));
        currentGame.getPlayerTwo().setTroops(currentGame.getGameMap().getPlayerTroop(2));
    }

    @Override
    public void attack(CompressedTroop selectedTroop, CompressedTroop troop) {

    }

    @Override
    public void comboAttack(ArrayList<CompressedTroop> comboTroops, CompressedTroop troop) {

    }

    @Override
    public void move(CompressedTroop selectedTroop, int j, int i) {

    }

    @Override
    public void endTurn() {

    }

    @Override
    public void insert(String cardID, int row, int column) {

    }

    @Override
    public void useSpecialPower(int row, int column) {

    }

}
