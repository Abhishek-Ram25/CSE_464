package aramak21;

public class Edge {
    public Node source;

    public Node target;

    public Edge(Node source, Node target)
    {
        this.source = source;
        this.target = target;
    }

    public Node getSource() {
        return source;
    }

    public Node getTarget()
    {
        return target;
    }

}
