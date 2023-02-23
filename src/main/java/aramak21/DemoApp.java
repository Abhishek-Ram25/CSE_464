package aramak21;

public class DemoApp {

    public static void main(String[] args) throws Exception {

        System.out.println("Software QA and Testing Part-1");
        GraphManager gm = new GraphManager();
        gm.parseGraph("testInputGraph.dot");
    }

}
