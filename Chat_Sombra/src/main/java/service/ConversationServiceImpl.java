package service;

import dao.ConversationDAO;
import model.Conversation;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConversationServiceImpl implements ConversationService {

    @Autowired
    ConversationDAO conversationDAO;

    @Autowired
    UserService userService;

    @Override
    public Conversation addConversation(Conversation conversation) {
        if (conversation!=null&&conversation.getName()!=null){
            Set<User> listOfUser = new HashSet<>();
            listOfUser.add(userService.identifyUser());
            conversation.setUsers(listOfUser);
            return conversationDAO.add(conversation);
        }
        return null;
    }

    @Override
    public Conversation updateConversation(Conversation conversation, int id) {
        Conversation conversationInDB=conversationDAO.getById(id);
        if (conversation!=null&&conversationInDB!=null){
            if (conversation.getName()!=null){
                conversationInDB.setName(conversation.getName());
            }
        }
        return null;
    }

    @Override
    public List<Conversation> getAllConversationOfCurrentUser() {
        return conversationDAO.getAllConversationOfCurrentUser(userService.identifyUser().getId());
    }

    @Override
    public Conversation addUserInConversation(int conversationId, int userId) {
        User user = userService.getUserById(userId);
                Conversation conversation = conversationDAO.getById(conversationId);
                conversation.getUsers().add(user);
                return conversationDAO.update(conversation);
    }
}
