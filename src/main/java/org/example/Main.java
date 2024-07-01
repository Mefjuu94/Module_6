package org.example;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


//        zad 2a
        System.out.println("*****2a*************");
        JSONPlaceholderFetcher jsonPlaceholderFetcher = new JSONPlaceholderFetcher(HttpClient.newBuilder().build());
//        System.out.println(jsonPlaceholderFetcher.getSinglePost(-23));

        //2b
//        System.out.println("*****2b*************");
        jsonPlaceholderFetcher.getAllPosts();
//        System.out.println(jsonPlaceholderFetcher.getAllPosts().size());
//        for (Post s: jsonPlaceholderFetcher.getAllPosts()) {
//            System.out.println(s.toString());
//        }
        //2c
        System.out.println("*****2c*************");

        String addPost = "{\n" +
                "    \"userId\": 9,\n" +
                "    \"id\": 85,\n" +
                "    \"title\": \"This is title\",\n" +
                "    \"body\": \"similique sed nisi voluptas iusto omnis\\nmollitia et quo\"\n" +
                "  }";

        System.out.println(jsonPlaceholderFetcher.addPost(addPost));

        //3
        System.out.println("*****3 c*************");

        JSONMapper jsonMapper = new JSONMapper();
        System.out.println(jsonMapper.mapTo(addPost));

        //5
        System.out.println("***** 5 *************");

        Post postToEx5 = new Post(999,99,"lalala","body");

        System.out.println(jsonMapper.mapToJSON(postToEx5));
        System.out.println(jsonPlaceholderFetcher.getSinglePost(99));


    }
}