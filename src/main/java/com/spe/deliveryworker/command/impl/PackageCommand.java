package com.spe.deliveryworker.command.impl;

import com.spe.deliveryworker.command.Command;
import com.spe.deliveryworker.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Component("package")
@AllArgsConstructor
public class PackageCommand implements Command {

    private final FileService fileService;

    @Override
    public void execute(String[] args) {

        if (args.length < 3) {
            System.out.println("Invalid Command Argument(s)");
            System.exit(0);
        }

        String diffFile = args[0];
        String dstFolder = args[1];
        String srcFolder = args[2];

        try {
            Map<String, ArrayList<String>> testMap = fileService.readDiffFile(diffFile);
            fileService.createDirectories(dstFolder, testMap);
            fileService.copyFiles(dstFolder, testMap, srcFolder);
            fileService.packageDirectories(dstFolder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
