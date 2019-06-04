package server.clientPortal.models.message;

import server.clientPortal.models.JsonConverter;
import server.detaCenter.models.account.Account;
import server.detaCenter.models.account.Collection;
import server.detaCenter.models.card.Card;
import server.gameCenter.models.game.Game;
import server.gameCenter.models.game.Story;
import server.gameCenter.models.game.Troop;

public class Message {//TODO:ServerToClientMessage && ClientToServerMessage
    private MessageType messageType;
    //serverName || clientName
    private String sender;
    private String receiver;
    private int messageId;

    //SENDER:SERVER
    private GameCopyMessage gameCopyMessage;
    private OriginalCardsCopyMessage originalCardsCopyMessage;
    private AccountCopyMessage accountCopyMessage;
    private LeaderBoardCopyMessage leaderBoardCopyMessage;
    private StoriesCopyMessage storiesCopyMessage;
    private CardPositionMessage cardPositionMessage;
    private TroopUpdateMessage troopUpdateMessage;
    private GameUpdateMessage gameUpdateMessage;
    private ExceptionMessage exceptionMessage;
    private OpponentInfoMessage opponentInfoMessage;
    private GameFinishMessage gameFinishMessage;

    //SENDER:CLIENT
    private GetDataMessage getDataMessage;
    private OtherFields otherFields;
    private AccountFields accountFields;
    private NewGameFields newGameFields;


    private Message(String sender, String receiver, int messageId) {
        this.sender = sender;
        this.receiver = receiver;
        this.messageId = messageId;
    }

    public static Message convertJsonToMessage(String messageJson) {
        return JsonConverter.fromJson(messageJson, Message.class);
    }

    public static Message makeDoneMessage(String sender, String receiver, int messageId) {
        Message message = new Message(sender, receiver, messageId);
        message.messageType = MessageType.DONE;
        return message;
    }

    public static Message makeGameCopyMessage(String sender, String receiver, Game game, int messageId) {
        Message message = new Message(sender, receiver, messageId);
        message.gameCopyMessage = new GameCopyMessage(game);
        message.messageType = MessageType.GAME_COPY;
        return message;
    }

    public static Message makeOriginalCardsCopyMessage(String sender, String receiver, Collection originalCards, int messageId) {
        Message message = new Message(sender, receiver, messageId);
        message.originalCardsCopyMessage = new OriginalCardsCopyMessage(originalCards);
        message.messageType = MessageType.ORIGINAL_CARDS_COPY;
        return message;
    }

    public static Message makeAccountCopyMessage(String sender, String receiver, Account account, int messageId) {
        Message message = new Message(sender, receiver, messageId);
        message.accountCopyMessage = new AccountCopyMessage(account);
        message.messageType = MessageType.ACCOUNT_COPY;
        return message;
    }

    public static Message makeLeaderBoardCopyMessage(String sender, String receiver, Account[] leaderBoard, int messageId) {
        Message message = new Message(sender, receiver, messageId);
        message.leaderBoardCopyMessage = new LeaderBoardCopyMessage(leaderBoard);
        message.messageType = MessageType.LEADERBOARD_COPY;
        return message;
    }

    public static Message makeStoriesCopyMessage(String sender, String receiver, Story[] stories, int messageId) {
        Message message = new Message(sender, receiver, messageId);
        message.storiesCopyMessage = new StoriesCopyMessage(stories);
        message.messageType = MessageType.STORIES_COPY;
        return message;
    }

    public static Message makeChangeCardPositionMessage(String sender, String receiver, Card card, CardPosition cardPosition, int messageId) {
        Message message = new Message(sender, receiver, messageId);
        message.cardPositionMessage = new CardPositionMessage(card, cardPosition);
        message.messageType = MessageType.CARD_POSITION;
        return message;
    }

    public static Message makeTroopUpdateMessage(String sender, String receiver, Troop troop, int messageId) {
        Message message = new Message(sender, receiver, messageId);
        message.troopUpdateMessage = new TroopUpdateMessage(troop);
        message.messageType = MessageType.TROOP_UPDATE;
        return message;
    }

    public static Message makeGameUpdateMessage(String sender, String receiver, int turnNumber, int player1CurrentMP,
                                                int player1NumberOfCollectedFlags, int player2CurrentMP,
                                                int player2NumberOfCollectedFlags, int messageId) {
        Message message = new Message(sender, receiver, messageId);
        message.gameUpdateMessage = new GameUpdateMessage(turnNumber, player1CurrentMP, player1NumberOfCollectedFlags,
                player2CurrentMP, player2NumberOfCollectedFlags);
        message.messageType = MessageType.GAME_UPDATE;
        return message;
    }

    public static Message makeExceptionMessage(String sender, String receiver, String exceptionString, int messageId) {
        Message message = new Message(sender, receiver, messageId);
        message.exceptionMessage = new ExceptionMessage(exceptionString);
        message.messageType = MessageType.SEND_EXCEPTION;
        return message;
    }

    public static Message makeAccountInfoMessage(String sender, String receiver, Account opponent, int messageId) {
        Message message = new Message(sender, receiver, messageId);
        message.opponentInfoMessage = new OpponentInfoMessage(opponent);
        message.messageType = MessageType.OPPONENT_INFO;
        return message;
    }

    public static Message makeGameFinishMessage(String sender, String receiver, boolean youWon, int messageId) {
        Message message = new Message(sender, receiver, messageId);
        message.gameFinishMessage = new GameFinishMessage(youWon);
        message.messageType = MessageType.Game_FINISH;
        return message;
    }

    public String toJson() {
        return JsonConverter.toJson(this);
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public int getMessageId() {
        return messageId;
    }

    public GetDataMessage getGetDataMessage() {
        return getDataMessage;
    }

    public OtherFields getOtherFields() {
        return otherFields;
    }

    public AccountFields getAccountFields() {
        return accountFields;
    }

    public NewGameFields getNewGameFields() {
        return newGameFields;
    }
}