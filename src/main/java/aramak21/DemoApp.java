package aramak21;

import com.paypal.digraph.parser.GraphEdge;
import com.paypal.digraph.parser.GraphNode;
import com.paypal.digraph.parser.GraphParser;
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

        System.out.println("The code for the graph is " + gm.toString());

        gm.outputGraph("testInputGraph.dot");

        gm.addNode("e");

        gm.addEdge("e","c");

        gm.removeNode("e");

        gm.removeEdge("a","c");

        //

//        Graph g = graph("example1").directed()
//                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
//                .linkAttr().with("class", "link-class")
//                .with(
//                        node("a").with(Color.RED).link(node("b")),
//                        node("b").link(
//                                to(node("c")).with(attr("weight", 5), Style.DASHED)
//                        )
//                );
//        Graphviz.fromGraph(g).height(100).render(Format.PNG).toFile(new File("example/ex1.png"));


    }


}
