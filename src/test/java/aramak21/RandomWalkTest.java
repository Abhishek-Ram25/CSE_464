package aramak21;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RandomWalkTest {
    private static GraphManager graphManager;
    private static final String TEST_FILE_NAME = "rand_walk.dot";
    @Before
    public void setUp() throws Exception {
        graphManager = new GraphManager();
        graphManager.parseGraph(TEST_FILE_NAME);
    }

    @Test
    public void testRandomWalkGraphSearch() {
        System.out.println("random testing");
        Path path = graphManager.GraphSearch(new Node("a"), new Node("c"), Algorithm.random_walk);
        Assert.assertEquals("a -> b -> c", path.toString());
    }
}
