package store.domain;

import java.util.List;

public class Promotions {
    private final List<Promotion> promotions;

    public Promotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public Promotion getPromotionByName(String name) {
        for (Promotion promotion : promotions) {
            if (promotion.getName().equals(name)) {
                return promotion;
            }
        }
        return null;
    }

}
