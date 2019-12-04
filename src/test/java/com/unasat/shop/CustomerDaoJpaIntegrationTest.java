package com.unasat.shop;

import com.unasat.shop.entity.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;

public class CustomerDaoJpaIntegrationTest {

    private CustomerDaoJpa customerDaoJpa;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Before
    public void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("shop");
        entityManager = entityManagerFactory.createEntityManager();
        customerDaoJpa = new CustomerDaoJpa(entityManager);
    }

    @After
    public void tearDown() {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void shouldFindPreviouslySavedCustomer() {
        // given
        Integer age = 22;
        String firstName = "John";
        String lastName = "Connor";
        Calendar calendar = Calendar.getInstance();
        calendar.set(1988, Calendar.FEBRUARY, 5);
        Date birthDate = calendar.getTime();

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setBirthDate(birthDate);

        customerDaoJpa.addPerson(customer);

        // when
        List<Customer> result = customerDaoJpa.findAll();

        // then
        assertThat(result, hasSize(1));
        Customer foundCustomer = result.get(0);

        assertThat(foundCustomer.getBirthDate(), is(birthDate));
        assertThat(foundCustomer.getFirstName(), is(firstName));
        assertThat(foundCustomer.getLastName(), is(lastName));
    }
}
