package com.example.Practice24.service;

import com.example.Practice24.models.MethodInvoker;
import com.example.Practice24.repository.DogRepository;
import com.example.Practice24.repository.UserRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;

@Service
@Slf4j
public class SchedulerService  {

    private final DogRepository dogRepository;
    private final UserRepository userRepository;
    private final MethodInvoker methodInvoker;
    private final String USER_FILE_PATH = "user.txt";
    private final String DOG_FILE_PATH = "dog.txt";
    private ObjectName objectName;

    @Autowired
    public SchedulerService(DogRepository dogRepository, UserRepository userRepository, MethodInvoker methodInvoker) {
        this.dogRepository = dogRepository;
        this.userRepository = userRepository;
        this.methodInvoker = methodInvoker;
    }

    @PostConstruct
    @SneakyThrows
    public void init() {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        objectName = new ObjectName("com.example.Practice22.models:type=MethodInvoker");
        mbs.registerMBean(methodInvoker, objectName);
    }

    @Scheduled(cron = "0 0/30 * * * ?")
    public void doScheduledTask() {
        delete();
        createNewFiles();
    }

    private void delete() {
        try {
            new File(USER_FILE_PATH)
                    .delete();
            new File(DOG_FILE_PATH)
                    .delete();
        } catch (Exception e) {
            log.error("Error delete files", e);
        }
    }

    private void createNewFiles() {
        try {
            createUserFile();
            createDogFile();
        } catch (IOException e) {
            log.error("Can't create file(s)", e);
        }
    }

    private void createUserFile() throws IOException {
        new FileWriter(USER_FILE_PATH)
                .append(userRepository.findAll().toString())
                .close();
    }

    private void createDogFile() throws IOException {
        new FileWriter(DOG_FILE_PATH)
                .append(dogRepository.findAll().toString())
                .close();
    }
}
