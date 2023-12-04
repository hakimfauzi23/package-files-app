package com.spe.deliveryworker.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigurationDto {

    private String srcFolder;
    private String dstFolder;
    private ArrayList<String> diffFiles;

}
