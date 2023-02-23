package aramak21;

import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GraphManager {
    public void parseGraph(String filename) throws Exception
    {
      try {
          //InputStream dot = getClass().getResourceAsStream(filename);
          FileInputStream input
                  = new FileInputStream(filename);
          MutableGraph g = new Parser().read(input);

          System.out.println("The graph " + g);
      }catch (Exception e)
      {
          throw e;
      }
    }
}
