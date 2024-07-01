import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.JSONMapper;
import org.example.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonMapperTests {

    @Test
    public void mapToJSONHappyPath(){
        Post postToEx5 = new Post(999,99,"lalala","body");

        String expected = "{\"userID\":999,\"id\":99,\"title\":\"lalala\",\"body\":\"body\"}";
        Assertions.assertEquals(expected,JSONMapper.mapToJSON(postToEx5));
    }

    @Test
    public void mapToJSONUnHappyPath(){

        Post postToEx5 = new Post(999,99,"lalala","body");

        String expected = "{\"userID\":999,\"id\":99,\"ttle\":\"lalala\",\"body\":\"body\"}";
        Assertions.assertNotEquals(expected,JSONMapper.mapToJSON(postToEx5));
    }

    @Test
    public void mapToJSONUnHappyPathExcptions(){

        Post postToEx5 = new Post(23/2,99,"lalala","1");

        String expected = "{\"userID\":999,\"id\":99,\"ttle\":\"lalala\",\"body\":\"body\"}";
        Assertions.assertNotEquals(expected,JSONMapper.mapToJSON(postToEx5));
    }
}
