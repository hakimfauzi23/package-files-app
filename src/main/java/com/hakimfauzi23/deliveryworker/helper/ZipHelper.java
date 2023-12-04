package com.hakimfauzi23.deliveryworker.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipHelper {

    public static void zipFile(File fileToZip,
                               String fileName,
                               ZipOutputStream zipOutputStream) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }

        if (fileToZip.isDirectory()) {
            File[] children = fileToZip.listFiles();
            for (File child : children) {
                zipFile(child, fileName + "/" + child.getName(), zipOutputStream);
            }
        } else {
            byte[] buffer = new byte[1024];
            try (FileInputStream fileInputStream = new FileInputStream(fileToZip)) {
                zipOutputStream.putNextEntry(new ZipEntry(fileName));
                int length;
                while ((length = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, length);
                }
                zipOutputStream.closeEntry();
            }
        }
    }
}
