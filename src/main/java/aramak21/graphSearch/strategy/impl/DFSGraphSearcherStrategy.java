package aramak21.graphSearch.strategy.impl;

import aramak21.Graph;
import aramak21.Node;
import aramak21.Path;
import aramak21.Algorithm;
import aramak21.graphSearch.strategy.GraphSearcherStrategy;
import aramak21.graphTraverse.DFSGraphSearchImpl;
import aramak21.graphTraverse.GraphTraverseAlgorithm;
public class DFSGraphSearcherStrategy extends GraphSearcherStrategy {

    public DFSGraphSearcherStrategy() {
        algoName = Algorithm.dfs;
    }

    @Override
    public Path search(Graph graph, Node source, Node destination) {
        GraphTraverseAlgorithm algo = new DFSGraphSearchImpl(graph, source, destination);
        return algo.search();
    }
}
