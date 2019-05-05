package client.models.comperessedData;

import client.models.map.Position;

import java.util.ArrayList;
import java.util.Iterator;

public class CompressedGameMap {
    private static final int ROW_NUMBER = 5, COLUMN_NUMBER = 9;

    private CompressedCell[][] cells;
    private ArrayList<CompressedTroop> troops = new ArrayList<>();

    public static int getRowNumber() {
        return ROW_NUMBER;
    }

    public static int getColumnNumber() {
        return COLUMN_NUMBER;
    }

    public CompressedCell[][] getCells() {
        return cells;
    }

    public ArrayList<CompressedTroop> getTroops() {
        return troops;
    }

    public CompressedTroop searchTroop(String cardID) {
        for (CompressedTroop troop : troops) {
            if (troop.getCard().getCardId().equalsIgnoreCase(cardID)) {
                return troop;
            }
        }
        return null;
    }

    public ArrayList<CompressedTroop> getPlayerTroop(int playerNumber) {
        ArrayList<CompressedTroop> compressedTroops = new ArrayList<>();
        for (CompressedTroop troop : troops) {
            if (troop.getPlayerNumber() == playerNumber)
                compressedTroops.add(troop);
        }
        return compressedTroops;
    }

    public CompressedCell getCell(int row, int column) {
        if (isInMap(row, column)) {
            return cells[row][column];
        }

        return null;
    }

    public boolean isInMap(int row, int column) {
        return row >= 0 && row < ROW_NUMBER && column >= 0 && column < COLUMN_NUMBER;

    }

    public boolean checkCoordination(int row, int column) {
        return row >= 0 && row < ROW_NUMBER && column >= 0 && column < COLUMN_NUMBER;
    }

    public CompressedTroop getTroop(Position cell) {
        for (CompressedTroop troop : troops) {
            if (troop.getPosition().equals(cell)) {
                return troop;
            }
        }
        return null;
    }

    public void removeItem(String cardId){
        for(CompressedCell[] cells1:cells){
            for(CompressedCell cell:cells1){
                if(cell.getItem().getCardId().equals(cardId))
                    cell.removeItem();
            }
        }
    }

    public void addFlagNum(Position position, int addition){
        cells[position.getRow()][position.getColumn()].addNumberOfFlags(addition);
    }

    public int getFlagNum(Position position){
        return cells[position.getRow()][position.getColumn()].getNumberOfFlags();//TODO:Ahmad Check
    }

    public void updateTroop(CompressedTroop troop){//flag
        removeTroop(troop.getCard().getCardId());
        troops.add(troop);
        cells[troop.getPosition().getRow()][troop.getPosition().getColumn()].removeFlags();
    }

    public void killTroop(String cardId){//flag
        for(CompressedTroop troop:troops){
            if(troop.getCard().getCardId().equals(cardId)){
                addFlagNum(troop.getPosition(),troop.getNumberOfCollectedFlags());
            }
        }
        removeTroop(cardId);
    }

    private void removeTroop(String cardId){
        Iterator<CompressedTroop> iterator=troops.iterator();
        while (iterator.hasNext()){
            if(iterator.next().getCard().getCardId().equals(cardId))
                iterator.remove();
        }
    }

    public CompressedTroop getTroop(String cardId){
        for (CompressedTroop troop : troops) {
            if (troop.getCard().getCardId().equals(cardId)) {
                return troop;
            }
        }
        return null;
    }
}