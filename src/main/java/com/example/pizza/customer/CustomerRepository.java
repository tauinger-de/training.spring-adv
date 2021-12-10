package com.example.pizza.customer;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Profile("default | customer | order")
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findByPhoneNumber(String phoneNumber);

    @Query("SELECT c FROM Customer c WHERE LOCATE(:prefix, c.phoneNumber) = 1")
    List<Customer> queryCustomersByPhoneNumberPrefix(@Param("prefix") String phoneNumberPrefix);

    @Query(value = "SELECT * FROM Customer c WHERE LOCATE(:prefix, c.phone) = 1", nativeQuery = true)
    List<Customer> queryCustomersByPhoneNumberPrefixNative(@Param("prefix") String phoneNumberPrefix);

}
