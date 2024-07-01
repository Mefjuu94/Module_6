import org.example.JSONMapper;
import org.example.JSONPlaceholderFetcher;
import org.example.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceMockitoTest {

    private JSONPlaceholderFetcher jsonPlaceholderFetcher;
    @Mock
    private HttpResponse<String> response;
    @Mock
    HttpClient client;
    @Mock
    HttpRequest request;

    @Test
    public void testStuff(){

        jsonPlaceholderFetcher = new JSONPlaceholderFetcher(client);

        when(response.statusCode()).thenReturn(404);
        System.out.println(response.statusCode());
        Assertions.assertEquals(404, response.statusCode());
    }

    public void setResponse(String responseBody,int statusCode){
        when(response.statusCode()).thenReturn(statusCode);
        when(response.body()).thenReturn(responseBody);
        }

    @Test
    public void happyPathToResponseBody() throws IOException, InterruptedException, URISyntaxException {

        setResponse("{\n" +
                "  \"userId\": 1,\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"sunt\",\n" +
                "  \"body\": \"ok\"}", 200);

        Post post = JSONMapper.mapTo(response.body());
        String URL = "https://jsonplaceholder.typicode.com/posts";
        request = HttpRequest.newBuilder(new URI(URL + "/" + 1)).GET().build();
        jsonPlaceholderFetcher = new JSONPlaceholderFetcher(client);
        // przekazanie do jsonPlaceholderFetcher stworzonego clienta wydmuszkę
        when(client.send(request, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        // kiedy wywołam clienta (zapytanie ma zwrócić response z ustawioną odpowiedzią)
        Post expectedPost = new Post(1,1,"sunt","ok");
        Assertions.assertEquals(expectedPost,jsonPlaceholderFetcher.getSinglePost(1));
    }

    @Test
    public void happyPathToResponseUserID() throws URISyntaxException, IOException, InterruptedException {

        setResponse("{\n" +
                "  \"userId\": 1,\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"sunt\",\n" +
                "  \"body\": \"ok\"}",200);


        Post post = JSONMapper.mapTo(response.body());
        String URL = "https://jsonplaceholder.typicode.com/posts";
        request = HttpRequest.newBuilder(new URI(URL + "/" + 1)).GET().build();
        jsonPlaceholderFetcher = new JSONPlaceholderFetcher(client);
        // przekazanie do jsonPlaceholderFetcher stworzonego clienta wydmuszkę
        when(client.send(request, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        // kiedy wywołam clienta (zapytanie ma zwrócić response z ustawioną odpowiedzią)
        Post expectedPost = new Post(1,1,"sunt","ok");
        Assertions.assertEquals(expectedPost.getUserID(),jsonPlaceholderFetcher.getSinglePost(1).getUserID());
    }

    @Test
    public void happyPathToGetAllPosts() throws URISyntaxException, IOException, InterruptedException {

        setResponse("[{\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 6,\n" +
                "    \"title\": \"dolorem eum magni eos aperiam quia\",\n" +
                "    \"body\": \"ut aspernatur corporis harum nihil quis provident sequi\\nmollitia nobis aliquid molestiae\\nperspiciatis et ea nemo ab reprehenderit accusantium quas\\nvoluptate dolores velit et doloremque molestiae\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 7,\n" +
                "    \"title\": \"magnam facilis autem\",\n" +
                "    \"body\": \"dolore placeat quibusdam ea quo vitae\\nmagni quis enim qui quis quo nemo aut saepe\\nquidem repellat excepturi ut quia\\nsunt ut sequi eos ea sed quas\"\n" +
                "  }]",200);

        String URL = "https://jsonplaceholder.typicode.com/posts";
        request = HttpRequest.newBuilder(new URI(URL)).GET().build();
        jsonPlaceholderFetcher = new JSONPlaceholderFetcher(client);
        // przekazanie do jsonPlaceholderFetcher stworzonego clienta wydmuszkę
        when(client.send(request, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        // kiedy wywołam clienta (zapytanie ma zwrócić response z ustawioną odpowiedzią)


        String s = "[{\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 6,\n" +
                "    \"title\": \"dolorem eum magni eos aperiam quia\",\n" +
                "    \"body\": \"ut aspernatur corporis harum nihil quis provident sequi\\nmollitia nobis aliquid molestiae\\nperspiciatis et ea nemo ab reprehenderit accusantium quas\\nvoluptate dolores velit et doloremque molestiae\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 7,\n" +
                "    \"title\": \"magnam facilis autem\",\n" +
                "    \"body\": \"dolore placeat quibusdam ea quo vitae\\nmagni quis enim qui quis quo nemo aut saepe\\nquidem repellat excepturi ut quia\\nsunt ut sequi eos ea sed quas\"\n" +
                "  }]";
        ArrayList<Post> predict = JSONMapper.mapToListOfPosts(s);
        Assertions.assertEquals(predict,jsonPlaceholderFetcher.getAllPosts());
    }

    @Test
    public void happyPathToSetPost() throws IOException, InterruptedException, URISyntaxException {

        setResponse("{\n" +
                "  \"userId\": 1,\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"sunt\",\n" +
                "  \"body\": \"ok\"}", 201);

        Post post = JSONMapper.mapTo(response.body());
        String URL = "https://jsonplaceholder.typicode.com/posts";
        request = HttpRequest.newBuilder(new URI(URL)).POST(HttpRequest.BodyPublishers.ofString(post.toString())).build();
        jsonPlaceholderFetcher = new JSONPlaceholderFetcher(client);
        // przekazanie do jsonPlaceholderFetcher stworzonego clienta wydmuszkę
        when(client.send(request, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        // kiedy wywołam clienta (zapytanie ma zwrócić response z ustawioną odpowiedzią)
        Assertions.assertEquals(true,jsonPlaceholderFetcher.addPost("lala"));
    }

    //unhappy ways

    @Test
    public void unHappyPathToResponseBody() throws IOException, InterruptedException, URISyntaxException {

        setResponse("{\n" +
                "  \"userId\": 1,\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"sunt\",\n" +
                "  \"body\": \"ok\"}", 404);

        Post post = JSONMapper.mapTo(response.body());
        String URL = "https://jsonplaceholder.typicode.com/posts";
        request = HttpRequest.newBuilder(new URI(URL + "/" + 1)).GET().build();
        jsonPlaceholderFetcher = new JSONPlaceholderFetcher(client);
        // przekazanie do jsonPlaceholderFetcher stworzonego clienta wydmuszkę
        when(client.send(request, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        // kiedy wywołam clienta (zapytanie ma zwrócić response z ustawioną odpowiedzią)
        Assertions.assertEquals(null,jsonPlaceholderFetcher.getSinglePost(1));
    }


    @Test
    public void unHappyPathToSetPost() throws IOException, InterruptedException, URISyntaxException {

        setResponse("{\n" +
                "  \"userId\": 1,\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"sunt\",\n" +
                "  \"body\": \"ok\"}", 200);

        Post post = JSONMapper.mapTo(response.body());
        String URL = "https://jsonplaceholder.typicode.com/posts";
        request = HttpRequest.newBuilder(new URI(URL)).POST(HttpRequest.BodyPublishers.ofString(post.toString())).build();
        jsonPlaceholderFetcher = new JSONPlaceholderFetcher(client);
        // przekazanie do jsonPlaceholderFetcher stworzonego clienta wydmuszkę
        when(client.send(request, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        // kiedy wywołam clienta (zapytanie ma zwrócić response z ustawioną odpowiedzią)
        Assertions.assertFalse(jsonPlaceholderFetcher.addPost("lala"));
    }

    @Test
    public void getAllPostsGoneWrong() throws URISyntaxException, IOException, InterruptedException {

        setResponse("[{\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 6,\n" +
                "    \"title\": \"dolorem eum magni eos aperiam quia\",\n" +
                "    \"body\": \"ut aspernatur corporis harum nihil quis provident sequi\\nmollitia nobis aliquid molestiae\\nperspiciatis et ea nemo ab reprehenderit accusantium quas\\nvoluptate dolores velit et doloremque molestiae\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 7,\n" +
                "    \"title\": \"magnam facilis autem\",\n" +
                "    \"body\": \"dolore quo vitae\\nmagni quis enim qui quis quo nemo aut saepe\\nquidem repellat excepturi ut quia\\nsunt ut sequi eos ea sed quas\"\n" +
                "  }]",200);

        String URL = "https://jsonplaceholder.typicode.com/posts";
        request = HttpRequest.newBuilder(new URI(URL)).GET().build();
        jsonPlaceholderFetcher = new JSONPlaceholderFetcher(client);
        // przekazanie do jsonPlaceholderFetcher stworzonego clienta wydmuszkę
        when(client.send(request, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        // kiedy wywołam clienta (zapytanie ma zwrócić response z ustawioną odpowiedzią)


        String s = "[{\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 6,\n" +
                "    \"title\": \"dolorem eum magni eos aperiam quia\",\n" +
                "    \"body\": \"ut aspernatur corporis harum nihil quis provident sequi\\nmollitia nobis aliquid molestiae\\nperspiciatis et ea nemo ab reprehenderit accusantium quas\\nvoluptate dolores velit et doloremque molestiae\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 7,\n" +
                "    \"title\": \"magnam facilis autem\",\n" +
                "    \"body\": \"dolore placeat quibusdam ea quo vitae\\nmagni quis enim qui quis quo nemo aut saepe\\nquidem repellat excepturi ut quia\\nsunt ut sequi eos ea sed quas\"\n" +
                "  }]";
        ArrayList<Post> predict = JSONMapper.mapToListOfPosts(s);
        Assertions.assertNotEquals(predict,jsonPlaceholderFetcher.getAllPosts());
    }
}
