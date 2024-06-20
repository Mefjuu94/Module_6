package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class JSONPlaceholderFetcher {

    private final String URL = "https://jsonplaceholder.typicode.com/posts";

    private HttpClient client = HttpClient.newHttpClient();


    public Post getSinglePost(int id) {

        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(URL + "/" + id)).GET().build();
            Post post = new Post();
            JSONMapper mapper = new JSONMapper();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            post = mapper.mapTo(response.body());
            return post;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // -> używając metody GET, zapytaj o pojedynczy post, używając podanego id przy budowie URI i zwróć ten post;
    public ArrayList<Post> getAllPosts() {
        JSONMapper jsonMapper = new JSONMapper();

        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(URL)).GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return jsonMapper.mapToListOfPosts(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //-> używając metody GET zapytaj o wszystkie posty i zwróć je;
    public boolean addPost(String post) {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(URL)).POST(HttpRequest.BodyPublishers.ofString(post)).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

            return response.statusCode() == 201;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    // -> dodaj ciało do swojego zapytania a następnie używając metody POST dodaj nowe dane do istniejącego zasobu. Zwróć true, kiedy operacja zakończy się sukcesem, false, jeśli nie (oprzyj sie o zwrócony kod).

}
