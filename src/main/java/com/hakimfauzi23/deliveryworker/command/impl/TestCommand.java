package com.hakimfauzi23.deliveryworker.command.impl;

import com.hakimfauzi23.deliveryworker.command.Command;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component("test")
@AllArgsConstructor
public class TestCommand implements Command {


    @Override
    public void execute(String[] args) {

        String jsonFile = args[0];

        System.out.println(jsonFile);
    }
}
