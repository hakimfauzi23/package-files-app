package com.spe.deliveryworker.service;

import com.spe.deliveryworker.data.ConfigurationDto;

import java.util.ArrayList;
import java.util.Map;

public interface FileService {

    void createDirectories(String dstDirectory, Map<String, ArrayList<String>> folderAndFolderMap);

    void copyFiles(String dstDirectory, Map<String, ArrayList<String>> folderAndFolderMap, String srcDirectory);

    void packageDirectories(String dstDirectory);

    Map<String, ArrayList<String>> getFolderMapping(ArrayList<String> diffFiles);

    ConfigurationDto readConfigurationJsonFile(String jsonFilePath);
}
