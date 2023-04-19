package aramak21;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Link.to;

public class DemoApp {

    public static void main(String[] args) throws Exception {

        System.out.println("Software QA and Testing Part-1");


        //parsing graph
        GraphManager graphManager = new GraphManager();
        System.out.println(" Enter the dot file to parse the graph:");
        String dotFileName =  new Scanner(System.in).nextLine();
        graphManager.parseGraph(dotFileName);

        int choice=0;
        while(choice!=13)
        {
            System.out.println(" \nSelect an option");
            showOptions();

            choice =  new Scanner(System.in).nextInt();

            switch(choice)
            {
                case 1: graphManager.toString(); break;
                case 2 : System.out.println("Enter the filename to which you want the info to be copied to ");
                         String filename = new Scanner(System.in).nextLine();
                         graphManager.outputGraph(filename);
                         break;

                case 3:
                    performAddNode(graphManager);
                    break;

                case 4:
                    performAddListOfNodes(graphManager);
                    break;

                case 5:
                    performRemoveNode(graphManager);
                    break;

                case 6:
                    performRemoveListOfNodes(graphManager);
                    break;

                case 7 :
                    performAddEdge(graphManager);
                    break;

                case 8 :
                    performRemoveEdge(graphManager);
                    break;

                case 9 :
                    performOutputAsDotFile(graphManager);
                    break;

                case 10 :
                    performOutputIntoGraphics(graphManager);
                    break;

                case 11 : graphSearch(graphManager,Algorithm.bfs);
                          break;

                case 12 : graphSearch(graphManager,Algorithm.dfs);
                          break;

                case 13:
                    System.out.println("Exiting the application");
                    return;
            }
        }

    }

    private static void performOutputIntoGraphics(GraphManager graphManager) throws IOException {
        System.out.println("Enter the desired format - SVG or PNG");
        String fileType = new Scanner(System.in).nextLine();
        System.out.println("Enter the desired file name");
        String fileName = new Scanner(System.in).nextLine();
        graphManager.outputGraphics(fileName,fileType);
    }

    private static void performOutputAsDotFile(GraphManager graphManager) throws IOException {
        System.out.println("Enter the file name for the dot Graph");
        String dotFile = new Scanner(System.in).nextLine();
        graphManager.outputDOTGraph(dotFile);
    }

    private static void performRemoveEdge(GraphManager graphManager) {
        System.out.println("Enter the source node");
        String removeSource = new Scanner(System.in).nextLine();
        System.out.println("Enter the target");
        String removeTarget = new Scanner(System.in).nextLine();
        graphManager.removeEdge(removeSource,removeTarget);
    }

    private static void performAddEdge(GraphManager graphManager) {
        System.out.println("Enter the source node");
        String addSource = new Scanner(System.in).nextLine();
        System.out.println("Enter the target");
        String addTarget = new Scanner(System.in).nextLine();
        graphManager.addEdge(addSource,addTarget);
    }

    private static void performRemoveListOfNodes(GraphManager graphManager) {
        System.out.println("Enter a list of nodes to be deleted with a [space] separating them ");
        String rem_nodes = new Scanner(System.in).nextLine();
        graphManager.removeNodes(rem_nodes.split(" "));
    }

    private static void performRemoveNode(GraphManager graphManager) {
        System.out.println(" Enter the node to be removed");
        String rem_node= new Scanner(System.in).nextLine();
        graphManager.removeNode(rem_node);
    }

    private static void performAddListOfNodes(GraphManager graphManager) {
        System.out.println("Enter a list of nodes to be inserted with a [space] separating them ");
        String nodes = new Scanner(System.in).nextLine();
        graphManager.addNodes(nodes.split(" "));
    }

    private static void performAddNode(GraphManager graphManager) {
        System.out.println("Enter the node to be inserted");
        String nodeName = new Scanner(System.in).nextLine();
        graphManager.addNode(nodeName);
    }

    private static void showOptions() {
        System.out.println("1.  Print info about the graph\n"
                            + "2.Write the information of the graph into a new file\n"
                            + "3. Insert a new node\n"
                            + "4. Insert many nodes into the graph\n"
                            + "5. Remove a node from the graph\n"
                            +  "6.Remove a number of nodes from the graph\n"
                            +  "7. Add an edge between two nodes\n"
                            +  "8. Remove an edge between two nodes\n"
                            +  "9.Return the graph info in a DOT file\n" +
                               "10.Return the graph as an SVG or PNG\n" +
                               "11. Perform bfs\n"+
                                "12. Perform dfs\n"
                            + "13.Exit");
    }

    public static void graphSearch(GraphManager graphManager,Algorithm algo)
    {
        System.out.println("Enter the source node");
        String src = new Scanner(System.in).nextLine();
        System.out.println("Enter the destination node");
        String dst = new Scanner(System.in).nextLine();
        Path path = graphManager.GraphSearch(new Node(src),new Node(dst),algo);
        System.out.println("the path is: \n" + path.toString());
    }

}
