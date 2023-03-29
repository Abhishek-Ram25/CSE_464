package aramak21;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class GraphManagerTest {
    GraphManager g;

    @Before
    public void setup() throws Exception {
        g = new GraphManager();
        g.parseGraph("testInputGraph.dot");
    }

    @Test
    public void testParseGraph() {
        Assert.assertEquals(4, g.nodeSize());
        Assert.assertEquals(4, g.edgeSize());
        Assert.assertTrue(g.containsEdge("a", "b"));
        Assert.assertTrue(g.containsEdge("b", "c"));
        Assert.assertTrue(g.containsEdge("c", "d"));
        Assert.assertTrue(g.containsEdge("d", "a"));
        System.out.println(g.toString());
    }

    @Test
    public void testAddNode() throws Exception {
        Assert.assertEquals(4, g.nodeSize());
        g.addNode("e");
        System.out.println(g.toString());

        Assert.assertEquals(5, g.nodeSize());
        Assert.assertTrue(g.containsNode("e"));

       g.addNode("a");
        Assert.assertEquals(5, g.nodeSize());
        Assert.assertTrue(g.containsNode("a"));

    }

    @Test
    public void testAddNodes() throws Exception{
        Assert.assertEquals(4, g.nodeSize());
        g.addNodes(new String[]{"e", "f", "g"});
        Assert.assertEquals(7, g.nodeSize());

        g.addNodes(new String[]{"a", "b", "c"});
        Assert.assertEquals(7, g.nodeSize());
    }

    @Test
    public void testAddEdge() throws Exception {
        Assert.assertEquals(4, g.edgeSize());
        g.addEdge("e", "a");
       // System.out.println(g.toString());
        Assert.assertEquals(5, g.edgeSize());
        Assert.assertEquals(5, g.nodeSize());
        Assert.assertTrue(g.containsNode("e"));
        Assert.assertTrue(g.containsEdge("e", "a"));

        g.addEdge("a","b");
        Assert.assertEquals(5, g.edgeSize());
        Assert.assertEquals(5, g.nodeSize());
        Assert.assertTrue(g.containsEdge("a", "b"));
    }

    @Test
    public void testRemoveNode() throws Exception {
        Assert.assertEquals(4, g.nodeSize());
        g.removeNode("a");

        Assert.assertEquals(3, g.nodeSize());
        Assert.assertFalse(g.containsNode("a"));

        g.removeNode("f");
        Assert.assertEquals(3,g.nodeSize());
    }


    @Test
    public void testRemoveNodes() throws Exception{
        Assert.assertEquals(4, g.nodeSize());
        g.removeNodes(new String[]{"e", "f", "g"});
        Assert.assertEquals(4, g.nodeSize());

        g.removeNodes(new String[]{"a", "b", "c"});
        Assert.assertEquals(1, g.nodeSize());

    }

    @Test
    public void testRemoveEdge() {
        Assert.assertEquals(4, g.edgeSize());
        g.removeEdge("b", "c");
       // System.out.println(g.toString());

        Assert.assertEquals(4, g.nodeSize());
        Assert.assertEquals(3, g.edgeSize());

        Assert.assertEquals(3, g.edgeSize());
        g.removeEdge("a","c");
        Assert.assertEquals(4, g.nodeSize());
        Assert.assertEquals(3, g.edgeSize());
    }

    @Test
    public void testOutputDOTGraph() throws IOException {
        g.addEdge("e", "f");
        String outputfile = "output2.dot";
        g.outputDOTGraph(outputfile);

        List<String> output = Files.readAllLines(Paths.get(outputfile));
        System.out.println("output: " + output);
        List<String> expected = Files.readAllLines(Paths.get("expected.txt"));
        Assert.assertEquals(expected, output);
    }

    @Test
    public void testOutputGraph() throws IOException
    {
        String output_graph_name = "testOPGraph.txt";
        g.outputGraph(output_graph_name);
        List<String> output = Files.readAllLines(Paths.get(output_graph_name));
        System.out.println("output: " + output_graph_name);
        List<String> expected = Files.readAllLines(Paths.get("expectedOutputGraph.txt"));
        Assert.assertEquals(expected,output);

    }

    @Test
    public void testDFSGraphSearch() {
        Path path = g.GraphSearch(new Node("a"), new Node("d"), Algorithm.dfs );
        Assert.assertEquals("a -> b -> c -> d", path.toString());

        path = g.GraphSearch(new Node("a"), new Node("a") , Algorithm.dfs);
        Assert.assertEquals("a", path.toString());


        path = g.GraphSearch(new Node("b"), new Node("a") , Algorithm.dfs);
        Assert.assertEquals("b -> c -> d -> a", path.toString());

        g.addNodes(new String[]{"e","f","g", "h", "i"});
        g.addEdge("d","e");
        g.addEdge("d","f");
        g.addEdge("d","g");
        g.addEdge("d","h");
        g.addEdge("g","h");
        g.addEdge("h","i");

        path = g.GraphSearch(new Node("a"), new Node("i") , Algorithm.dfs);
        Assert.assertEquals("a -> b -> c -> d -> g -> h -> i", path.toString());
    }

    @Test
    public void testBFS(){
        {
            Path path = g.GraphSearch(new Node("a"), new Node("d"), Algorithm.bfs);
            Assert.assertEquals("a -> b -> c -> d", path.toString());

            path = g.GraphSearch(new Node("a"), new Node("a"), Algorithm.bfs);
            Assert.assertEquals("a", path.toString());


            path = g.GraphSearch(new Node("b"), new Node("a"), Algorithm.bfs);
            Assert.assertEquals("b -> c -> d -> a", path.toString());

            g.addNodes(new String[]{"e", "f", "g"});
            g.addEdge("d","e");
            g.addEdge("e","f");
            g.addEdge("d","f");
            g.addEdge("f","g");

            path = g.GraphSearch(new Node("a"), new Node("g"), Algorithm.bfs);
            Assert.assertEquals("a -> b -> c -> d -> " +
                    "f -> g", path.toString());
        }
    }
}