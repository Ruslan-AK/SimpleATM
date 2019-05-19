package com.reneuby.dao.impl;

import com.reneuby.dao.CustomerRepository;
import com.reneuby.domain.Customer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "CustomerRepositoryImpl")
@Primary
public class CustomerRepositoryImpl implements CustomerRepository {
    @Autowired
    protected SessionFactory factory;

    @Override
    public <S extends Customer> S save(S s) {
        Session session = factory.openSession();
        Transaction tr = session.beginTransaction();
        session.save(s);
        tr.commit();
        session.close();
        return s;
    }

    @Override
    public <S extends Customer> Iterable<S> save(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Customer findOne(Long aLong) {
        Session session = factory.openSession();
        Customer customer = session.get(Customer.class, aLong);
        session.close();
        return customer;
    }

    @Override
    public boolean exists(Long aLong) {
        return findOne(aLong) == null;
    }

    @Override
    public Iterable<Customer> findAll() {
        Iterable result = factory.openSession().createQuery("from Customer").list();
        factory.getCurrentSession().close();
        return result;
    }

    @Override
    public Iterable<Customer> findAll(Iterable<Long> iterable) {
        String idString = iterable.toString().replace("[", "").replace("]", "");
        Iterable result = factory.openSession().createQuery("from Customer c where c.id in " + idString + ";").list();
        factory.getCurrentSession().close();
        return result;
    }

    @Override
    public long count() {
        return ((List<Customer>) findAll()).size();
    }

    @Override
    public void delete(Long aLong) {
        delete(findOne(aLong));
    }

    @Override
    public void delete(Customer customer) {
        Session session = factory.openSession();
        Transaction tr = session.beginTransaction();
        session.delete(customer);
        tr.commit();
        session.close();
    }

    @Override
    public void delete(Iterable<? extends Customer> iterable) {
        for (Customer customer : iterable) {
            delete(customer);
        }
    }

    @Override
    public void deleteAll() {
        delete(findAll());
    }

    @Override
    public boolean update(Customer customer) {
        Transaction tr = null;
        try {
            Session session = factory.openSession();
            tr = session.beginTransaction();
            session.update(customer);
            tr.commit();
            session.close();
            return true;
        } catch (Exception e) {
            tr.rollback();
            return false;
        }
    }

    @Override
    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        factory.getCurrentSession().close();
        Query query = factory.openSession().createQuery("SELECT c FROM Customer c WHERE c.phoneNumber=:phone");
        query.setParameter("phone", phoneNumber);
        Customer customer = (Customer) query.uniqueResult();
        return customer;
    }
}
