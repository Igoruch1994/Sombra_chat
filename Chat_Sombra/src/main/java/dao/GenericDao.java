package dao;

import java.util.List;

public interface GenericDao<T> {

    T add(T t);

    T update(T t);

    T getById(int id);

    List<T> getAll();

}
