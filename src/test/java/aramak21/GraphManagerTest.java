package aramak21;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GraphManagerTest {
    GraphManager graphManager;

    @Before
    public void setup() throws Exception {
        graphManager = new GraphManager();
        graphManager.parseGraph("testInputGraph.dot");
    }

    @Test
    public void testParseGraph() {
        Assert.assertEquals(4, graphManager.nodeSize());
        Assert.assertEquals(4, graphManager.edgeSize());
        Assert.assertTrue(graphManager.containsEdge("a", "b"));
        Assert.assertTrue(graphManager.containsEdge("b", "c"));
        Assert.assertTrue(graphManager.containsEdge("c", "d"));
        Assert.assertTrue(graphManager.containsEdge("d", "a"));
        System.out.println(graphManager.toString());
    }

    @Test
    public void testAddNode() throws Exception {
        Assert.assertEquals(4, graphManager.nodeSize());
        graphManager.addNode("e");
        System.out.println(graphManager.toString());

        Assert.assertEquals(5, graphManager.nodeSize());
        Assert.assertTrue(graphManager.containsNode("e"));

       graphManager.addNode("a");
        Assert.assertEquals(5, graphManager.nodeSize());
        Assert.assertTrue(graphManager.containsNode("a"));

    }

    @Test
    public void testAddNodes() throws Exception{
        Assert.assertEquals(4, graphManager.nodeSize());
        graphManager.addNodes(new String[]{"e", "f", "g"});
        Assert.assertEquals(7, graphManager.nodeSize());

        graphManager.addNodes(new String[]{"a", "b", "c"});
        Assert.assertEquals(7, graphManager.nodeSize());
    }

    @Test
    public void testAddEdge() throws Exception {
        Assert.assertEquals(4, graphManager.edgeSize());
        graphManager.addEdge("e", "a");
       // System.out.println(g.toString());
        Assert.assertEquals(5, graphManager.edgeSize());
        Assert.assertEquals(5, graphManager.nodeSize());
        Assert.assertTrue(graphManager.containsNode("e"));
        Assert.assertTrue(graphManager.containsEdge("e", "a"));

        graphManager.addEdge("a","b");
        Assert.assertEquals(5, graphManager.edgeSize());
        Assert.assertEquals(5, graphManager.nodeSize());
        Assert.assertTrue(graphManager.containsEdge("a", "b"));
    }

    @Test
    public void testRemoveNode() throws Exception {
        Assert.assertEquals(4, graphManager.nodeSize());
        graphManager.removeNode("a");

        Assert.assertEquals(3, graphManager.nodeSize());
        Assert.assertFalse(graphManager.containsNode("a"));

        graphManager.removeNode("f");
        Assert.assertEquals(3, graphManager.nodeSize());
    }


    @Test
    public void testRemoveNodes() throws Exception{
        Assert.assertEquals(4, graphManager.nodeSize());
        graphManager.removeNodes(new String[]{"e", "f", "g"});
        Assert.assertEquals(4, graphManager.nodeSize());

        graphManager.removeNodes(new String[]{"a", "b", "c"});
        Assert.assertEquals(1, graphManager.nodeSize());

    }

    @Test
    public void testRemoveEdge() {
        Assert.assertEquals(4, graphManager.edgeSize());
        graphManager.removeEdge("b", "c");
       // System.out.println(g.toString());

        Assert.assertEquals(4, graphManager.nodeSize());
        Assert.assertEquals(3, graphManager.edgeSize());

        Assert.assertEquals(3, graphManager.edgeSize());
        graphManager.removeEdge("a","c");
        Assert.assertEquals(4, graphManager.nodeSize());
        Assert.assertEquals(3, graphManager.edgeSize());
    }

    @Test
    public void testOutputDOTGraph() throws IOException {
        graphManager.addEdge("e", "f");
        String outputfile = "output2.dot";
        graphManager.outputDOTGraph(outputfile);

        List<String> output = Files.readAllLines(Paths.get(outputfile));
        System.out.println("output: " + output);
        List<String> expected = Files.readAllLines(Paths.get("expected.txt"));
        Assert.assertEquals(expected, output);
    }

    @Test
    public void testOutputGraph() throws IOException
    {
        String output_graph_name = "testOPGraph.txt";
        graphManager.outputGraph(output_graph_name);
        List<String> output = Files.readAllLines(Paths.get(output_graph_name));
        System.out.println("output: " + output_graph_name);
        List<String> expected = Files.readAllLines(Paths.get("expectedOutputGraph.txt"));
        Assert.assertEquals(expected,output);

    }

    @Test
    public void testDFSGraphSearch() {
        Path path = graphManager.GraphSearch(new Node("a"), new Node("d"), Algorithm.dfs );
        Assert.assertEquals("a -> b -> c -> d", path.toString());

        path = graphManager.GraphSearch(new Node("a"), new Node("a") , Algorithm.dfs);
        Assert.assertEquals("a", path.toString());


        path = graphManager.GraphSearch(new Node("b"), new Node("a") , Algorithm.dfs);
        Assert.assertEquals("b -> c -> d -> a", path.toString());

        graphManager.addNodes(new String[]{"e","f","g", "h", "i"});
        graphManager.addEdge("d","e");
        graphManager.addEdge("d","f");
        graphManager.addEdge("d","g");
        graphManager.addEdge("d","h");
        graphManager.addEdge("g","h");
        graphManager.addEdge("h","i");

        path = graphManager.GraphSearch(new Node("a"), new Node("i") , Algorithm.dfs);
        Assert.assertEquals("a -> b -> c -> d -> g -> h -> i", path.toString());
    }

    @Test
    public void testBFSGraphSearch(){
        {
            Path path = graphManager.GraphSearch(new Node("a"), new Node("d"), Algorithm.bfs);
            Assert.assertEquals("a -> b -> c -> d", path.toString());

            path = graphManager.GraphSearch(new Node("a"), new Node("a"), Algorithm.bfs);
            Assert.assertEquals("a", path.toString());


            path = graphManager.GraphSearch(new Node("b"), new Node("a"), Algorithm.bfs);
            Assert.assertEquals("b -> c -> d -> a", path.toString());

            graphManager.addNodes(new String[]{"e", "f", "g"});
            graphManager.addEdge("d","e");
            graphManager.addEdge("e","f");
            graphManager.addEdge("d","f");
            graphManager.addEdge("f","g");

            path = graphManager.GraphSearch(new Node("a"), new Node("g"), Algorithm.bfs);
            Assert.assertEquals("a -> b -> c -> d -> f -> g", path.toString());
        }
    }
}