package store.service;

import java.util.LinkedHashMap;
import java.util.Map;
import store.domain.Product;
import store.domain.Products;
import store.domain.Promotions;
import store.domain.dto.OrderRequest;
import store.domain.dto.OrderResponse;

public class StoreService {
    private final Products products;
    private final Promotions promotions;

    public StoreService(Products products, Promotions promotions) {
        this.products = products;
        this.promotions = promotions;
    }

    public OrderResponse order(OrderRequest orderRequest) {
        Map<Product, Integer> orders = convertProduct(orderRequest);
        Map<Product, Integer> gifts = products.purchaseProducts(orderRequest);
        return new OrderResponse(orders, gifts);
    }

    private Map<Product, Integer> convertProduct(OrderRequest orderRequest) {
        Map<String, Integer> input = orderRequest.orderRequest;
        Map<Product, Integer> orders = new LinkedHashMap<>();
        input.forEach(((name, quantity) -> {
            Product product = products.findByName(String.valueOf(name));
            orders.put(product, quantity);
        }));
        return orders;
    }


    public String getProducts() {
        return products.toString();
    }
}
