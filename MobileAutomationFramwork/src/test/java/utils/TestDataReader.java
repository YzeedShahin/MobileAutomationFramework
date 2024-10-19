package utils;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

public class TestDataReader {

    Map<String,String> testData;

    public TestDataReader(){
        loadTestData();
    }

    private void loadTestData(){
        String testDataPath = System.getProperty("user.dir") + File.separator + "data" + File.separator + "test_data.yml";
        try (FileInputStream input = new FileInputStream(testDataPath)) {
            Yaml yaml = new Yaml();
            testData = yaml.load(input);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found: " + testDataPath, e);
        } catch (Exception e) {
            throw new RuntimeException("Error loading YAML data", e);
        }
    }

    public String getValue(String key){
        return testData.get(key);
    }
}
