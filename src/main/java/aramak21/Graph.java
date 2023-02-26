package aramak21;

import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
}

