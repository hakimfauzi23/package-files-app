package com.spe.deliveryworker;

import com.spe.deliveryworker.command.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class DeliveryScWorkerApplication implements CommandLineRunner {

    private final CommandHandler commandHandler;

    public static void main(String[] args) {
        SpringApplication.run(DeliveryScWorkerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length < 1) {
            System.out.println("No command specified. Usage: java -jar filename.jar <command> [args]");
            return;
        }

        String argument = args[0].toLowerCase();
        String[] commandArgs = new String[args.length - 1];
        System.arraycopy(args, 1, commandArgs, 0, commandArgs.length);
        commandHandler.handleCommand(argument, commandArgs);

        System.exit(0);
    }
}
