package store.domain;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Pattern;
import store.exception.StoreException;
import store.view.InputView;

public class Product {
    private static final String VALID_NUMBER_REGEX = "\\d+";

    private final String name;
    private final int price;
    private final Promotion promotion;
    private int quantity;

    public Product(String name, String price, String quantity, Promotion promotion) {
        validNumber(price);
        validNumber(quantity);
        this.name = name;
        this.price = Integer.parseInt(price);
        this.quantity = Integer.parseInt(quantity);
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void purchase(int quantity) {
        valid(quantity);
        this.quantity -= quantity;
    }

    public int getMaxPromotion(int quantity) {
        int gift = 0;
        if (!validQuantity(quantity)) {
            gift  = promotion.getMaxPurchase(this.quantity);
            return gift + freePromotionCheck(quantity);
        }
        gift = promotion.getMaxPurchase(quantity);
        return gift + freePromotionCheck(quantity);
    }

    private int freePromotionCheck(int quantity){
        if(promotion.freeGiftCheck(quantity) && this.quantity >= quantity + promotion.getGet()){
            if(InputView.freePromotionCheck(name,promotion.getGet()).getResponse()){
                this.quantity -= promotion.getGet();
                return promotion.getGet();
            }
        }
        return 0;
    }


    private boolean validNumber(String input) {
        return Pattern.matches(VALID_NUMBER_REGEX, input);
    }

    private void valid(int quantity){
        if(!validQuantity(quantity)){
            throw new StoreException("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        }
    }
    private boolean validQuantity(int quantity) {
        return this.quantity >= quantity;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("- " + name + " " + priceFormat() + "원 ");
        String quantityToString = quantity+"개 ";
        if(quantity == 0){
            quantityToString = "재고 없음";
        }
        output.append(quantityToString);
        if (promotion != null) {
            output.append(promotion.getName());
        }
        output.append("\r\n");
        return output.toString();
    }

    private String priceFormat() {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        return numberFormat.format(price);
    }
}
