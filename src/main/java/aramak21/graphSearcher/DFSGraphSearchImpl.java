package aramak21.graphSearcher;
import aramak21.graphSearcher.GraphSearcher;
import aramak21.Graph;
import aramak21.Node;
import aramak21.Path;


import java.util.*;
import java.util.stream.Collectors;

public class DFSGraphSearchImpl extends GraphSearcher{
    private Stack<String> stack;

    public DFSGraphSearchImpl (Graph graph, Node src, Node dst) {
        super(graph, src, dst);
    }

    @Override
    protected void setupNextNode(String currentNode, String nextNode) {
        if(!visited.contains(nextNode)){
            stack.push(nextNode);
            childToParentNodeMap.put(nextNode, currentNode);
        }
    }

    @Override
    protected List<Node> getPossibleNextNodes(String currentNode) {
        List<Node> possibleDestinations = edgeMap.getOrDefault(currentNode, new LinkedList<>());
        possibleDestinations = possibleDestinations.stream()
                .sorted(Comparator.comparing(Node::getName))
                .collect(Collectors.toList());
        Collections.reverse(possibleDestinations);
        return possibleDestinations;
    }

    @Override
    protected String getNext() {
        return stack.pop();
    }

    @Override
    protected boolean isNext() {
        return !stack.isEmpty();
    }

    @Override
    protected void setupSearch() {
        childToParentNodeMap = new HashMap<>();
        childToParentNodeMap.put(src.getName(), null);

        edgeMap = getEdgeAsMap();
        visited = new HashSet<>();
        stack = new Stack<>();
        stack.push(src.getName());
    }

}
