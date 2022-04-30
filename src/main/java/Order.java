import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * Заказ.
 */
public class Order {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;


    /**
     * Получает заготовку заказа
     */
    public static Order getDefault(){
        String firstName = "Петя";
        String lastName = "Петров";
        String address = "Проспект Ленина, 7";
        String metroStation = "2";
        String phone = "+77779994455";
        int rentTime = 3;
        String deliveryDate = "04.05.22";
        String comment = "Позвонить за час";
        String[] color = new String[2];
        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }
}