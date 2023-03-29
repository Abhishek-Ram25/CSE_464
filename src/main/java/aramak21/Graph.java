package aramak21;

import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;

import java.util.*;

public class Graph {
    public String label;

    public ArrayList<Node> nodes;

    public ArrayList<Edge> edges;

    public Graph(MutableGraph graph)
    {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();

        label=graph.name().toString();

        for (MutableNode n:graph.nodes())
        {
        nodes.add(new Node(n.name().toString()));
        }

        for(Link l: graph.edges()) {
            Node source = null;
            Node target = null;

            for (Node n : nodes)
            {
                if(n.getName() == l.from().name().toString())
                    source= n;
                if(n.getName() == l.to().name().toString())
                    target= n;
            }
            edges.add(new Edge(source,target));

        }

    }

    public Path findPathUsingBFS(Node src, Node dst) {
        Map<String, String> parentMap = new HashMap<>();
        parentMap.put(src.getName(), null);
        Map<String, List<Node>> edgeMap = getEdgeAsMap();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(src.getName());
        while (!queue.isEmpty()){
            String currentNode = queue.poll();
            if (!visited.contains(currentNode)){
                visited.add(currentNode);

                if (currentNode.equals(dst.getName())){
                    break;
                }

                List<Node> possibleDestinations = edgeMap.getOrDefault(currentNode, new LinkedList<>());
                for (Node eachDst : possibleDestinations) {
                    queue.add(eachDst.getName());
                    if (!parentMap.containsKey(eachDst.getName())){
                        parentMap.put(eachDst.getName(), currentNode);
                    }
                }
            }
        }

        return generatePath(parentMap, dst);
    }

    private Path generatePath(Map<String, String> childToParentMap, Node dst){
        Path path = null;
        if (childToParentMap.containsKey(dst.getName())){
            path = new Path();
            path.addNodeInTheFront(dst);
            String parent = childToParentMap.get(dst.getName());
            while (parent != null){
                path.addNodeInTheFront(new Node(parent));
                parent = childToParentMap.get(parent);
            }
        }
        return path;
    }

    private Map<String, List<Node>> getEdgeAsMap() {
        Map<String, List<Node>> edgeMap = new HashMap<>();
        if (!edges.isEmpty()){
            for (Edge eachEdge : edges) {
                List<Node> list = edgeMap.getOrDefault(eachEdge.getSource().getName(), new LinkedList<>());
                list.add(eachEdge.getTarget());
                edgeMap.put(eachEdge.getSource().getName(), list);
            }
        }
        return edgeMap;
    }
}

