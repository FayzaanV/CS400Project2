import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FrontentTests {
    
    @Test
    public void RoleTest1() {
        // Create the necessary objects
        Graph_Placeholder graph = new Graph_Placeholder();
        Backend_Placeholder backend = new Backend_Placeholder(graph);
        Frontend frontend = new Frontend(backend);

        String shortestPath = frontend.generateShortestPathPromptHTML();
        String furthestFrom = frontend.generateFurthestLocationListFromPromptHTML();

        assertFalse(shortestPath == null, "The generateShortestPathPromptHTML() was null");
        assertFalse(shortestPath.isEmpty(), "The generateShortestPathPromptHTML() was null");
        assertFalse(furthestFrom == null, "The generateFurthestLocationListFromPromptHTML() was null");
        assertFalse(furthestFrom.isEmpty(), "The generateFurthestLocationListFromPromptHTML() was null");

        assertTrue(shortestPath.contains("<input"), "generateShortestPathPromptHTML() has no input tags");
        assertTrue(shortestPath.contains("type='text'"), "generateShortestPathPromptHTML() has no input tags of type 'text'");
        assertTrue(shortestPath.contains("<button>"), "generateShortestPathPromptHTML() has no button tags");

        assertTrue(furthestFrom.contains("<input"), "generateFurthestLocationListFromPromptHTML() has no input tags");
        assertTrue(furthestFrom.contains("type='text'"), "generateFurthestLocationListFromPromptHTML() has no input tags of type 'text'");
        assertTrue(furthestFrom.contains("<button>"), "generateFurthestLocationListFromPromptHTML() has no button tags");
    }

    @Test
    public void RoleTest2() {

    }

    @Test
    public void RoleTest3() {

    }
}
