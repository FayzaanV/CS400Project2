import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.List;

public class FrontendTests {
    
    /**
     * This test checks both the generateShortestPathPromptHTML() and generateFurthestLocationListFromPromptHTML() methods
     * It runs both of them and checks to make sure they 1) do not return a null output 2) do not return an empty output and 3) return the
     * correct HTML tags to create the necessary inputs
     */
    @Test
    public void roleTest1() {
        // Create the necessary objects
        Graph_Placeholder graph = new Graph_Placeholder();
        Backend_Placeholder backend = new Backend_Placeholder(graph);
        Frontend frontend = new Frontend(backend);

        // Run both methods to generate input prompts
        String shortestPath = frontend.generateShortestPathPromptHTML();
        String furthestFrom = frontend.generateFurthestLocationListFromPromptHTML();

        // Check to make sure both outputs aren't null and aren't empty
        assertNotNull(shortestPath, "The generateShortestPathPromptHTML() was null");
        assertFalse(shortestPath.isEmpty(), "The generateShortestPathPromptHTML() was empty");
        assertNotNull(furthestFrom, "The generateFurthestLocationListFromPromptHTML() was null");
        assertFalse(furthestFrom.isEmpty(), "The generateFurthestLocationListFromPromptHTML() was empty");

        // Make sure the generateShortestPathPromptHTML() creates text and button inputs
        assertTrue(shortestPath.contains("<input"), "generateShortestPathPromptHTML() has no input tags");
        assertTrue(shortestPath.contains("type='text'"), "generateShortestPathPromptHTML() has no input tags of type 'text'");
        assertTrue(shortestPath.contains("<button>"), "generateShortestPathPromptHTML() has no button tags");

        // Make sure the generateFurthestLocationListFromPromptHTML() creates text and button inputs
        assertTrue(furthestFrom.contains("<input"), "generateFurthestLocationListFromPromptHTML() has no input tags");
        assertTrue(furthestFrom.contains("type='text'"), "generateFurthestLocationListFromPromptHTML() has no input tags of type 'text'");
        assertTrue(furthestFrom.contains("<button>"), "generateFurthestLocationListFromPromptHTML() has no button tags");
    }

    /**
     * This test tests the generateShortestPathResponseHTML() method. It ensures the output is not null or empty, and then
     * checks to make sure the correct HTML tags are in the output. It also checks to see if the correct double value for the shortest time is 
     * in the output.
     */
    @Test
    public void roleTest2() {
        // Create the necessary objects
        Graph_Placeholder graph = new Graph_Placeholder();
        Backend_Placeholder backend = new Backend_Placeholder(graph);
        Frontend frontend = new Frontend(backend);

        // Run the method
        String shortestPath = frontend.generateShortestPathResponseHTML("Union South", "Weeks Hall for Geological Sciences");

        // Check to make sure output is not empty or null
        assertNotNull(shortestPath, "generateShortestPathResponseHTML() returned a null value");
        assertFalse(shortestPath.isEmpty(), "generateShortestPathResponseHTML() returned an empty value");

        // Check to make sure output has necessary HTML tags in it and correctly calculates how much time the shortest path would take
        assertTrue(shortestPath.contains("<p>"), "generateShortestPathResponseHTML() does not contain any p tags");
        assertTrue(shortestPath.contains("</p>"), "generateShortestPathResponseHTML() does not contain any p tags");
        assertTrue(shortestPath.contains("<ol>"), "generateShortestPathResponseHTML() does not contain any ordered list tags");
        assertTrue(shortestPath.contains("<li>"), "generateShortestPathResponseHTML() does not contain any list item tags");
        assertTrue(shortestPath.contains("6.0"), "generateShortestPathResponseHTML() does not correctly calculate time to take shortest path");
    }

    /**
     * This test checks the generateFurthestLocationListFromResponseHTML() method. It calls the method and then stores the output.
     * It checks to make sure the output is not null or empty, then it checks to make sure the output contains the correct HTML tags
     * and checks for the correct integer values to make sure the method calculated the amount of node correctly.
     */
    @Test
    public void roleTest3() {
        // Create the necessary objects
        Graph_Placeholder graph = new Graph_Placeholder();
        Backend_Placeholder backend = new Backend_Placeholder(graph);
        Frontend frontend = new Frontend(backend);

        // Run the method
        String furthest = frontend.generateFurthestLocationListFromResponseHTML("Union South");

        // Check to make sure output is not empty or null
        assertNotNull(furthest, "generateFurthestLocationListFromResponseHTML() returned a null value");
        assertFalse(furthest.isEmpty(), "generateFurthestLocationListFromResponseHTML() returned an empty value");

        // Check to make sure output has necessary HTML tags in it and correctly calculates how much time the shortest path would take
        assertTrue(furthest.contains("<p>"), "generateFurthestLocationListFromResponseHTML() does not contain any p tags");
        assertTrue(furthest.contains("</p>"), "generateFurthestLocationListFromResponseHTML() does not contain any p tags");
        assertTrue(furthest.contains("<ol>"), "generateFurthestLocationListFromResponseHTML() does not contain any ordered list tags");
        assertTrue(furthest.contains("<li>"), "generateFurthestLocationListFromResponseHTML() does not contain any list item tags");
        assertTrue(furthest.contains("3"), "generateFurthestLocationListFromResponseHTML() does not correctly calculate time to take shortest path");
    }

    @Test
    public void IntegrationTest1() {
        GraphADT<String,Double> graph = new DijkstraGraph<>();
		BackendInterface backend = new Backend(graph);
        try {
            backend.loadGraphData("./europeanRail.dot");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
		FrontendInterface frontend = new Frontend(backend);

        try {
            backend.getFurthestFromList("New York");
            fail();
        } catch (NoSuchElementException e) {

        } catch (Exception e) {
            fail();
        }

        String response = frontend.generateFurthestLocationListFromResponseHTML("New York");
        assertTrue(response.contains("The starting node could not be found in the list"));
        
    }

    @Test
    public void IntegrationTest2() {
        GraphADT<String,Double> graph = new DijkstraGraph<>();
		BackendInterface backend = new Backend(graph);
        try {
            backend.loadGraphData("./europeanRail.dot");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
		FrontendInterface frontend = new Frontend(backend);

        String response = frontend.generateFurthestLocationListFromResponseHTML("Vienna");
        List<String> backendResponse = backend.getFurthestFromList("Vienna");

        assertTrue(response.contains("Vienna"));
        assertTrue(response.contains(backendResponse.get(0)));
        assertTrue(response.contains("Furthest location(s): " + backendResponse.size()));
    }

    @Test
    public void IntegrationTest3() {
        GraphADT<String,Double> graph = new DijkstraGraph<>();
		BackendInterface backend = new Backend(graph);
        try {
            backend.loadGraphData("./europeanRail.dot");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
		FrontendInterface frontend = new Frontend(backend);

        try {
            backend.findLocationsOnShortestPath("New York", "Los Angeles");
            fail();
        } catch (NoSuchElementException e) {

        } catch (Exception e) {
            fail();
        }

        String response = frontend.generateShortestPathResponseHTML("New York", "Los Angeles");
        assertTrue(response.contains("Unable to calculate shortest path locations."));
    }
}
