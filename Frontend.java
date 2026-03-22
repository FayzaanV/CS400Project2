public class Frontend implements FrontendInterface {
    
    public Frontend(BackendInterface backend) {

    }

    public String generateShortestPathPromptHTML() {
        String startInput = "<input type='text' id='start' placeholder='Enter start location'>";
        String endInput = "<input type='text' id='end' placeholder='Enter end location'>";
        String button = "<button>Find Shortest Path</button>";
        return startInput + "\n" + endInput + "\n" + button + "\n";
    }

    public String generateShortestPathResponseHTML(String start, String end) {
        return null;
    }

    public String generateFurthestLocationListFromPromptHTML() {
        return null;
    }

    public String generateFurthestLocationListFromResponseHTML(String start) {
        return null;
    }

    public static void main(String[] args) {
        Graph_Placeholder graph = new Graph_Placeholder();
        Backend_Placeholder backend = new Backend_Placeholder(graph);
        Frontend frontend = new Frontend(backend);
        System.out.println(frontend.generateShortestPathPromptHTML());
    }
}