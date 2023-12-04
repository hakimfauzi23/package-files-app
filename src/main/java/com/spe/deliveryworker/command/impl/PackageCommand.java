package com.spe.deliveryworker.command.impl;

import com.spe.deliveryworker.command.Command;
import com.spe.deliveryworker.data.ConfigurationDto;
import com.spe.deliveryworker.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component("package")
@AllArgsConstructor
public class PackageCommand implements Command {

    private final FileService fileService;

    @Override
    public void execute(String[] args) {

        String jsonFilePath = args[0];
        ConfigurationDto configurationDto = fileService.readConfigurationJsonFile(jsonFilePath);

        Map<String, ArrayList<String>> folderMapping = fileService.getFolderMapping(configurationDto.getDiffFiles());
        fileService.createDirectories(configurationDto.getDstFolder(), folderMapping);
        fileService.copyFiles(configurationDto.getDstFolder(), folderMapping, configurationDto.getSrcFolder());
        fileService.packageDirectories(configurationDto.getDstFolder());

    }
}
