package aramak21.graphSearch.strategy;
import aramak21.Graph;
import aramak21.Node;
import aramak21.Path;
import aramak21.Algorithm;
public abstract class GraphSearcherStrategy {
    protected Algorithm algoName;

    public GraphSearcherStrategy(Algorithm algorithmName) {
        this.algoName = algorithmName;
    }

    public abstract Path search(Graph graph, Node source, Node destination);

    public Algorithm getAlgoName() {
        return algoName;
    }
}
