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

public class GraphManager {
    MutableGraph g;
    public void parseGraph(String filename) throws Exception
    {
      try {
          //InputStream dot = getClass().getResourceAsStream(filename);
          FileInputStream input
                  = new FileInputStream(filename);
           g = new Parser().read(input);

          System.out.println("the number of nodes in the graph is  " + g.nodes().size());

          System.out.println("the number of edges in the graph is " + g.edges().size());

          for (MutableNode node : g.nodes()) {
              System.out.println("The name of the node is " + node.name());

          }
          for (Link edge : g.edges()) {
              System.out.println("the edge is from " + edge.from().name() + " -> " + edge.to().name());
          }

         // System.out.println(g.toString());
      }catch (Exception e)
      {
          throw e;
      }
    }

   @Override public String toString()
    {
     return g.toString();
    }

    public void outputGraph(String filepath) throws IOException {
        try {
            FileInputStream input
                    = new FileInputStream(filepath);
            Graphviz.fromGraph(g).height(100).render(Format.PNG).toFile(new File("example/ex1.png"));
        } catch (Exception e) {
            throw e;
        }
    }
    public void addEdge(String source, String target) {

    }

    public void addNode(String node) {
    }

    public void removeNode(String node) {
    }

    public void removeEdge(String source, String target) {
    }
}
