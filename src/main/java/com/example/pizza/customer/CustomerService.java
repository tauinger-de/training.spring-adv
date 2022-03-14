package com.example.pizza.customer;

import com.example.pizza.aop.LogExecutionTime;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Profile("default | customer | order")
public class CustomerService {

    //
    // injected beans
    //

    private final CustomerRepository repository;

    //
    // constructors and setup
    //

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    //
    // business logic
    //

    @NonNull
    @LogExecutionTime
    public Customer getCustomer(long id) {
        return this.repository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("For id `" + id + "`"));
    }

    @NonNull
    @LogExecutionTime
    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        return this.repository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new CustomerNotFoundException("For phoneNumber `" + phoneNumber + "`"));
    }

    @NonNull
    @LogExecutionTime
    public Iterable<Customer> getCustomers(Collection<Predicate<Customer>> filters) {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false)
                .filter(c -> filters.stream().allMatch(f -> f.test(c)))
                .collect(Collectors.toList());
    }


    @NonNull
    @LogExecutionTime
    public Iterable<Customer> getAllCustomers() {
        return getCustomers(null);
    }


    @NonNull
    @LogExecutionTime
    public Customer createCustomer(Customer customer) {
        return this.repository.save(customer);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void increaseOrderCount(long customerId) {
        // obtain entity of customer valid for the new transaction that was started for this method
        Optional<Customer> customerFromThisTrx = this.repository.findById(customerId);

        // customer might not yet be visible to this transaction in case it just has been created
        customerFromThisTrx.ifPresent(Customer::increaseOrderCount);
    }

}
