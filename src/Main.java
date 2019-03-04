import java.util.*;

public class Main {
    // This method takes the nodes input and returns a List of all different Nodes while connecting each node to its
    // neighbours
    public static List<Node> connectNeighbours(List<String[]> nodeStringList) {

        // Array which stores every Node (including duplicates). Used to find duplicates by sorting
        Node[] nodeArray = new Node[nodeStringList.size()];

        // Returned List
        List<Node> nodeList = new ArrayList<>();

        int prevLineId;
        int curLineId;

        String[] node = nodeStringList.get(0);
        nodeArray[0] = new Node(Double.parseDouble(node[0]), Double.parseDouble(node[1]));
        prevLineId = Integer.parseInt(node[2]);

        for(int i = 1; i < nodeStringList.size(); i++) {
            node = nodeStringList.get(i);
            nodeArray[i] = new Node(Double.parseDouble(node[0]), Double.parseDouble(node[1]));
            curLineId = Integer.parseInt(node[2]);

            // If nodes on same line, connect them
            if(prevLineId == curLineId) {
                nodeArray[i - 1].addNeighbour(nodeArray[i]);
                nodeArray[i].addNeighbour(nodeArray[i - 1]);
            }
        }

        // Sort array first by X and then by Y
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

        Node prevNode = nodeArray[0];
        Node curNode = null;
        for(int i = 1; i < nodeArray.length; i++) {
            curNode = nodeArray[i];

            // On duplicate Node, transfer previous appearance's neighbours to next one
            if(curNode.getX() == prevNode.getX() && curNode.getY() == prevNode.getY()) {
                for(Node neighbhour : prevNode.getNeighbours()) {
                    curNode.addNeighbour(neighbhour);
                    neighbhour.addNeighbour(curNode);
                }
            }

            // On not duplicate Node or on solo appearance, add it to List
            else {
                nodeList.add(prevNode);
            }
            prevNode = nodeArray[i];
        }
        nodeList.add(prevNode);

        return nodeList;
    }

    // This method finds the nearest (in distance) Node of given input coordinates
    // It is essential to the program, as the client and taxis input do not correspond to any given node
    public static Node findNearestNode(List<Node> nodeList, String[] input) {
        double candidateX = Double.parseDouble(input[0]);
        double candidateY = Double.parseDouble(input[1]);
        Node tempNode = new Node(candidateX, candidateY);
        Node inputNode = null;
        double minDistance = Double.MAX_VALUE;

        // Find nearest node to given inputNode
        for(Node candidateNode : nodeList) {
            double candidateDistance = candidateNode.calculateDistance(tempNode);
            if(minDistance > candidateDistance) {
                minDistance = candidateDistance;
                inputNode = candidateNode;
            }
        }

        // Add the distance as initial path cost
        inputNode.setPathCost(minDistance);
        return inputNode;
    }

    public static void main(String[] args) {
        String clientPath = "Data\\client.csv";
        String taxisPath = "Data\\taxis.csv";
        String nodesPath = "Data\\nodes.csv";
        List<Node> nodesList;

        CSVReader CSVFileReader = new CSVReader();

        List<String[]> inputStringList = CSVFileReader.read(nodesPath);
        inputStringList.remove(0);
        List<Node> nodeList = Main.connectNeighbours(inputStringList);

        // Read taxis and create a taxiList with taxi nodes
        inputStringList = CSVFileReader.read(taxisPath);
        inputStringList.remove(0);

        List<Node> taxiList = new LinkedList<>();
        for(String[] taxi : inputStringList) {
            taxiList.add(findNearestNode(nodeList, taxi));
        }


        // Read client and set a target node
        inputStringList = CSVFileReader.read(clientPath);
        inputStringList.remove(0);

        Node target = findNearestNode(nodeList, inputStringList.get(0));


    }
}
