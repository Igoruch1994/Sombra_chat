package dao;


import model.Message;

import javax.persistence.Query;
import java.util.List;

public class MessageDAOImpl extends GenericDaoImpl<Message> implements MessageDAO {

    private final String getMessagesByConversationIdQuery = "SELECT m FROM Conversation c JOIN c.messageSet m WHERE c.id = :idConversation";

    @Override
    public List<Message> getAllMessagesByConversationId(int id) {
        Query query = em.createQuery(getMessagesByConversationIdQuery);
        query.setParameter("idConversation", id);
        return (List<Message>) query.getResultList();
    }
}
