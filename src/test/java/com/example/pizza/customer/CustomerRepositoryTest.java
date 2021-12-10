package com.example.pizza.customer;

import org.assertj.core.api.Assertions;
import org.h2.tools.Server;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.sql.SQLException;

@SpringBootTest
@TestPropertySource(properties = {"spring.datasource.url = jdbc:h2:mem:testing"})
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;


    @BeforeAll
    public static void initTest() throws SQLException {
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082")
                .start();
    }

    @Test
    void queryCustomersByPhoneNumberPrefix() throws Exception {
        // given
        customerRepository.save(new Customer("A", null, "222 333"));
        customerRepository.save(new Customer("B", null, "222333"));
        customerRepository.save(new Customer("C", null, "23"));

        // sleep so we can access h2 console at http://localhost:8082 using the DB-URL configured above
//        Thread.sleep(600000);

        // when #1 -- query including whitespace
        var customers = customerRepository.queryCustomersByPhoneNumberPrefix("222 ");

        // then #1
        Assertions.assertThat(customers).hasSize(1);

        // when #2 -- query just triple 2
        customers = customerRepository.queryCustomersByPhoneNumberPrefix("222");

        // then #2
        Assertions.assertThat(customers).hasSize(2);

        // when #3 -- query just single 2
        customers = customerRepository.queryCustomersByPhoneNumberPrefix("2");

        // then #3
        Assertions.assertThat(customers).hasSize(3);

        // when #4 -- query a single 3
        customers = customerRepository.queryCustomersByPhoneNumberPrefix("3");

        // then #3
        Assertions.assertThat(customers).hasSize(0);
    }


    @Test
    void queryCustomersByPhoneNumberPrefixNative() {
        // given
        customerRepository.save(new Customer("X", null, "987-456-789"));

        // when
        var customers = customerRepository.queryCustomersByPhoneNumberPrefixNative("987-");

        // then
        Assertions.assertThat(customers).hasSize(1);
    }
}
