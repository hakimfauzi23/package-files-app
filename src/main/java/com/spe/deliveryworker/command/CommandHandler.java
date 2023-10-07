package com.spe.deliveryworker.command;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class CommandHandler {

    private final Map<String, Command> commandMap;

    public void handleCommand(String commandName, String[] args) {
        Command command = commandMap.get(commandName);
        if (command == null) {
            System.out.println("Invalid command. Use 'help' for a list of available commands.");
            return;
        }

        command.execute(args);
    }

}
