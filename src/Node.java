import java.util.ArrayList;
import java.util.List;

public class Node {
    private double x;
    private double y;
    private double heuristicValue = 0;
    private double pathCost = 0;
    private List<Node> neighbours = new ArrayList<>();

    Node(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double calculateDistance(Node target) {
        Haversine distanceCalculator = new Haversine();
        return distanceCalculator.haversine(y, x, target.getY(), target.getX());
    }

    public void calculateHeuristic(Node target) {
        heuristicValue = calculateDistance(target);
    }

    public void addNeighbour(Node newNeighbour) {
        neighbours.add(newNeighbour);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getHeuristicValue() {
        return heuristicValue;
    }

    public void setHeuristicValue(double heuristicValue) {
        this.heuristicValue = heuristicValue;
    }

    public double getPathCost() {
        return pathCost;
    }

    public void setPathCost(double pathCost) {
        this.pathCost = pathCost;
    }

    public List<Node> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Node> neighbours) {
        this.neighbours = neighbours;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
