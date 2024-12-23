package entities;

import lombok.Data;

@Data
public class BookResponse {
    private int id;
    private int userId;
    private String title;
    private String body;
}
