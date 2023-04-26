package aramak21.graphTraverse;

import aramak21.Graph;
import aramak21.Node;
import java.util.*;

public class RandomWalkSearchImpl extends GraphTraverseAlgorithm {
    private List<String> nextNodes;

    public RandomWalkSearchImpl(Graph graph, Node src, Node dst) {
        super(graph, src, dst);
    }

    @Override
    protected void setupNextNode(String currentNode, String nextNode) {
        nextNodes.add(nextNode);
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
        Random random = new Random();
        int randomIndex = random.nextInt(nextNodes.size());
        String next = nextNodes.get(randomIndex);
        nextNodes.remove(next);
        return next;
    }

    @Override
    protected boolean isNext() {
        return !nextNodes.isEmpty();
    }

    @Override
    protected void setupSearch() {
        super.setupSearch();
        nextNodes = new LinkedList<>();
        nextNodes.add(src.getName());
    }

}
