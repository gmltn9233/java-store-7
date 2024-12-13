package store.domain;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Product {
    private final String name;
    private final int price;
    private final int quantity;
    private final String promotion;

    public Product(List<String> input) {
        this.name = input.get(0);
        this.price = Integer.parseInt(input.get(1));
        this.quantity = Integer.parseInt(input.get(2));
        this.promotion = input.get(3);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("- " + name + " " + priceFormat() + "원 " + quantity + "개 ");
        if(!promotion.equals("null")){
            output.append(promotion);
        }
        output.append("\r\n");
        return output.toString();
    }

    private String priceFormat() {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        return numberFormat.format(price);
    }
}
