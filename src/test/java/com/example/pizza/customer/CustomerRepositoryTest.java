package com.example.pizza.customer;

import org.assertj.core.api.Assertions;
import org.h2.tools.Server;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.sql.SQLException;

@DataJpaTest(
        properties = {"spring.datasource.url=jdbc:h2:mem:testing"},
        showSql = false
)
// otherwise connection string is ignored:
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeAll
    public static void initTest() throws SQLException {
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082")
                .start();
    }

    @Test
    void queryCustomersByPhoneNumberPrefix() {
        // THIS DOESN'T WORK ANYMORE - @DataJpaTest is @transactional -- nothing is written!

        // if you want to access the h2 web console:
        // - set a THREAD-ONLY breakpoint anywhere below this line
        // - access the h2 console at http://localhost:8082 using the DB-URL configured at class level (default username and pwd)

        // given
        testEntityManager.persist(new Customer("A", null, "222 333"));
        testEntityManager.persist(new Customer("B", null, "222333"));
        testEntityManager.persist(new Customer("C", null, "23"));

        // when #1 -- query including whitespace
        var customers1 = customerRepository.queryCustomersByPhoneNumberPrefix("222 ");

        // then #1
        Assertions.assertThat(customers1).hasSize(1);

        // when #2 -- query just triple 2
        var customers2 = customerRepository.queryCustomersByPhoneNumberPrefix("222");

        // then #2
        Assertions.assertThat(customers2).hasSize(2);

        // when #3 -- query just single 2
        var customers3 = customerRepository.queryCustomersByPhoneNumberPrefix("2");

        // then #3
        Assertions.assertThat(customers3).hasSize(3);

        // when #4 -- query a single 3
        var customers4 = customerRepository.queryCustomersByPhoneNumberPrefix("3");

        // then #3
        Assertions.assertThat(customers4).hasSize(0);
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
