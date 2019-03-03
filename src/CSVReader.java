import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private List<String[]> fields;
    private BufferedReader reader;
    private String filename;

    public CSVReader() {};

    // Function that returns CSV file content in form of List of String arrays
    // or null on failure
    public List<String[]> read(String filename) {
        fields = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {

                // use comma as separator
                fields.add(line.split(","));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fields;
    }
}
