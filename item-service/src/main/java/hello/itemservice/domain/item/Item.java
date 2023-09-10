package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Setter @Getter
public class Item {

    private Long id;
    private String itemName;
    private Integer price; // int 대신 Integer 쓰면 null 가능
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}

