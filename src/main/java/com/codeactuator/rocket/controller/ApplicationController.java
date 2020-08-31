package com.codeactuator.rocket.controller;

import java.util.Collection;

public interface ApplicationController<T> {

    public String ping();
    public Collection<T> findAll();
    public T findById(Long projectId);
    public T create(T t);
    public T update(T t);
    public T delete(Long id);

}
