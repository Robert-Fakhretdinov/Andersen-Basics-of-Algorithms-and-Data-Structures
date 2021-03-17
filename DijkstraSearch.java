import java.util.*;

public class DijkstraSearch {
    public static void main(String[] args) {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        Node nodeH = new Node("H");

        nodeA.addWay(nodeB, 23);
        nodeA.addWay(nodeC, 11);

        nodeB.addWay(nodeD, 15);
        nodeB.addWay(nodeF, 12);

        nodeC.addWay(nodeE, 85);

        nodeD.addWay(nodeE, 12);
        nodeD.addWay(nodeF, 12);

        nodeF.addWay(nodeE, 5);

        Graph graph = new Graph();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        graph = Graph.dijkstraSearch(graph, nodeA);

    }
}

class Graph {
    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node node) {
        nodes.add(node);
    }

    public static Graph dijkstraSearch(Graph graph, Node node) {
        node.setDistance(0);

        Set<Node> selectNode = new HashSet<>();
        Set<Node> unSelectNode = new HashSet<>();

        unSelectNode.add(node);

        while (unSelectNode.size() != 0) {
            Node currentNode = getLowDistanceNode(unSelectNode);
            currentNode.getAdjacentNodes()
                    .forEach((key, weight) -> {
                        if (!selectNode.contains(key)) {
                            compareActualDistWithNewDistance(key,weight,currentNode);
                            unSelectNode.add(key);
                        }
                    });
            selectNode.add(currentNode);
            unSelectNode.remove(currentNode);
        }

        return graph;
    }

    public static Node getLowDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;            
        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    public static void compareActualDistWithNewDistance(Node calcNode, Integer weight, Node sourceNode) {
        if (sourceNode.getDistance() + weight < calcNode.getDistance()) {
            calcNode.setDistance(sourceNode.getDistance() + weight);
            List<Node> shortPath = new LinkedList<>(sourceNode.getShortPath());
            shortPath.add(sourceNode);
            calcNode.setShortPath(shortPath);
        }
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + nodes +
                '}';
    }

    public Set<Node> getNodes() {
        return nodes;
    }
}

class Node {
    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", shortPath=" + shortPath +
                ", distance=" + distance +
                '}';
    }

    private String name;
    private List<Node> shortPath = new LinkedList<>();
    private int distance = Integer.MAX_VALUE;
    Map<Node, Integer> adjacentNodes = new HashMap<>();

    public Node(String name) {
        this.name = name;
    }

    public void addWay(Node nextNode, int distance) {
        adjacentNodes.put(nextNode, distance);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getShortPath() {
        return shortPath;
    }

    public void setShortPath(List<Node> shortPath) {
        this.shortPath = shortPath;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }
}
