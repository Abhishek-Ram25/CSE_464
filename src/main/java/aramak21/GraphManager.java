package aramak21;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import guru.nidi.graphviz.parse.Parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLOutput;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;

public class GraphManager {

    Graph graph;
    public void parseGraph(String filename) throws IOException
    {
             //InputStream dot = getClass().getResourceAsStream(filename);
          FileInputStream input
                  = new FileInputStream(filename);
           MutableGraph   g = new Parser().read(input);
           graph = new Graph(g);
           toString();

     }

   @Override public String toString()

    {
        String output;
     System.out.println("The number of nodes in the graphs is " + graph.nodes.size());
     System.out.println("The number of edges in the graph is " + graph.edges.size());
     System.out.println("The label is " + graph.label);

     for(Node node : graph.nodes)
         System.out.println("The node is " + node.getName());

     for(Edge edge : graph.edges)
         System.out.println("The edge is from " + edge.getSource().getName() + " -> " + edge.getTarget().getName());

     output = graphToMutableGraph().toString();
    System.out.println("op file is " + output);
     return output;
    }


    public void addEdge(String source, String target) {

    }

    public void addNode(String node) {

    }
    public void addNodes(String[] node) {
        for (String n:node) {
            addNode(n);
        }
        //logic to handle duplicate node
    }

    public void removeNode(String node) {
       // System.out.println( g.toString());
    }

    public void removeEdge(String source, String target) {

    }

    public MutableGraph graphToMutableGraph()
    {
        MutableGraph g = mutGraph(graph.label).setDirected(true);

        for(Node node : graph.nodes)
        {
            g.add(mutNode(node.name));
        }

        for(Edge edge : graph.edges)
        {
            g.add(mutNode(edge.getSource().getName()).addLink(mutNode(edge.getTarget().getName())));
        }

        return g;
    }
}
