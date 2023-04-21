package aramak21.graphSearch.strategy.impl;
import aramak21.Graph;
import aramak21.Node;
import aramak21.Path;
import aramak21.Algorithm;
import aramak21.graphSearch.strategy.GraphSearcherStrategy;
import aramak21.graphTraverse.BFSGraphSearchImpl;
import aramak21.graphTraverse.GraphTraverseAlgorithm;

public class BFSGraphSearcherStrategy extends GraphSearcherStrategy {

    public BFSGraphSearcherStrategy() {
        algoName = Algorithm.bfs;
    }

    @Override
    public Path search(Graph graph, Node source, Node destination) {
        GraphTraverseAlgorithm algo = new BFSGraphSearchImpl(graph, source, destination);
        return algo.search();
    }

}
