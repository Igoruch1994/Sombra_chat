package dao;


import model.Message;
import java.util.List;

public interface MessageDAO extends GenericDao<Message> {
    List<Message> getAllMessagesByConversationId(int id);
}
