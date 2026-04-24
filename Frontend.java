import java.util.List;
 import java.util.NoSuchElementException;

public class Frontend implements FrontendInterface {

    private BackendInterface backend;

    public Frontend(BackendInterface backend) {
        this.backend = backend;
    }

    /**
     * Returns an HTML fragment that includes two text inputs for the user to enter two locations and a button for the user to 
     * calculate the shortest path between these two locations
     * @return the HTML fragment
     */
    public String generateShortestPathPromptHTML() {
        // Write HTML tags as Strings
        String startInput = "<input type='text' id='start' placeholder='Enter start location'>";
        String endInput = "<input type='text' id='end' placeholder='Enter end location'>";
        String button = "<button>Find Shortest Path</button>";
        // Return each tags with a line break in between
        return startInput + "\n" + endInput + "\n" + button + "\n";
    }

    /**
     * Returns an HTML fragment that includes a p-tag with the start and endpoints, an ordered list with every node along the path
     * from the start to the end point, and a p-tag that shows the total time to travel this path. If no path can be found, then
     * state that there is no path
     * @param start is the node at the start of the path
     * @param end is the node at the end of the path that we will calculate
     * @return the HTML fragment
     */
    public String generateShortestPathResponseHTML(String start, String end) {
        // Create a p tag to hold the start and end points
        String endpoints = "<p>Start: " + start + ", End: " + end + "</p>";
        // Get the shortest path from the start to the end from the backend
        // If any errors are thrown, an error message with that exception's message in a p-tag will be returned.
        List<String> shortestPath;
        try {
            shortestPath = backend.findLocationsOnShortestPath(start, end);
        } catch (Exception e) {
            return "<p>Unable to calculate shortest path locations. An exception was thrown: " + e.getMessage() + "</p>";
        }
        StringBuilder path = new StringBuilder();
        // If it returns an empty list, there is no path between the nodes
        // Add this issue as a p tag and return
        if (shortestPath.isEmpty()) {
            path.append("<p>There is no path between the two nodes</p>");
            return endpoints + "\n" + path;
        }
        // If there is a path, loop through the array and add each stop as a list item in the HTML tag
        path.append("<ol>\n");
        for (int i = 0; i < shortestPath.size(); i++) {
            path.append("    <li>").append(shortestPath.get(i)).append("</li>\n");
        }
        path.append("</ol>\n");
        // Calculate the total time from the backend
        // If any errors are thrown, an error message with that exception's message in a p-tag will be returned.
        double totalTime = 0.0;
        List<Double> timeOnShortestPath;
        try {
            timeOnShortestPath = backend.findTimesOnShortestPath(start, end);
        } catch (Exception e) {
            return "<p>Unable to calculate shortest path time. An exception was thrown: " + e.getMessage() + "</p>";
        }
        for(Double d : timeOnShortestPath) {
            totalTime += d;
        }
        // Return each tag with line breaks in between
        return endpoints + "\n" + path.toString() + "<p>Time to take shortest path: " + totalTime + "</p>\n";
    }

    /**
     * Returns an HTML fragment that includes a text inputs for the user to enter a locations and a button for the user to 
     * calculate the furthest location from that node.
     * @return the HTML fragment
     */
    public String generateFurthestLocationListFromPromptHTML() {
        // Write the HTML tags as Strings
        String textInput = "<input type='text' id = 'from' placeholder = 'Enter node here'>";
        String button = "<button>Furthest Location List</button>";
        // Return each tag with a line break in between
        return textInput + "\n" + button;
    }

    /**
     * Returns an HTML fragment that includes a p-tag with the start and endpoints, an ordered list with every node along the path
     * from the start to the end point, and a p-tag that shows the total number of nodes along this path
     * @param start is the node at the start of the path. We will find whatever node is furthest away from this one
     * @return the HTML fragment
     */
    public String generateFurthestLocationListFromResponseHTML(String start) {
        // Get the furthestList and the furthest node from the backend
        // If any errors are thrown, an error message with that exception's message in a p-tag will be returned
        List<String> furthestList;
        try {
            furthestList = backend.getFurthestFromList(start);
        } catch (NoSuchElementException e) {
            return "<p>The starting node could not be found in the list</p>";
        } catch (Exception e) {
            return "<p>Unable to calculate furthest from location. An exception was thrown: " + e.getMessage() + "</p>";
        }
        String end = furthestList.get(furthestList.size() - 1);
        // Make a p tag for the start and end points
        String endpoints = "<p>Start: " + start + ", End: " + end + "</p>";
        StringBuilder path = new StringBuilder();
        // Unlike in generateShortestPathResponseHTML(), there is guaranteed to be a path between the nodes
        // Loop through the whole list and add each node as a list item
        path.append("<ol>\n");
        for (int i = 0; i < furthestList.size(); i++) {
            path.append("    <li>").append(furthestList.get(i)).append("</li>\n");
        }
        path.append("</ol>\n");
        // The nodes on the path is just the size of the array
        int nodesOnPath = furthestList.size();
        // Return each tag with line breaks in between
        return endpoints + "\n" + path.toString() + "<p>Nodes on shortest path: " + nodesOnPath + "</p>\n";
    }
}