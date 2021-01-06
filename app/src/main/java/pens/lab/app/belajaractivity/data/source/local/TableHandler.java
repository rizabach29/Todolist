package pens.lab.app.belajaractivity.data.source.local;

import java.util.List;

public interface TableHandler<T, S> {
    boolean create(T t);
    boolean delete(S s);
    boolean update(T t);
    List<T> selectAll();
    T selectById(S t);
}
