package pl.lsobotka.green.code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class BaseTest {

    private static final String RESOURCE_PATH = "src/test/resources/";

    public String getFileInput(final String fileName) {
        try (FileReader fileReader = new FileReader(RESOURCE_PATH.concat(fileName))) {
            return new BufferedReader(fileReader).lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            throw new RuntimeException("Can not read file " + fileName, e);
        }
    }

    public List<String> getFileInputLines(final String fileName) {
        try (FileReader fileReader = new FileReader(RESOURCE_PATH.concat(fileName))) {
            return new BufferedReader(fileReader).lines().toList();
        } catch (Exception e) {
            throw new RuntimeException("Can not read file " + fileName, e);
        }
    }

}
