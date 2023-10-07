package com.spe.deliveryworker.command.impl;

import com.spe.deliveryworker.command.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("help")
@Slf4j
public class HelpCommand implements Command {
    @Override
    public void execute(String[] args) {
        log.info("HELP COMMAND TEST");
    }
}
