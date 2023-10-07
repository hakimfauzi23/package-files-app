package com.spe.deliveryworker.service.impl;

import com.spe.deliveryworker.helper.ZipHelper;
import com.spe.deliveryworker.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipOutputStream;

@Service
@AllArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    private final ResourceLoader resourceLoader;

    public static String combineArrayElements(String[] array, int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex >= array.length || startIndex > endIndex) {
            throw new IllegalArgumentException("Invalid start or end index.");
        }

        String[] subArray = Arrays.copyOfRange(array, startIndex, endIndex + 1);
        return String.join("/", subArray);
    }


    @Override
    public Map<String, ArrayList<String>> readDiffFile(String fileDir) throws IOException {

        log.info("Reading " + fileDir + " -- START --");
        Map<String, ArrayList<String>> folderAndFileMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] filePathArr = line.split("/");
                String fileName = filePathArr[filePathArr.length - 1];
                String filePath = combineArrayElements(filePathArr, 0, (filePathArr.length - 2));

                ArrayList<String> fileNames;
                if (!folderAndFileMap.containsKey(filePath)) {
                    fileNames = new ArrayList<>();
                } else {
                    fileNames = folderAndFileMap.get(filePath);
                    if (fileNames.contains(fileName)) {
                        continue;
                    }
                }
                fileNames.add(fileName);
                folderAndFileMap.put(filePath, fileNames);

            }

            log.info("Successfully read " + fileDir);
        }

        log.info("Reading " + fileDir + " -- END --");
        return folderAndFileMap;
    }

    @Override
    public void createDirectories(String dstDirectory, Map<String, ArrayList<String>> folderAndFolderMap) {

        log.info("Creating destination directories -- START --");
        File dstDirFile = new File(dstDirectory);

        if (!dstDirFile.exists()) {
            boolean isDstDirCreated = dstDirFile.mkdirs();
            if (!isDstDirCreated) {
                log.warn("Failed to create destination directory");
            }
        }

        for (String folder : folderAndFolderMap.keySet()) {
            File directory = new File(dstDirectory + folder);
            boolean isSuccess = directory.mkdirs();
            if (!isSuccess) {
                log.warn("Failed to create filepath(s)");
            }
        }

        log.info("Creating destination directories -- END --");
    }

    @Override
    public void copyFiles(String dstDirectory, Map<String, ArrayList<String>> folderAndFolderMap, String srcDirectory) {

        log.info("Copying file(s) to destination directory -- START --");
        for (String folder : folderAndFolderMap.keySet()) {
            ArrayList<String> fileList = folderAndFolderMap.get(folder);
            for (String fileName : fileList) {
                File srcFile = new File(srcDirectory + "/" + folder + "/" + fileName);
                File dstFile = new File(dstDirectory + "/" + folder + "/" + fileName);

                try (InputStream inputStream = new FileInputStream(srcFile);
                     OutputStream outputStream = new FileOutputStream(dstFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, length);
                    }
                    log.info("Successfully copy :" + dstDirectory + "/" + folder + "/" + fileName);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        log.info("Copying file(s) to destination directory -- END --");

    }

    @Override
    public void packageDirectories(String dstDirectory) throws IOException {
        log.info("Zipping directory source code -- START -- ");
        String[] dstDirectoryArr = dstDirectory.split("/");
        StringBuilder zipFilePath = new StringBuilder();

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmm");
        String timestamp = currentDateTime.format(formatter);

        for (int i = 0; i < (dstDirectoryArr.length - 1); i++) {
            zipFilePath.append(dstDirectoryArr[i]).append("/");
        }

        zipFilePath.append(dstDirectoryArr[dstDirectoryArr.length - 1])
                .append("-")
                .append(timestamp)
                .append(".zip");

        try (FileOutputStream foStream = new FileOutputStream(String.valueOf(zipFilePath));
             ZipOutputStream zoStream = new ZipOutputStream(foStream)) {

            File sourceFolder = new File(dstDirectory);
            ZipHelper.zipFile(sourceFolder, sourceFolder.getName(), zoStream);
        }

        log.info("Successfully zip folder " + zipFilePath);
        log.info("Zipping directory source code -- END -- ");
    }


}
