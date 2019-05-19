package com.reneuby.service;

import com.reneuby.domain.AbstractEntity;

public interface CommonService<E extends AbstractEntity> {

    Long save(E entity);

    E get(Long id);

    void delete(E entity);

    boolean update(E entity);
}
