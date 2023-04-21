package aramak21.graphSearch;
import aramak21.Graph;
import aramak21.Node;
import aramak21.Path;
import aramak21.graphSearch.strategy.GraphSearcherStrategy;

import java.util.*;

public class GraphSearcher {
    private final GraphSearcherStrategy strategy;

    public GraphSearcher(GraphSearcherStrategy strategy) {
        this.strategy = strategy;
    }

    public Path search(Graph graph, Node source, Node destination){
        return strategy.search(graph, source, destination);
    }
}
