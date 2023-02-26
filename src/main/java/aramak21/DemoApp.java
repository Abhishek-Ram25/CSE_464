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

        gm.parseGraph("testInputGraph.dot");


//        gm.outputGraph("testInputGraph.dot");
//
        gm.addNode("e");

       gm.addNodes(new String[]{"e", "f", "g"});

       gm.removeNode("a");

       gm.removeNodes(new String[]{ "e","f","g"});

       gm.toString();

        gm.addEdge("a","e");
        gm.addEdge("f","g");

        gm.toString();

        gm.removeEdge("a","e");

        gm.toString();

    }


}
