package org.example;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.w3c.dom.Node;

import java.util.ArrayList;

public class JSONMapper {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public Post mapTo(String JSON) {
        Post post = new Post();

        try {
            JsonNode jsonNode = MAPPER.readTree(JSON);
            post.setId(jsonNode.get("id").asLong());
            post.setUserID(jsonNode.get("userId").asLong());
            post.setTitle(jsonNode.get("title").textValue());
            post.setBody(jsonNode.get("body").textValue());

            return post;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Post> mapToListOfPosts(String JSON) {
        try {
            JsonNode node = MAPPER.readTree(JSON);
            ArrayList<Post> allPosts = new ArrayList<>();
            for (int i = 0; i < node.size(); i++) {
                allPosts.add(mapTo(String.valueOf(node.get(i))));
            }
            return allPosts;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String mapToJSON(Post post) {
        try {
           return MAPPER.writeValueAsString(post);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



}
