package dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class GenericDaoImpl<T> implements GenericDao<T> {

    public EntityManager em;

    private Class<T> type;


    public GenericDaoImpl() {
        em = Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public T add(T t) {
        em.getTransaction().begin();
        T tFromDB = em.merge(t);
        em.getTransaction().commit();
        return tFromDB;
    }

    @Override
    public T update(T t) {;
        em.getTransaction().begin();
        T fromDB=em.merge(t);
        em.getTransaction().commit();
        return fromDB;
    }

    @Override
    public T getById(int id) {
        return em.find(type, id);
    }

    @Override
    public List<T> getAll() {
        TypedQuery<T> namedQuery = em.createNamedQuery(type.getTypeName()+".getAll", type);
        return namedQuery.getResultList();
    }
}
