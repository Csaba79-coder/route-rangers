package hu.pannonuni.routerangers;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RouteRangersApplicationTests {

    @BeforeAll
    static void loadEnv() {
        Dotenv dotenv = Dotenv.configure()
                .filename(".env")
                .load();
        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue()));
    }

    @Test
    void contextLoads() {
    }

}
