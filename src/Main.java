import java.util.*;

public class Main {
    public List<Node> connectNeighbours(List<String[]> nodeStringList) {
        String[] first = nodeStringList.remove(0);
        Node[] nodeArray = new Node[nodeStringList.size()];

        int prevLineId;
        int curLineId;

        String[] node = nodeStringList.get(0);
        nodeArray[0] = new Node(Double.parseDouble(node[0]), Double.parseDouble(node[1]));
        prevLineId = Integer.parseInt(node[2]);

        for(int i = 1; i < nodeStringList.size(); i++) {
            node = nodeStringList.get(i);
            nodeArray[i] = new Node(Double.parseDouble(node[0]), Double.parseDouble(node[1]));
            curLineId = Integer.parseInt(node[2]);
            if(prevLineId == curLineId) {
                nodeArray[i - 1].addNeighbour(nodeArray[i]);
                nodeArray[i].addNeighbour(nodeArray[i - 1]);
            }
        }

        Arrays.sort(nodeArray, new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                if(a.getX() < b.getX()) {
                    return -1;
                }
                else if(a.getX() > b.getX()) {
                    return 1;
                }
                else {
                    if(a.getY() < b.getY()) {
                        return -1;
                    }
                    else if(a.getY() > b.getY()) {
                        return 1;
                    }
                }
                return 0;
            }
        });
        for(int i = 1; i < nodeArray.length; i++) {
            if(nodeArray[i].getX() == nodeArray[i - 1].getX() && nodeArray[i].getY() == nodeArray[i - 1].getY()) {
                nodeArray[i].g
            }
        }
        return
    }



    public static void main(String[] args) {
        String clientPath = "Data\\client.csv";
        String taxisPath = "Data\\taxis.csv";
        String nodesPath = "Data\\nodes.csv";
        List<Node> nodesList;

        CSVReader CSVFileReader = new CSVReader();

        List<String[]> nodeList = CSVFileReader.read(nodesPath);
        nodeList.remove(0);

    }
}
