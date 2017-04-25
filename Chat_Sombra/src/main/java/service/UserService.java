package service;


import model.User;
import java.util.List;

public interface UserService {

    User addUser(User user);

    User updateUser(User user,int id);

    User getUserById(int id);

    User getUserByName(String name);

    User identifyUser();

    List<User> getAllUsers();

    String registrationUser(User user);

    List<User> getAllUsersInConversation(int conversationId);

    List<User> getUserFriends();

    User addToFriend(int friendId);

    String  varifyUser(String key,int id);

}
