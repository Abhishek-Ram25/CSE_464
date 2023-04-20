package aramak21.graphSearcher;
import aramak21.Graph;
import aramak21.Node;


import java.util.*;

public class BFSGraphSearchImpl extends GraphSearcher{

    private Queue<String> queue;

    public BFSGraphSearchImpl(Graph graph, Node src, Node dst) {
        super(graph, src, dst);
    }

    @Override
    protected void setupNextNode(String currentNode, String nextNode) {
        queue.add(nextNode);
        if (!childToParentNodeMap.containsKey(nextNode)){
            childToParentNodeMap.put(nextNode, currentNode);
        }
    }

    @Override
    protected List<Node> getPossibleNextNodes(String currentNode) {
        return edgeMap.getOrDefault(currentNode, new LinkedList<>());
    }

    @Override
    protected String getNext() {
        return queue.poll();
    }

    @Override
    protected boolean isNext() {
        return !queue.isEmpty();
    }

    @Override
    protected void setupSearch() {
        childToParentNodeMap = new HashMap<>();
        childToParentNodeMap.put(src.getName(), null);
        edgeMap = getEdgeAsMap();
        visited = new HashSet<>();
        queue = new LinkedList<>();
        queue.add(src.getName());
    }

}
