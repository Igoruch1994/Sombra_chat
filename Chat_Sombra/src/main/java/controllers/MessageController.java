package controllers;

import model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.MessageService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping(value="message/conversation/{id}", method = RequestMethod.GET, produces = "application/json")
    public List<Message> getMessageByConversationId(@PathVariable int id){
        return messageService.getAllMessagesByConversationId(id);
    }

    @RequestMapping(value = "message/conversation/{id}", method = RequestMethod.POST, produces = "application/json")
    public Message createProduct(@PathVariable int id,@RequestBody Message message) {
        return messageService.addMessage(message,id);
    }

    @RequestMapping(value = "message/email/conversation/{id}", method = RequestMethod.POST, produces = "application/json")
    public void sendEmail(@PathVariable int id,@RequestBody Message message){
        messageService.sendEmail(message,id);
    }

}
