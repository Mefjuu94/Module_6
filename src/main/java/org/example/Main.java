package org.example;

import java.net.http.HttpResponse;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        //zad 2a
        System.out.println("*****2a*************");
        JSONPlaceholderFetcher jsonPlaceholderFetcher = new JSONPlaceholderFetcher();
        System.out.println(jsonPlaceholderFetcher.getSinglePost(4));

        //2b
        System.out.println("*****2b*************");
        System.out.println(jsonPlaceholderFetcher.getAllPosts().size());
        for (Post s: jsonPlaceholderFetcher.getAllPosts()) {
            System.out.println(s.toString());
        }
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

        Post postToEx5 = new Post(999,998,"lalala","body");

        System.out.println(jsonMapper.mapToJSON(postToEx5));

//       6 Napisz testy wykorzystując nowo napisane mapowanie:
//        Sprawdź, czy przy wywołaniu metody getSinglePost z argumentem 1 otrzymany obiekt ma takie same wartości jak w JSONie:
//        {
//            "userId": 1,
//                "id": 1,
//                "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
//                "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
//        },
//        Sprawdź czy przy wywołaniu metody getAllPosts rozmiar tablicy jest równy 100;
//        Sprawdź, czy jeśli stworzysz obiekt klasy Post i go zmapujesz na JSON, czy String będzie równy temu co zakładasz.
//       7 Dodaj testy z wykorzystaniem biblioteki Mockito, w ramach których zweryfikujesz działanie API bez potrzeby robienia zapytań


    }
}