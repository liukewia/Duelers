package server.chatCenter;

import server.Server;
import server.clientPortal.models.message.Message;
import server.dataCenter.DataCenter;
import server.dataCenter.models.account.Account;
import server.exceptions.ClientException;

import java.util.ArrayList;

public class ChatCenter {
    private static ChatCenter ourInstance = new ChatCenter();
    private ArrayList<String> globalMessages = new ArrayList<>();

    public static ChatCenter getInstance() {
        return ourInstance;
    }

    private ChatCenter() {
    }

    public void getMessage(Message message){
        if (message.getChatMessage().getReceiverUsername()==null) {
            for(Account account:DataCenter.getInstance().getAccounts().keySet()){//TODO:can has much more performance
                if(DataCenter.getInstance().isOnline(account.getUsername())){
                    sendMessage(DataCenter.getInstance().getClientName(account.getUsername()),
                            message.getChatMessage().getSenderUsername(),null,
                            message.getChatMessage().getText());
                }
            }
        } else {
            if(DataCenter.getInstance().isOnline(message.getChatMessage().getReceiverUsername())){
                sendMessage(DataCenter.getInstance().getClientName(message.getChatMessage().getReceiverUsername()),
                        message.getChatMessage().getSenderUsername(),message.getChatMessage().getReceiverUsername(),
                        message.getChatMessage().getText());
            }
        }
    }

    private void sendMessage(String receiverClientName,String senderUsername, String receiverUsername, String text){
        if (receiverClientName == null) {
            Server.getInstance().serverPrint("Chat Receiver Error!");
        }
        Message message = Message.makeChatMessage(Server.getInstance().serverName, receiverClientName,
                senderUsername, receiverUsername, text, 0);
        Server.getInstance().addToSendingMessages(message);
    }
}