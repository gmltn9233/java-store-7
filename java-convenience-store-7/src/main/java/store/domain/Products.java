package store.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import store.domain.dto.OrderRequest;
import store.exception.StoreException;
import store.view.InputView;

public class Products {
    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    public Map<Product, Integer> purchaseProducts(OrderRequest orderRequest) {
        Map<String, Integer> orders = orderRequest.orderRequest;
        Map<Product, Integer> gifts = new LinkedHashMap<>();
        orders.forEach(((name, quantity) -> {
            // 해당 상품이 할인기간
            if (isInTime(name)) {
                int gift = purchasePromotionAndRegular(name, quantity);
                if (gift != 0) {
                    gifts.put(findPromotionProduct(name), gift);
                }
                return;
            }
            purchaseRegular(name, quantity);
        }));
        return gifts;
    }

    private boolean isInTime(String name) {
        Product product = findByName(name);
        if (product.getPromotion() == null) {
            return false;
        }
        Promotion promotion = product.getPromotion();
        return promotion.isInTime();
    }

    public Product findByName(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        throw new StoreException("해당하는 상품을 찾을 수 없습니다.");
    }

    private List<Product> findAllByName(String name) {
        List<Product> matchingProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().equals(name)) {
                matchingProducts.add(product);
            }
        }
        if (matchingProducts.size() == 0) {
            throw new StoreException("해당하는 상품을 찾을 수 없습니다.");
        }
        return matchingProducts;
    }

    private Product findRegularProduct(String name) {
        List<Product> products = findAllByName(name);
        for (Product product : products) {
            if (product.getPromotion() == null) {
                return product;
            }
        }
        throw new StoreException("일반 상품이 존재하지 않습니다.");
    }

    private Product findPromotionProduct(String name) {
        List<Product> products = findAllByName(name);
        for (Product product : products) {
            if (product.getPromotion() != null) {
                return product;
            }
        }
        throw new StoreException("행사 상품이 존재하지 않습니다.");
    }

    private int purchasePromotionAndRegular(String name, int quantity) {
        Product promotionProduct = findPromotionProduct(name);
        Product regularProduct = findRegularProduct(name);
        int maxPromotionQuantity = promotionProduct.getMaxPromotion(quantity);
        if (quantity - maxPromotionQuantity > 0) {
            if (InputView.noPromotionCheck(regularProduct, quantity - maxPromotionQuantity).getResponse()) {
                return 0;
            }
        }
        promotionProduct.purchase(maxPromotionQuantity);
        regularProduct.purchase(quantity - maxPromotionQuantity);

        return promotionProduct.getPromotion().getGiftCount(maxPromotionQuantity);
    }

    private void purchaseRegular(String name, int quantity) {
        Product regularProduct = findRegularProduct(name);
        regularProduct.purchase(quantity);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Product product : products) {
            output.append(product.toString());
        }
        return output.toString();
    }
}
