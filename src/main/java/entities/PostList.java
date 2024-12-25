package entities;

import lombok.Data;


@Data
public class PostList {
    private int id;
    private int userId;
    private String title;
    private String body;

}
