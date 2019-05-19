package com.reneuby.dao;

import com.reneuby.domain.AbstractEntity;
import com.reneuby.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonRepository<E extends AbstractEntity> extends CrudRepository<E, Long> {
    boolean update(Customer customer);
}
