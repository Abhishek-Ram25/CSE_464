package aramak21;

import aramak21.graphSearch.GraphSearcher;
import aramak21.graphSearch.strategy.GraphSearcherStrategy;
import aramak21.graphSearch.strategy.factory.GraphSearcherStrategyFactory;
import aramak21.graphTraverse.BFSGraphSearchImpl;
import aramak21.graphTraverse.DFSGraphSearchImpl;
import aramak21.graphTraverse.GraphTraverseAlgorithm;
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
        printInfo();
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

    private void printInfo() {
        System.out.println("The number of nodes in the graphs is " + graph.nodes.size());
        System.out.println("The number of edges in the graph is " + graph.edges.size());
        System.out.println("The label is " + graph.label);
        System.out.println("The nodes are ");
    }


    public void addNode(String node_name) {
        for(Node node : graph.nodes)
        {
            if(node.getName().equals(node_name))
            {
                System.out.println("\n the node " + node_name + " already exists"); return;
            }
        }
        graph.nodes.add(new Node(node_name));
        displayUponAdd(node_name);

    }

    private void displayUponAdd(String node_name) {
        System.out.println("\nThe number of nodes after adding " + node_name + " is " + graph.nodes.size());
        System.out.println("\nthe nodes after add operation ");
        for(Node node : graph.nodes)
            System.out.print(" " + node.getName() + " ");
    }

    public void addNodes(String[] nodes) {
        for (String node:nodes) {
            addNode(node);
        }
    }
    public void addEdge(String source, String target) {
  boolean isSource = false;
  boolean isTarget = false;
  Node node_source = null;
  Node node_target= null;
    for(Node node : graph.nodes)
    {
        if(node.getName().equals(source))
        {
            isSource=true;
            node_source = node;
        }

        if(node.getName().equals(target))
        {
            isTarget=true;
            node_target= node ;

        }
    }
    if(!isSource)
    {
        node_source = new Node(source);
        graph.nodes.add(node_source);
    }
    if(!isTarget)
    {
        node_target= new Node(target);
        graph.nodes.add(node_target);
    }

    for(Edge edge : graph.edges)
    {
        if(edge.getSource().getName().equals(source) && edge.getTarget().getName().equals(target))
        {
            System.out.println("\n the edge with source "+ source + "  and target " + target + " already exists");
            return;
        }
    }
    graph.edges.add(new Edge(node_source,node_target));

        System.out.println("\nThe edges are from : ");
        for(Edge edge : graph.edges)
            System.out.println( edge.getSource().getName() + " -> " + edge.getTarget().getName());

    }
    public void removeNode(String node_name) {
       ArrayList<Node> nodes1 = new ArrayList<>();
       ArrayList<Edge> edges1 = new ArrayList<>();
        for(Node node : graph.nodes)
        {
            if(node.getName().equals(node_name))
            {
                 continue;
            }
            nodes1.add(node);

        }
        System.out.println(" \nthe size of list after removing the node is " + nodes1.size());
        graph.nodes = nodes1;
        System.out.println("\nthe nodes after the remove operation ");
        for(Node node : graph.nodes)
            System.out.print(" " + node.getName() + " ");

        for(Edge edge : graph.edges)
        {
            if(edge.getSource().getName().equals(node_name) || edge.getTarget().getName().equals(node_name))
                continue;
            edges1.add(new Edge(edge.getSource(),edge.getTarget()));
        }
        graph.edges = edges1;

    }

    public void removeNodes(String[] nodes) {
        for (String node:nodes) {
            removeNode(node);
        }
    }

    public void removeEdge(String source, String target) {
        ArrayList<Edge> edges1 = new ArrayList<>();
        for(Edge edge : graph.edges)
        {
            if(edge.getSource().getName().equals(source) && edge.getTarget().getName().equals(target))
            {
                continue;
            }
            edges1.add( new Edge(edge.getSource(),edge.getTarget()));
        }
        graph.edges= edges1 ;

        System.out.println("\nthe number of edges after the delete operation is "+ graph.edges.size());
        System.out.println("The edges are from : ");
        for(Edge edge : graph.edges)
            System.out.println( edge.getSource().getName() + " -> " + edge.getTarget().getName());
    }



    public MutableGraph graphToMutableGraph()
    {
        MutableGraph mutableGraph = mutGraph(graph.label).setDirected(true);

        for(Node node : graph.nodes)
        {
            mutableGraph.add(mutNode(node.name));
        }

        for(Edge edge : graph.edges)
        {
            mutableGraph.add(mutNode(edge.getSource().getName()).addLink(mutNode(edge.getTarget().getName())));
        }

        return mutableGraph;
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
    {   BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
        bw.write("The number of Nodes is " + graph.nodes.size());
        bw.write("\nThe number of edges is " + graph.edges.size());
        bw.write("\nThe nodes are as follows");
        for(Node n: graph.nodes)
            bw.write(" " + n.getName());
        bw.write("\nthe edges are as follows " + graphToMutableGraph());
        bw.close();
    }


    public int nodeSize() {
        return graph.nodes.size();
    }

    public int edgeSize() {
        return  graph.edges.size();
    }

    public boolean containsEdge(String a, String b) {
        boolean hasEdge = false;
        for(Edge edge : graph.edges) {
            if (edge.getSource().getName().equals(a) && edge.getTarget().getName().equals(b))
                hasEdge = true;
        }

        return hasEdge;
    }

    public boolean containsNode(String e) {
        boolean hasNode = false;
        for(Node node : graph.nodes)
        {
            if(node.getName().equals(e))
            {
             hasNode = true;
            }
        }
        return hasNode;
    }

    public Path GraphSearch(Node src, Node dst, Algorithm algo) {
        printSearchInformation(src, dst, algo);
        GraphSearcherStrategy strategy = GraphSearcherStrategyFactory.getStrategy(algo);
        GraphSearcher graphSearcher = new GraphSearcher(strategy);
        Path path = graphSearcher.search(graph, src, dst);
        printPath(path);
        return path;
    }

    private static void printSearchInformation(Node src, Node dst, Algorithm algo) {
        System.out.printf("Searching path from %s to %s by using algorithm: %s%n",
                src.getName(), dst.getName(), algo.name());
    }

    private static void printPath(Path path) {
        if (path != null){
            System.out.printf("The path : %s%n", path.toString());
        }else{
            System.out.println("Sorry. No valid path was found!!!");
        }
    }

}
