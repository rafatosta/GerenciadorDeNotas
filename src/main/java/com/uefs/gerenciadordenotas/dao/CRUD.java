package com.uefs.gerenciadordenotas.dao;

import java.util.List;

public interface CRUD<T> {

    public T create(T obj);

    public T findById(int id);

    public List<T> findMany();

    public void update(T obj);

    public void delete(int id) ;

    public void deleteMany();
}
