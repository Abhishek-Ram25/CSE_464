package aramak21;

import java.util.Iterator;
import java.util.LinkedList;

public class Path {
    final LinkedList<Node> nodesInThePath;

    public Path() {
        nodesInThePath = new LinkedList<>();
    }

    public void addNodeInTheEnd(Node node){
        nodesInThePath.add(node);
    }

    public void addNodeInTheFront(Node node){
        nodesInThePath.addFirst(node);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (!nodesInThePath.isEmpty()){
            Iterator<Node> iterator = nodesInThePath.iterator();
            builder.append(iterator.next().getName());
            while (iterator.hasNext()){
                builder.append(" -> ").append(iterator.next().getName());
            }
        }
        return builder.toString();
    }

    public Node removeLastNode() {
        return nodesInThePath.removeLast();
    }
}
