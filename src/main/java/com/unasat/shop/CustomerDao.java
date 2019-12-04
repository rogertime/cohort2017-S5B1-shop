package com.unasat.shop;

import com.unasat.shop.entity.Customer;

import java.util.List;

public interface CustomerDao {

    List<Customer> findAll();

    void addPerson(Customer customer);
}
