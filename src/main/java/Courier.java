import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Описание курьера.
 */
public class Courier {
    private String login;
    private String password;
    private String firstName;
}
