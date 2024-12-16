package com.epa.m.clean.task4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
class SampleController {
    @GetMapping("/unsafe")
    public String unsafeMethod(String input) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "password");
            Statement statement = connection.createStatement();
            // Intentional SQL Injection flaw
            statement.executeQuery("SELECT * FROM user_data WHERE user_name = '" + input + "'");
            return "Data Retrieved";
        } catch (SQLException e) {
            return "Error in SQL Handling";
        }
    }

    @GetMapping("/logic-error")
    public int faultyLogic() {
        int a = 10;
        int b = 0;
        // Intentional Division by Zero error
        return a / b;
    }

    @GetMapping("duplicate")
    public int duplicate() {
        int duplicated = 0;
        for (int i = 1; i <= 10; i++) {
            duplicated += duplicated;
        }
        return duplicated;
    }

    @GetMapping("duplicate2")
    public int duplicate2() {
        int duplicated = 0;
        for (int i = 1; i <= 10; i++) {
            duplicated += duplicated;
        }
        return duplicated;
    }
}