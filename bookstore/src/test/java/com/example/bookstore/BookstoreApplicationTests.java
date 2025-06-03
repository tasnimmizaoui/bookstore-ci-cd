package com.example.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")  // This tells Spring to use application-test.properties
class BookstoreApplicationTests {

    @Test
    void contextLoads() {
        // This test will now pass because it uses H2 instead of PostgreSQL
    }

}