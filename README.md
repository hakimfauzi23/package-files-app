# Packaging Source Code Tools
<p align="center">
  <img src="./flow.gif" alt="App Flow" />
</p>

## Overview

When confronted with the task of copying files from source code where each file resides in a different directory, the manual approach involves creating directories before copying files. While this is manageable for a small number of files, it becomes time-consuming and inefficient when dealing with numerous files scattered across various folders.

This tool is designed to simplify this process. It eliminates the need for manual directory creation by allowing you to list file paths in a `.json` file. Running a single command automates the creation of directories, copies the specified files, and finalizes the process by compressing the result into a convenient zip file.

## Why Using This Tools?

- **Efficiency**: Save time by automating the directory creation and file copying process.
- **Flexibility**: Manage numerous files with diverse directory structures effortlessly.

## Getting Started

1. Clone the repository: `git clone https://github.com/hakimfauzi23/package-files-app.git`
2. Navigate to the project directory: `cd package-files-app`
3. **Prepare `.json` File**: Create a json file (`config.json`) listing the file paths to be copied and so source folder that wanna be copied and destination folder.

    ```json
    {
      "srcFolder": "path/to/your/source/folder",
      "dstFolder": "path/to/your/destination/folder",
      "diffFiles": [
         "src/main/java/com/hakimfauzi23/deliveryworker/DeliveryScWorkerApplication.java",
         "src/main/java/com/hakimfauzi23/deliveryworker/service/FileService.java",
         "src/main/java/com/hakimfauzi23/deliveryworker/service/impl/FileServiceImpl.java",
         "src/main/java/com/hakimfauzi23/deliveryworker/helper/ZipHelper.java",
         "src/main/java/com/hakimfauzi23/deliveryworker/data/ConfigurationDto.java"
      ]
   }
    ```
4. Build the application JAR File: `mvn clean package`
5. Run the application by runnning the JAR File : `java -jar target/package-files-app-1.0.0.jar package path/to/your/config.json`
6. Outcome: Will copy all the listed file to Destination Folder and ZIP overall files.

## Dependencies

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) - Required for running the application.
- [Spring Boot](https://spring.io/projects/spring-boot) - Framework for creating stand-alone, production-grade Spring-based applications.
- [Spring Web](https://spring.io/guides/gs/spring-boot/) - Spring framework for creating and add message converter in Rabbit MQ Purposes.
- [Jackson Faster XML](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core) - Object Mapper for reading config files as JSON.

## Contributing

Contributions are welcome! If you encounter any issues or have suggestions for improvements, please feel free to open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT) - see the [LICENSE.md](LICENSE.md) file for details.