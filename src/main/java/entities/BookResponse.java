package entities;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BookResponse {
    private int id;
    private int userId;
    private String title;
    private String body;
}
