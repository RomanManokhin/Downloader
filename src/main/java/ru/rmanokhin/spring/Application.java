package ru.rmanokhin.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {

//    private static StartProgramImpl startProgramImpl;
//
//    @Autowired
//    public void setStart(StartProgramImpl startProgramImpl) {
//
//        Application.startProgramImpl = startProgramImpl;
//
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        startProgramImpl.start();
    }


}
