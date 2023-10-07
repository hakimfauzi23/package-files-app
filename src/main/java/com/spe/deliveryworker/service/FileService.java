package com.spe.deliveryworker.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public interface FileService {

    Map<String, ArrayList<String>> readDiffFile(String fileDir) throws IOException;

    void createDirectories(String dstDirectory, Map<String, ArrayList<String>> folderAndFolderMap);

    void copyFiles(String dstDirectory, Map<String, ArrayList<String>> folderAndFolderMap, String srcDirectory);

    void packageDirectories(String dstDirectory) throws IOException;
}
