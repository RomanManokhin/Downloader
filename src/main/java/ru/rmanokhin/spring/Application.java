package ru.rmanokhin.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ru.rmanokhin.spring.starter.StartProgramImpl;

@SpringBootApplication
public class Application {

    private static StartProgramImpl startProgramImpl;

    @Autowired
    public void setStart(StartProgramImpl startProgramImpl) {
        Application.startProgramImpl = startProgramImpl;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        startProgramImpl.start();

    }
}
