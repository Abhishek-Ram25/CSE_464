package aramak21.graphTraverse;
import aramak21.Edge;
import aramak21.Graph;
import aramak21.Node;
import aramak21.Path;
import java.util.*;

public abstract class GraphTraverseAlgorithm {

    protected final Graph graph;

    protected final Node src;

    protected final Node dst;

    protected Map<String, List<Node>> edgeMap;

    protected Map<String, String> childToParentNodeMap;

    protected Set<String> visited;

    public GraphTraverseAlgorithm(Graph graph, Node src, Node dst) {
        this.graph = graph;
        this.src = src;
        this.dst = dst;
    }
    public final Path search(){
        Path path = null;
        if(validateInput(src, dst)){
            setupSearch();
            boolean destinationFound = false;
            while(!destinationFound && isNext()){
                String currentNodeName = getNext();
                destinationFound = processCurrentNode(currentNodeName);
            }
            path = generatePath();
        }
        return path;
    }

    private boolean processCurrentNode(String currentNode){
        if (!visited.contains(currentNode)){
            visited.add(currentNode);
            if (currentNode.equals(dst.getName())){
                return true;
            }else{
                List<Node> possibleDestinations = getPossibleNextNodes(currentNode);
                for (Node eachDst : possibleDestinations) {
                    setupNextNode(currentNode, eachDst.getName());
                }
            }
        }
        return false;
    }

    protected abstract void setupNextNode(String currentNode, String nextNode);

    protected abstract List<Node> getPossibleNextNodes(String currentNode);

    protected abstract String getNext();

    protected abstract boolean isNext();

    protected abstract void setupSearch();

    private boolean validateInput(Node src, Node dst) {
        boolean isSource = false;
        boolean isTarget = false;
        for(Node node : graph.nodes)
        {
            if(node.getName().equals(src.getName()))
            {
                isSource=true;
            }

            if(node.getName().equals(dst.getName()))
            {
                isTarget=true;
            }
        }
        return isSource && isTarget;
    }

    protected Map<String, List<Node>> getEdgeAsMap() {
        Map<String, List<Node>> edgeMap = new HashMap<>();
        if (!graph.edges.isEmpty()){
            for (Edge eachEdge : graph.edges) {
                List<Node> list = edgeMap.getOrDefault(eachEdge.getSource().getName(), new LinkedList<>());
                list.add(eachEdge.getTarget());
                edgeMap.put(eachEdge.getSource().getName(), list);
            }
        }
        return edgeMap;
    }

    protected Path generatePath(){
        Path path = null;
        if (childToParentNodeMap.containsKey(dst.getName())){
            path = new Path();
            path.addNodeInTheFront(dst);
            String parent = childToParentNodeMap.get(dst.getName());
            while (parent != null){
                path.addNodeInTheFront(new Node(parent));
                parent = childToParentNodeMap.get(parent);
            }
        }
        return path;
    }



}
