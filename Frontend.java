import java.util.List;

public class Frontend implements FrontendInterface {
    
    private BackendInterface backend;

    public Frontend(BackendInterface backend) {
        this.backend = backend;
    }

    public String generateShortestPathPromptHTML() {
        String startInput = "<input type='text' id='start' placeholder='Enter start location'>";
        String endInput = "<input type='text' id='end' placeholder='Enter end location'>";
        String button = "<button>Find Shortest Path</button>";
        return startInput + "\n" + endInput + "\n" + button + "\n";
    }

    public String generateShortestPathResponseHTML(String start, String end) {
        String endpoints = "<p>Start: " + start + ", End: " + end + "</p>";
        List<String> shortestPath = backend.findLocationsOnShortestPath(start, end);
        String path;
        if (shortestPath.isEmpty()) {
            path = "<p>There is no path between the two nodes</p>";
            return endpoints + "\n" + path;
        }
        path = "<ol>\n";
        for (int i = 0; i < shortestPath.size(); i++) {
            path = path.concat("<li>" + shortestPath.get(i) + "</li>\n");
        }
        path = path.concat("</ol>\n");
        double totalTime = 0.0;
        List<Double> timeOnShortestPath = backend.findTimesOnShortestPath(start, end);
        for(Double d : timeOnShortestPath) {
            totalTime += d;
        }
        return endpoints + "\n" + path + "<p>Time to take shortest path: " + totalTime + "</p>\n";
    }

    public String generateFurthestLocationListFromPromptHTML() {
        String textInput = "<input type = 'text' id = 'from' placeholder = 'Enter node here'>";
        String button = "<button>Furthest Location List</button>";
        return textInput + "\n" + button;
    }

    public String generateFurthestLocationListFromResponseHTML(String start) {
        return null;
    }

    public static void main(String[] args) {
        Graph_Placeholder graph = new Graph_Placeholder();
        Backend_Placeholder backend = new Backend_Placeholder(graph);
        Frontend frontend = new Frontend(backend);
        // System.out.println(frontend.generateShortestPathPromptHTML());
        System.out.println(frontend.generateShortestPathResponseHTML("Union South", "Computer Sciences and Statistics"));
        // System.out.println(frontend.generateFurthestLocationListFromPromptHTML());
    }
}