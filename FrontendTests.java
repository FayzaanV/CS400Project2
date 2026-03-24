import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FrontendTests {
    
    /**
     * This test checks both the generateShortestPathPromptHTML() and generateFurthestLocationListFromPromptHTML() methods
     * It runs both of them and checks to make sure they 1) do not return a null output 2) do not return an empty output and 3) return the
     * correct HTML tags to create the necessary inputs
     */
    @Test
    public void RoleTest1() {
        // Create the necessary objects
        Graph_Placeholder graph = new Graph_Placeholder();
        Backend_Placeholder backend = new Backend_Placeholder(graph);
        Frontend frontend = new Frontend(backend);

        // Run both methods to generate input prompts
        String shortestPath = frontend.generateShortestPathPromptHTML();
        String furthestFrom = frontend.generateFurthestLocationListFromPromptHTML();

        // Check to make sure both outputs aren't null and aren't empty
        assertFalse(shortestPath == null, "The generateShortestPathPromptHTML() was null");
        assertFalse(shortestPath.isEmpty(), "The generateShortestPathPromptHTML() was null");
        assertFalse(furthestFrom == null, "The generateFurthestLocationListFromPromptHTML() was null");
        assertFalse(furthestFrom.isEmpty(), "The generateFurthestLocationListFromPromptHTML() was null");

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
    public void RoleTest2() {
        // Create the necessary objects
        Graph_Placeholder graph = new Graph_Placeholder();
        Backend_Placeholder backend = new Backend_Placeholder(graph);
        Frontend frontend = new Frontend(backend);

        // Run the method
        String shortestPath = frontend.generateShortestPathResponseHTML("Union South", "Weeks Hall for Geological Sciences");

        // Check to make sure output is not empty or null
        assertFalse(shortestPath == null, "generateShortestPathResponseHTML() returned a null value");
        assertFalse(shortestPath.isEmpty(), "generateShortestPathResponseHTML() returned an empty value");

        // Check to make sure output has necessary HTML tags in it and correctly calculates how much time the shortest path would take
        assertTrue(shortestPath.contains("<p>"), "generateShortestPathResponseHTML() does not contain any p tags");
        assertTrue(shortestPath.contains("</p>"), "generateShortestPathResponseHTML() does not contain any p tags");
        assertTrue(shortestPath.contains("<ol>"), "generateShortestPathResponseHTML() does not contain any ordered list tags");
        assertTrue(shortestPath.contains("<li>"), "generateShortestPathResponseHTML() does not contain any list item tags");
        assertTrue(shortestPath.contains("6.0"), "generateShortestPathResponseHTML() does not correctly calculate time to take shortest path");
    }

    @Test
    public void RoleTest3() {

    }
}
