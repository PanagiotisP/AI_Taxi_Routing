import java.util.List;

public class Main {
    public static void main(String[] args) {
        String clientPath = "Data\\client.csv";
        String taxisPath = "Data\\taxis.csv";
        String nodesPath = "Data\\nodes.csv";


        CSVReader CSVFileReader = new CSVReader();

        List<String[]> test = CSVFileReader.read(clientPath);
    }
}
