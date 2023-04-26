package aramak21.graphSearch.strategy.factory;

import aramak21.Algorithm;
import aramak21.graphSearch.strategy.GraphSearcherStrategy;
import aramak21.graphSearch.strategy.impl.BFSGraphSearcherStrategy;
import aramak21.graphSearch.strategy.impl.DFSGraphSearcherStrategy;
import aramak21.graphSearch.strategy.impl.RandomWalkSearcherStrategy;

import java.util.HashMap;
import java.util.Map;

public class GraphSearcherStrategyFactory {
    private static final Map<Algorithm, GraphSearcherStrategy> strategy = new HashMap<>();

    static {
        DFSGraphSearcherStrategy dfsStrategy = new DFSGraphSearcherStrategy();
        BFSGraphSearcherStrategy bfsStrategy = new BFSGraphSearcherStrategy();
        RandomWalkSearcherStrategy randomWalkStrategy = new RandomWalkSearcherStrategy();
        strategy.put(dfsStrategy.getAlgoName(), dfsStrategy);
        strategy.put(bfsStrategy.getAlgoName(), bfsStrategy);
        strategy.put(randomWalkStrategy.getAlgoName(),randomWalkStrategy);
    }
    public static GraphSearcherStrategy getStrategy(Algorithm algorithm){
        return strategy.get(algorithm);
    }
}
