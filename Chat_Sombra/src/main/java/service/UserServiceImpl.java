package service;

import dao.UserDAO;
import email.Sender;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    Sender sender;

    @Override
    public User addUser(User user) {
        if (user!=null){
            return userDAO.add(user);
        }
        return null;
    }

    @Override
    public User updateUser(User user, int id) {

        User userInDB=this.getUserById(id);
        if (user!=null&&userInDB!=null) {
            if (user.getLogin()!=null) {
                userInDB.setLogin(user.getLogin());
            }
            if (user.getEmail()!=null) {
                userInDB.setEmail(user.getEmail());
            }
            if (user.getPassword()!=null) {
                userInDB.setPassword(user.getPassword());
            }
            if (user.getPhone()!=null) {
                userInDB.setPhone(user.getPhone());
            }
            if (user.isEnabled()!=userInDB.isEnabled()){
                userInDB.setEnabled(user.isEnabled());
            }
        }
        return userDAO.update(userInDB);
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDAO.getUserByName(name);
    }

    @Override
    public User identifyUser() {
        try {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userDAO.getUserByName(user.getUsername());
        }catch (ClassCastException e){
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    @Override
    public String registrationUser(User user) {
        final String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}$";
        final String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        if (user==null){
            return "Empty field";
        }
        if (user.getLogin()==null||user.getLogin().length()<6){
            return "Invalid login. Less then 6 characters";
        }
        if (getUserByName(user.getLogin())!=null){
            return "User with this login already exist!";
        }
        if (user.getPassword()==null){
            return "Invalid password!";
        }
        if (user.getEmail()==null||!user.getEmail().matches(emailRegex)){
            return "Invalid email!";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User userFromDB = addUser(user);
        String hashedLogin = user.getLogin().hashCode()+user.getPhone().hashCode()+"/"+userFromDB.getId();
        sender.send("Verification: Sombra_Chat","Your verification link : http://localhost:8080/email/varification/"+ hashedLogin
                        ,"igor.igruk@gmail.com",
                userFromDB.getEmail());

        return  "Success registration! Verify your account with email !";
    }

    @Override
    public List<User> getAllUsersInConversation(int conversationId) {
        return userDAO.getAllUsersInConversation(conversationId);
    }

    @Override
    public List<User> getUserFriends() {
        return userDAO.getUserFriends(this.identifyUser().getId());
    }

    @Override
    public User addToFriend(int friendId) {
        User user = this.identifyUser();
        User userFriend = this.getUserById(friendId);
        if (user.getFriends()==null) {
            List<User> listOfUser = new ArrayList<>();
            listOfUser.add(userFriend);
            user.setFriends(listOfUser);
            return this.updateUser(user, user.getId());
        }
        else {
            List<User> userList = user.getFriends();
            for (User u: userList
                 ) {
                if (u.getLogin()==userFriend.getLogin()){
                    return null;
                }
            }
            userList.add(userFriend);
            return this.updateUser(user, user.getId());
        }
    }

    @Override
    public String  varifyUser(String key,int id) {
        User user = userDAO.getById(id);
        String hashCompare = user.getLogin().hashCode()+user.getPhone().hashCode()+"";
        if (hashCompare.equals(key)){
            user.setEnabled(true);
            userDAO.update(user);
            return "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Varification!</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div><h1>Success registration</h1> " +
                    " <a href=\"http://localhost:63343/Chat_Sombra_client/index.html?_ijt=o7j01j1d17tlr0292eq890j6gq#/login/\">Visit our chat</a> "+
                    "</div>" +
                    "\n" +
                    "</body>\n" +
                    "</html>";
        }else {
            return "<div>Invalid registration!</div>";
        }

    }
}
