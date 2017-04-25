package service;

import model.Conversation;
import java.util.List;

public interface ConversationService {

    Conversation addConversation(Conversation conversation);

    Conversation updateConversation(Conversation conversation,int id);

    List<Conversation> getAllConversationOfCurrentUser();

    Conversation addUserInConversation(int conversationId, int userId);

}
