import java.util.List;

public class Frontend implements FrontendInterface {
    
    private BackendInterface backend;

    public Frontend(BackendInterface backend) {
        this.backend = backend;
    }

    public String generateShortestPathPromptHTML() {
        // Write HTML tags as Strings
        String startInput = "<input type='text' id='start' placeholder='Enter start location'>";
        String endInput = "<input type='text' id='end' placeholder='Enter end location'>";
        String button = "<button>Find Shortest Path</button>";
        // Return each tags with a line break in between
        return startInput + "\n" + endInput + "\n" + button + "\n";
    }

    public String generateShortestPathResponseHTML(String start, String end) {
        // Create a p tag to hold the start and end points
        String endpoints = "<p>Start: " + start + ", End: " + end + "</p>";
        // Get the shortest path from the start to the end from the backend
        List<String> shortestPath = backend.findLocationsOnShortestPath(start, end);
        String path;
        // If it returns an empty list, there is no path between the nodes
        // Add this issue as a p tag and return
        if (shortestPath.isEmpty()) {
            path = "<p>There is no path between the two nodes</p>";
            return endpoints + "\n" + path;
        }
        // If there is a path, loop through the array and add each stop as a list item in the HTML tag
        path = "<ol>\n";
        for (int i = 0; i < shortestPath.size(); i++) {
            path = path.concat("    <li>" + shortestPath.get(i) + "</li>\n");
        }
        path = path.concat("</ol>\n");
        // Calculate the total time from the backend
        double totalTime = 0.0;
        List<Double> timeOnShortestPath = backend.findTimesOnShortestPath(start, end);
        for(Double d : timeOnShortestPath) {
            totalTime += d;
        }
        // Return each tag with line breaks in between
        return endpoints + "\n" + path + "<p>Time to take shortest path: " + totalTime + "</p>\n";
    }

    public String generateFurthestLocationListFromPromptHTML() {
        // Write the HTML tags as Strings
        String textInput = "<input type='text' id = 'from' placeholder = 'Enter node here'>";
        String button = "<button>Furthest Location List</button>";
        // Return each tag with a line break in between
        return textInput + "\n" + button;
    }

    public String generateFurthestLocationListFromResponseHTML(String start) {
        // Get the furthestList and the furthest node from the backend
        List<String> furthestList = backend.getFurthestFromList(start);
        String end = furthestList.get(furthestList.size() - 1);
        // Make a p tag for the start and end points
        String endpoints = "<p>Start: " + start + ", End: " + end + "</p>";
        String path;
        // Unlike in generateShortestPathResponseHTML(), there is guaranteed to be a path between the nodes
        // Loop through the whole list and add each node as a list item
        path = "<ol>\n";
        for (int i = 0; i < furthestList.size(); i++) {
            path = path.concat("    <li>" + furthestList.get(i) + "</li>\n");
        }
        path = path.concat("</ol>\n");
        // The nodes on the path is just the size of the array
        int nodesOnPath = furthestList.size();
        // Return each tag with line breaks in between
        return endpoints + "\n" + path + "<p>Nodes on shortest path: " + nodesOnPath + "</p>\n";
    }

    // public static void main(String[] args) {
    //     Graph_Placeholder graph = new Graph_Placeholder();
    //     Backend_Placeholder backend = new Backend_Placeholder(graph);
    //     Frontend frontend = new Frontend(backend);
    //     System.out.println(frontend.generateShortestPathPromptHTML());
    //     System.out.println(frontend.generateShortestPathResponseHTML("Union South", "Computer Sciences and Statistics"));
    //     System.out.println(frontend.generateFurthestLocationListFromPromptHTML());
    //     System.out.println(frontend.generateFurthestLocationListFromResponseHTML("Union South"));
    // }
}