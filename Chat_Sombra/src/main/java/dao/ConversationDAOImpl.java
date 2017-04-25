package dao;

import model.Conversation;
import javax.persistence.Query;
import java.util.List;

public class ConversationDAOImpl extends GenericDaoImpl<Conversation> implements ConversationDAO {

    private final String getAllConversationOfCurrentUser = "SELECT c FROM Conversation c JOIN c.users u WHERE u.id = :idUser";

    @Override
    public List<Conversation> getAllConversationOfCurrentUser(int userId) {
        Query query = em.createQuery(getAllConversationOfCurrentUser);
        query.setParameter("idUser", userId);
        return (List<Conversation>) query.getResultList();
    }
}
