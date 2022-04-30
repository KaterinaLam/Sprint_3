import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
/**
 * Получить случайную строку указанной длины.
 */
@Data

public class CourierGenerator {
    public static Courier getRandom(){
        String login = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);
        String firstName = RandomStringUtils.randomAlphabetic(10);
        Courier courier = new Courier(login, password, firstName);
        return courier;
    }
}