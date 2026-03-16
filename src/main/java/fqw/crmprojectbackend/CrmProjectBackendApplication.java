package fqw.crmprojectbackend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class CrmProjectBackendApplication {

    public static void main(String[] args) { SpringApplication.run(CrmProjectBackendApplication.class, args); }
}
