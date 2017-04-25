package service;

import dao.ConversationDAO;
import dao.MessageDAO;
import email.Sender;
import model.Message;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDAO messageDAO;

    @Autowired
    ConversationDAO conversationDAO;

    @Autowired
    UserService userService;

    @Autowired
    Sender sender;

    @Override
    public Message addMessage(Message message,int conversation_id) {
        if (message!=null) {
            User user = userService.identifyUser();
            message.setConversation(conversationDAO.getById(conversation_id));
            message.setUser(user);
            return messageDAO.add(message);
        }
        return null;
    }

    public void sendEmail(Message message, int conversation_id){
        List<User> userList = userService.getAllUsersInConversation(conversation_id);
        for (User u:userList) {
            sender.send("New message from Sombra_Chat","Message : ( "+message.getText()+" ) from conversation "
                            + conversationDAO.getById(conversation_id).getName(),"igor.igruk@gmail.com",
                    u.getEmail());
        }

    }


    @Override
    public List<Message> getAllMessagesByConversationId(int id) {
        return messageDAO.getAllMessagesByConversationId(id);
    }
}
