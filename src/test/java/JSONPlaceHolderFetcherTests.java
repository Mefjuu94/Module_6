import org.example.JSONMapper;
import org.example.JSONPlaceholderFetcher;
import org.example.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;

public class JSONPlaceHolderFetcherTests {

    private final JSONPlaceholderFetcher jsonPlaceHolderFetcher = new JSONPlaceholderFetcher(HttpClient.newBuilder().build());

    private final String URL = "https://jsonplaceholder.typicode.com/posts";

    @Test
    public void getSinglePostHappyPath() {
        String predicted = "{" +
                "\"userId\":1," +
                "\"id\":1," +
                "\"title\":\"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\"," +
                "\"body\":\"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"" +
                "}";

        Post post = JSONMapper.mapTo(predicted);
        Assertions.assertEquals(post, jsonPlaceHolderFetcher.getSinglePost(1));
    }


    @Test
    public void getSizeOfAllPostsHappy(){
        Assertions.assertEquals(100,jsonPlaceHolderFetcher.getAllPosts().size());
    }

    @Test
    public void postMappingIsWorkingHappy(){
        Post post = new Post(87,156,"middleTitle","Body Shame");

        String predicted = "{\"userID\":87,\"id\":156,\"title\":\"middleTitle\",\"body\":\"Body Shame\"}";
        Assertions.assertEquals(predicted,JSONMapper.mapToJSON(post));
    }



    //UnHappy path
    @Test
    public void getSinglePostUnhappyPath() {
        String predicted = "{" +
                "\"userId\":1," +
                "\"id\":1," +
                "\"title\":\"sunt aut facere repellat provident epturi optio reprehenderit\"," +
                "\"body\":\"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"" +
                "}";

        Post post = JSONMapper.mapTo(predicted);

        Assertions.assertNotEquals(post, jsonPlaceHolderFetcher.getSinglePost(1));
    }

    @Test
    public void getSizeOfAllPostsUnhappy(){
        Assertions.assertNotEquals(101,jsonPlaceHolderFetcher.getAllPosts().size());
    }

    @Test
    public void postMappingIsNotWorkingUnHappy(){
        Post post = new Post(87,156,"middleTitle","Body Shame");

        String predicted = "{\"userID\":1,\"id\":16,\"title\":\"middleTitle\",\"body\":\"Body Shame\"}";
        Assertions.assertNotEquals(predicted,JSONMapper.mapToJSON(post));
    }
    // wincyj przypadków negartywnych z inputem, złym stringiem ect

    @Test
    public void getSinglePostInvalidNumber() {
        String predicted = "{" +
                "\"userId\":1," +
                "\"id\":1," +
                "\"title\":\"sunt aut facere repellat provident epturi optio reprehenderit\"," +
                "\"body\":\"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"" +
                "}";

        Post post = JSONMapper.mapTo(predicted);

        Assertions.assertNotEquals(post, jsonPlaceHolderFetcher.getSinglePost(-23));
        Assertions.assertNotEquals(post, jsonPlaceHolderFetcher.getSinglePost(213));
    }

}
