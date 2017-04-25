package service;


import model.Message;
import java.util.List;

public interface MessageService {

    Message addMessage(Message message,int conversation_id);

    List<Message> getAllMessagesByConversationId(int id);

    void sendEmail(Message message, int conversation_id);

}
