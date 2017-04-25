package dao;

import model.User;
import java.util.List;

public interface UserDAO extends GenericDao<User> {

    User getUserByName(String name);

    List<User> getAllUsersInConversation(int conversationId);

    List<User> getUserFriends(int currentUserId);

}
