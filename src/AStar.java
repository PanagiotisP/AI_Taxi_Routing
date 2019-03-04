import java.util.*;

public class AStar {
    private List<Node> nodeList;
    private Node target;
    private PriorityQueue<Node> openSet;
    private Set<Node> closedSet;

    AStar(List<Node> nodeList, Node target) {
        this.nodeList = nodeList;
        this.target = target;
        closedSet = new HashSet<>();
    }

    public void initialiseHeuristic() {
        for(Node node : nodeList) {
            node.calculateHeuristic(target);
        }
    }

    public void solve(Collection<Node> taxiList) {
        openSet = new PriorityQueue<>(taxiList.size(), new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                if(a.getHeuristicValue() + a.getPathCost() < b.getHeuristicValue() + b.getPathCost()) {
                    return -1;
                } else if(a.getHeuristicValue() + a.getPathCost() > b.getHeuristicValue() + b.getPathCost()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        for(Node taxiNode : taxiList) {
            taxiNode.setVisited(true);
        }
        openSet.addAll(taxiList);
        boolean found = false;
        while(!openSet.isEmpty()) {
            Node curNode = openSet.poll();
            if(curNode == target) {
                found = true;
            }
            if(!closedSet.contains(curNode)) {
                if(found && curNode.getPathCost() + curNode.getHeuristicValue() > target.getPathCost()) {
                    break;
                }
                closedSet.add(curNode);
                for(Node neighbour : curNode.getNeighbours()) {

                    // If node isn't visited or if a better path has been found, then set its path cost and add previous
                    if(!neighbour.isVisited() || curNode.getPathCost() + curNode.calculateDistance(neighbour) < neighbour.getPathCost()) {
                        neighbour.setPathCost(neighbour.calculateDistance(curNode) + curNode.getPathCost());
                        neighbour.getPrevious().clear();
                        neighbour.addPrevious(curNode);
                        if(!neighbour.isVisited()) {
                            neighbour.setVisited(true);
                            openSet.add(neighbour);
                        }
                    }

                    // If the algorithm's found an equivalent path, then add additional previous
                    else if(curNode.getPathCost() + curNode.calculateDistance(neighbour) == neighbour.getPathCost()) {
                        neighbour.addPrevious(curNode);
                    }
                }
            }
        }
    }
}
