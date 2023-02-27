package aramak21;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

import java.io.File;
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
        GraphManager gm = new GraphManager();
        System.out.println(" Enter the dot file to parse the graph:");
        String dotFileName =  new Scanner(System.in).nextLine();
        gm.parseGraph(dotFileName);

        int choice=0;
        while(choice!=11)
        {
            System.out.println(" \nSelect an option");
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
                                   "11. Quit Program" );

            choice =  new Scanner(System.in).nextInt();

            switch(choice)
            {
                case 1: gm.toString(); break;
                case 2 : System.out.println("Enter the filename to wish you want the info to be copied to ");
                         String filename = new Scanner(System.in).nextLine();
                         gm.outputGraph(filename);
                         break;

                case 3: System.out.println("Enter the node to be inserted");
                        String nodeName = new Scanner(System.in).nextLine();
                        gm.addNode(nodeName);
                        break;

                case 4: System.out.println("Enter a list of nodes to be inserted with a [space] separating them ");
                        String nodes = new Scanner(System.in).nextLine();
                        gm.addNodes(nodes.split(" "));
                        break;

                case 5: System.out.println(" Enter the node to be removed");
                        String rem_node= new Scanner(System.in).nextLine();
                        gm.removeNode(rem_node);
                        break;

                case 6: System.out.println("Enter a list of nodes to be deleted with a [space] separating them ");
                        String rem_nodes = new Scanner(System.in).nextLine();
                        gm.removeNodes(rem_nodes.split(" "));
                        break;

                case 7 : System.out.println("Enter the source node");
                         String addSource = new Scanner(System.in).nextLine();
                         System.out.println("Enter the target");
                         String addTarget = new Scanner(System.in).nextLine();
                         gm.addEdge(addSource,addTarget);
                         break;

                case 8 :System.out.println("Enter the source node");
                        String removeSource = new Scanner(System.in).nextLine();
                        System.out.println("Enter the target");
                        String removeTarget = new Scanner(System.in).nextLine();
                        gm.removeEdge(removeSource,removeTarget);
                        break;

                case 9 : System.out.println("Enter the file name for the dot Graph");
                         String dotFile = new Scanner(System.in).nextLine();
                         gm.outputDOTGraph(dotFile);
                         break;

                case 10 : System.out.println("Enter the desired format - SVG or PNG");
                          String fileType = new Scanner(System.in).nextLine();
                          System.out.println("Enter the desired file name");
                          String fileName = new Scanner(System.in).nextLine();
                          gm.outputGraphics(fileName,fileType);
                          break;

                case 11 : System.out.println("exiting now ");
                          break;

            }
        }
//        //gm.parseGraph("testInputGraph.dot");
//
//        gm.addNode("e");
//
//       gm.addNodes(new String[]{"e", "f", "g"});
//
//       gm.removeNode("a");
//
//       gm.removeNodes(new String[]{ "e","f","g"});
//
//        gm.addEdge("a","e");
//        gm.addEdge("a","c");
//        gm.addEdge("a","f");
//        gm.addEdge("f","g");
//
//        gm.removeEdge("a","e");
//
//        gm.toString();
//
//        gm.outputDOTGraph("outputDOTGraphTest");
//        gm.outputDOTGraph("checkDOTgraph");
//
//        gm.outputGraphics("opGraphicsTest","png");
//        gm.outputGraphics("opGraphicsTest1a","SVG");
//
//        gm.outputGraph("outputTestGraph");

    }


}
