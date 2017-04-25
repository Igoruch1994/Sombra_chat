package dao;


import model.Conversation;
import java.util.List;

public interface ConversationDAO extends GenericDao<Conversation> {

    List<Conversation> getAllConversationOfCurrentUser(int userId);

}
