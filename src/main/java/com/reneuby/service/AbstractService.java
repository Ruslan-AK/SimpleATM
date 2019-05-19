package com.reneuby.service;

import com.reneuby.dao.CommonRepository;
import com.reneuby.domain.AbstractEntity;

public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>>
        implements CommonService<E> {

    protected final R repository;

    public AbstractService(R repository) {
        this.repository = repository;
    }

//другие методы, переопределённые из интерфейса
}
