package dao;

import model.User;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDAOImpl extends GenericDaoImpl<User> implements UserDAO {

    private final String getUserByNameQuery = "select u from User u where u.login = :Name";
    private final String getAllUserInConversationQuery = "SELECT u FROM Conversation c JOIN c.users u WHERE c.id = :idConversation";
    private final String getAllUserFriendsQuery = "SELECT f FROM User u JOIN u.friends f WHERE  u.id = :currentUserId";


    @Override
    public User getUserByName(String name){
        TypedQuery<User> namedQuery = em.createQuery(getUserByNameQuery, User.class).setParameter("Name",name);
        if (namedQuery.getResultList().isEmpty()){
            return null;
        }else return namedQuery.getSingleResult();
    }

    @Override
    public List<User> getAllUsersInConversation(int conversationId ) {
        Query query = em.createQuery(getAllUserInConversationQuery);
        query.setParameter("idConversation", conversationId);
        return (List<User>) query.getResultList();
    }

    @Override
    public List<User> getUserFriends(int currentUserId) {
        Query query = em.createQuery(getAllUserFriendsQuery);
        query.setParameter("currentUserId", currentUserId);
        return (List<User>) query.getResultList();
    }

}
