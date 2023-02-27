package aramak21;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

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
     System.out.println("The nodes are ");
     for(Node node : graph.nodes)
         System.out.print(" " + node.getName() + " ");
     System.out.println("\n");
     System.out.println("The edges are from : ");
     for(Edge edge : graph.edges)
         System.out.println( edge.getSource().getName() + " -> " + edge.getTarget().getName());

     output = graphToMutableGraph().toString();
    System.out.println(" \n op file is " + output);
     return output;
    }




    public void addNode(String node_name) {
        for(Node n : graph.nodes)
        {
            if(n.getName().equals(node_name))
            {
                System.out.println("\n the node " + node_name + " already exists"); return;
            }
        }
        graph.nodes.add(new Node(node_name));
        System.out.println("\nThe number of nodes after adding " + node_name+ " is " + graph.nodes.size());
        System.out.println("\nthe nodes after add operation ");
        for(Node node : graph.nodes)
            System.out.print(" " + node.getName() + " ");

    }
    public void addNodes(String[] node) {
        for (String n:node) {
            addNode(n);
        }
    }
    public void addEdge(String source, String target) {
  boolean isSource = false;
  boolean isTarget = false;
  Node s = null;
  Node t= null;
    for(Node n : graph.nodes)
    {
        if(n.getName().equals(source))
        {
            isSource=true;
            s = n;
        }

        if(n.getName().equals(target))
        {
            isTarget=true;
            t= n ;

        }
    }
    if(!isSource)
    {
        s = new Node(source);
        graph.nodes.add(s);
    }
    if(!isTarget)
    {
        t= new Node(target);
        graph.nodes.add(t);
    }

    for(Edge e : graph.edges)
    {
        if(e.getSource().getName().equals(source) && e.getTarget().getName().equals(target))
        {
            System.out.println("\n the edge with source "+ source + "and target " + target + " already exists");
            return;
        }
    }
    graph.edges.add(new Edge(s,t));

        System.out.println("\nThe edges are from : ");
        for(Edge edge : graph.edges)
            System.out.println( edge.getSource().getName() + " -> " + edge.getTarget().getName());

    }
    public void removeNode(String node_name) {
       ArrayList<Node> nodes1 = new ArrayList<>();
       ArrayList<Edge> edges1 = new ArrayList<>();
        for(Node n : graph.nodes)
        {
            if(n.getName().equals(node_name))
            {
                 continue;
            }
            nodes1.add(n);

        }
        System.out.println(" \nthe size of list after removing the node is " + nodes1.size());
        graph.nodes = nodes1;
        System.out.println("\nthe nodes after the remove operation ");
        for(Node node : graph.nodes)
            System.out.print(" " + node.getName() + " ");

        for(Edge e : graph.edges)
        {
            if(e.getSource().getName().equals(node_name) || e.getTarget().getName().equals(node_name))
                continue;
            edges1.add(new Edge(e.getSource(),e.getTarget()));
        }
        graph.edges = edges1;

    }

    public void removeNodes(String[] node) {
        for (String n:node) {
            removeNode(n);
        }
    }

    public void removeEdge(String source, String target) {
        ArrayList<Edge> edges1 = new ArrayList<>();
        for(Edge e : graph.edges)
        {
            if(e.getSource().getName().equals(source) && e.getTarget().getName().equals(target))
            {
                continue;
            }
            edges1.add( new Edge(e.getSource(),e.getTarget()));
        }
        graph.edges= edges1 ;

        System.out.println("\nthe number of edges after the delete operation is "+ graph.edges.size());
        System.out.println("The edges are from : ");
        for(Edge edge : graph.edges)
            System.out.println( edge.getSource().getName() + " -> " + edge.getTarget().getName());
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

    public void outputDOTGraph(String path) throws IOException
    {
        MutableGraph mutableGraph = graphToMutableGraph();
        Graphviz.fromGraph(mutableGraph).width(800).render(Format.DOT).toFile(new File(path));
    }

    public void outputGraphics(String path, String format) throws IOException
    {
        MutableGraph mutableGraph = graphToMutableGraph();

        if(Objects.equals(format,"png")||Objects.equals(format,"PNG"))
            Graphviz.fromGraph(mutableGraph).width(900).render(Format.PNG).toFile(new File(path));

        if(Objects.equals(format,"svg")||Objects.equals(format,"SVG"))
            Graphviz.fromGraph(mutableGraph).width(900).render(Format.SVG).toFile(new File(path));
    }

    public void outputGraph(String filepath) throws IOException
    {

        BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
        bw.write("\nThe number of Nodes is " + graph.nodes.size());
        bw.write("\nThe number of edges is " + graph.edges.size());
        bw.write(" \n The nodes are as follows " );
        for(Node n: graph.nodes)
            bw.write(n.getName() + " " );
        bw.write("\n the edges are as follows " + graphToMutableGraph());
        bw.close();
    }


}
