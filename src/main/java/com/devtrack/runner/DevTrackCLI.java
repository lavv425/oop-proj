package com.devtrack.runner;

import com.devtrack.service.ProjectServiceImpl;
import com.devtrack.factory.MenuFactory;
import com.devtrack.menu.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DevTrackCLI {

    @Autowired
    private ProjectServiceImpl projectService;

    public void run() {
        Scanner scanner = new Scanner(System.in);

        Menu mainMenu = MenuFactory.createMainMenu(projectService);
        mainMenu.display(scanner);
    }
}