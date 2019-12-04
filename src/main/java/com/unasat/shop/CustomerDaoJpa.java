package com.unasat.shop;

import com.unasat.shop.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoJpa implements CustomerDao {

    private EntityManager entityManager;

    public CustomerDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> result = new ArrayList<>();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            result = entityManager.createQuery("SELECT c FROM Customer c").getResultList();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return result;
    }

    @Override
    public void addPerson(Customer customer) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(customer);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

}
