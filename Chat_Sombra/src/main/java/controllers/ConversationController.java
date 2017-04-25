package controllers;

import model.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ConversationService;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ConversationController {

    @Autowired
    ConversationService conversationService;

    @RequestMapping(value="/conversation", method = RequestMethod.GET, produces = "application/json")
    public List<Conversation> getAllConversation(){
        return conversationService.getAllConversationOfCurrentUser();
    }

    @RequestMapping(value="/conversation/user/{conversationId}/{userId}", method = RequestMethod.GET, produces = "application/json")
    public Conversation addUserInConversation(@PathVariable int conversationId,@PathVariable int userId){
        return conversationService.addUserInConversation(conversationId,userId);
    }

    @RequestMapping(value = "conversation", method = RequestMethod.POST, produces = "application/json")
    public Conversation createConversation(@RequestBody Conversation conversation) {
        return conversationService.addConversation(conversation);
    }

    @RequestMapping(value = "conversation/{id}", method = RequestMethod.POST, produces = "application/json")
    public Conversation updateConversation(@PathVariable int id,@RequestBody Conversation conversation) {
        return conversationService.updateConversation(conversation,id);
    }

}
