package store.domain.dto;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import store.domain.Product;

public class OrderResponse {
    private final Map<Product, Integer> orders;
    private final Map<Product, Integer> gifts;
    private int totalPrice = 0;
    private int totalQuantity = 0;
    private int totalGiftPrice = 0;
    private int totalPromotionPrice = 0;
    private boolean membership = false;

    public OrderResponse(Map<Product, Integer> orders, Map<Product, Integer> gifts) {
        this.orders = orders;
        this.gifts = gifts;
    }

    public void setMembership() {
        this.membership = true;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("==============W 편의점================\r\n");
        output.append("상품명\t\t수량\t금액\r\n");
        output.append(ordersToString());
        output.append("=============증\t정===============\r\n");
        output.append(giftsToString());
        output.append("====================================\r\n");
        output.append(totalPrice());
        output.append(totalDiscount());
        output.append(membershipDiscount());
        output.append(finalPrice());
        return output.toString();
    }

    private String ordersToString() {
        StringBuilder output = new StringBuilder();
        orders.forEach(((product, quantity) -> {
            output.append(product.getName() + "\t\t");
            output.append(quantity + " \t");
            output.append(priceFormat(product.getPrice()) + "\r\n");
            totalQuantity += quantity;
            totalPrice += product.getPrice() * quantity;
        }));
        return output.toString();
    }

    private String giftsToString() {
        StringBuilder output = new StringBuilder();
        gifts.forEach(((product, quantity) -> {
            output.append(product.getName() + "\t\t");
            output.append(quantity + "\r\n");
            totalGiftPrice += product.getPrice() * quantity;
            totalPromotionPrice += quantity * product.getPromotion().getBuy(quantity) * product.getPrice();
        }));
        return output.toString();
    }

    private String totalPrice() {
        StringBuilder output = new StringBuilder();
        output.append("총구매액\t" + totalQuantity + "\t" + priceFormat(totalPrice) + "\r\n");
        return output.toString();
    }

    private String totalDiscount() {
        StringBuilder output = new StringBuilder();
        output.append("행사할인\t\t\t");
        output.append("-" + totalGiftPrice + "\r\n");
        return output.toString();
    }

    private String membershipDiscount() {
        StringBuilder output = new StringBuilder();
        output.append("멤버십할인\t\t\t-");
        output.append(priceFormat(calculateMembershipDiscount()));
        output.append("\r\n");
        return output.toString();
    }

    private String finalPrice() {
        StringBuilder output = new StringBuilder();
        output.append("내실돈\t\t\t ");
        output.append(priceFormat(totalPrice-totalGiftPrice-calculateMembershipDiscount()));
        output.append("\r\n");
        return output.toString();
    }

    private int calculateMembershipDiscount() {
        int price = totalPrice - totalPromotionPrice;
        System.out.println(totalPrice);
        System.out.println(totalPromotionPrice);
        int discount = price / 10 * 3;
        if (!membership) {
            return 0;
        }
        if (discount >= 8000) {
            return 8000;
        }
        return discount;
    }

    private String priceFormat(int price) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        return numberFormat.format(price);
    }

}
