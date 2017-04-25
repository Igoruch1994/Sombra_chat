package controllers;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import service.UserService;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value="user", method = RequestMethod.GET , produces = "application/json")
    public List<User> getListUser(){
        List<User> users = userService.getAllUsers();
        return users;
    }

    @RequestMapping(value = "user/friend/{friendId}", method = RequestMethod.GET, produces = "application/json")
    public User addFriendToUser(@PathVariable int friendId) {
        return userService.addToFriend(friendId);
    }

    @RequestMapping(value = "user/friend", method = RequestMethod.GET, produces = "application/json")
    public List<User> getFriendsOfUser() {
        return userService.getUserFriends();
    }

    @RequestMapping(value = "/identifyUser", method = RequestMethod.GET, produces = "application/json")
    public User identifyUser() {
        return userService.identifyUser();
    }

    @RequestMapping(value="user/{id}", method = RequestMethod.GET, produces = "application/json")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.POST)
    public User updateUser(@RequestBody User user,@PathVariable int id) {
        return userService.updateUser(user,id);
    }

    @RequestMapping(value="user/byName/{name}", method = RequestMethod.GET, produces = "application/json")
    public User getUserByName(@PathVariable String name){
        return userService.getUserByName(name);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = "text/plain")
    public String  registrationUser(@RequestBody User user) {
        System.out.println(user.toString());
        return userService.registrationUser(user);
    }

    @RequestMapping(value="email/varification/{key}/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String  varifyUser(@PathVariable String key,@PathVariable int id, HttpServletResponse response) {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        return userService.varifyUser(key,id);
    }

    @RequestMapping(value="user/conversation/{conversationId}", method = RequestMethod.GET, produces = "application/json")
    public List<User> getUserInConversation(@PathVariable int conversationId){
        return userService.getAllUsersInConversation(conversationId);
    }

}
