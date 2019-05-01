package server.models.map;

import server.models.card.Card;
import server.models.game.Player;
import server.models.game.Troop;
import server.models.message.CompressedGameMap;

import java.util.ArrayList;
import java.util.Random;

public class GameMap {
    private static final int ROW_NUMBER = 5, COLUMN_NUMBER = 9;

    private Cell[][] cells;
    private ArrayList<Troop> troops = new ArrayList<>();
    private ArrayList<Cell> flagCells = new ArrayList<>();
    private ArrayList<Cell> collectibleItemCells = new ArrayList<>();

    public GameMap(ArrayList<Card> items, int numberOfFlags, Card originalFlag) {
        cells = new Cell[ROW_NUMBER][COLUMN_NUMBER];
        for (int i = 0; i < ROW_NUMBER; i++) {
            for (int j = 0; j < COLUMN_NUMBER; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        cells[0][4].addItem(items.get(new Random().nextInt(items.size())));
        cells[2][5].addItem(items.get(new Random().nextInt(items.size())));
        cells[4][4].addItem(items.get(new Random().nextInt(items.size())));

        for (int i = 0; i < numberOfFlags; i++) {
            int row = new Random().nextInt(ROW_NUMBER);
            int column = new Random().nextInt(COLUMN_NUMBER);
            while (!cells[row][column].getItems().isEmpty()) {
                row = new Random().nextInt(ROW_NUMBER);
                column = new Random().nextInt(COLUMN_NUMBER);
            }
            cells[row][column].addItem(new Card(originalFlag, "Flag", i));
        }
    }

    public CompressedGameMap toCompressedGameMap() {
        return new CompressedGameMap(cells, troops);
    }

    public static int getRowNumber() {
        return ROW_NUMBER;
    }

    public static int getColumnNumber() {
        return COLUMN_NUMBER;
    }

    public Cell getCell(Position position) {
        return cells[position.getRow()][position.getColumn()];
    }

    public Cell getCell(int row, int column) {
        if (checkCoordination(row, column)) {
            return cells[row][column];
        }
        return null;
    }

    public boolean checkCoordination(int row, int column) {
        return row >= 0 && row < ROW_NUMBER && column >= 0 && column < COLUMN_NUMBER;
    }

    public boolean checkCoordination(Position position) {
        return position.getRow() >= 0 && position.getRow() < ROW_NUMBER && position.getColumn() >= 0 && position.getColumn() < COLUMN_NUMBER;
    }


    public Cell[][] getCells() {
        return this.cells;
    }

    public ArrayList<Cell> getFlagCells() {
        return flagCells;
    }

    public ArrayList<Cell> getCollectibleItemCells() {
        return collectibleItemCells;
    }


    public void addTroop(int playerNumber, Troop troop) {
        this.troops.add(troop);
    }

    public Troop getTroop(int row, int column) {
        for (Troop troop : troops) {
            if (troop.getCell().getColumn() == column && troop.getCell().getRow() == row) {
                return troop;
            }
        }
        return null;
    }

    public Troop getTroop(String cardId) {
        for (Troop troop : troops) {
            if (troop.getCard().getCardId().equals(cardId)) {
                return troop;
            }
        }
        return null;
    }

    public void removeTroop(Player player, Troop troop) {
        troops.remove(troop);
        throwFlags(player, troop);
        player.removeFlagCarrier(troop);
    }

    private void throwFlags(Player player, Troop troop) {
        for (Card flag : troop.getFlags()) {
            troop.getCell().addItem(flag);
            player.decreaseNumberOfCollectedFlags();
        }
    }

    public ArrayList<Troop> getTroops() {
        return troops;
    }
}