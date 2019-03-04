import java.util.*;

public class Node {
    private double x;
    private double y;
    private double heuristicValue = 0;
    private double pathCost = 0;
    private boolean visited = false;
    private List<Node> neighbours = new LinkedList<>();
    private Set<Node> previous = new HashSet<>();
    private List<Integer> startingIds = null;


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
        if(!(newNeighbour.getX() == x && newNeighbour.getY() == y)) {
            neighbours.add(newNeighbour);
        }
    }

    public void removeNeighbour(Node newNeighbour) {
        neighbours.remove(newNeighbour);
    }

    public void addPrevious(Node previousNode) {
        previous.add(previousNode);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void addTaxiId(int taxiId) {
        if(startingIds == null) {
            startingIds = new LinkedList<>();
        }
        startingIds.add(taxiId);
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

    public Set<Node> getPrevious() {
        return previous;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setPrevious(Set<Node> previous) {
        this.previous = previous;
    }

    public List<Integer> getStartingIds() {
        return startingIds;
    }

    public void setStartingIds(List<Integer> startingIds) {
        this.startingIds = startingIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node point = (Node) o;

        if (x != point.getX()) return false;
        if (y != point.getY()) return false;

        return true;
    }
}
