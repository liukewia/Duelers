package client.models.menus;

import client.Client;
import client.models.account.Collection;
import client.view.View;

public class CollectionMenu implements Menu {
    private static CollectionMenu collectionMenu;
    private Collection collection;

    private CollectionMenu() {
    }

    public static CollectionMenu getInstance() {
        if (collectionMenu == null)
            collectionMenu = new CollectionMenu();
        return collectionMenu;
    }

    public void showAllDecks(){

    }

    public void showDeck(String deckName){

    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public void newDeck(String deckName) {

    }

    public void addCardToDeck(String deckName, String cardID) {

    }

    public void removeCardFromDeck(String deckName, String cardID) {

    }

    public void save() {

    }

    public void removeDeck(String deckName) {

    }

    public void validateDeck(String deckName) {

    }

    public void selectDeck(String DeckName) {

    }

    @Override
    public void exit(Client client) {
        client.setCurrentMenu(MainMenu.getInstance());
    }

    public void showHelp() {
        String help = "\"show\" : show cards and items\n" +
                "\"create deck [deck name]\"\n" +
                "\"delete deck [deck name]\"\n" +
                "\"search [card name | item name]\"\n" +
                "\"add [card id | hero id] to deck [deck name]\"\n" +
                "\"remove [card id | hero id] from deck [deck name]\"\n" +
                "\"validate deck [deck name]\"\n" +
                "\"select deck [deck name]\"\n" +
                "\"save\"\n" +
                "\"show all decks\"\n" +
                "\"show deck [deck name]\"\n" +
                "\"exit\"";
        View.getInstance().showHelp(help);
    }

    public void showItemsAndCards(){

    }

    public void search(String cardID){

    }

}
