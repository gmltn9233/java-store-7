package store.controller;

import store.domain.Products;
import store.domain.Promotions;
import store.service.StoreService;
import store.view.OutputView;

public class StoreController {
    private final Products products;
    private final Promotions promotions;
    private final StoreService storeService;
    public StoreController(Products products, Promotions promotions, StoreService storeService){
        this.products = products;
        this.promotions = promotions;
        this.storeService = storeService;
    }

    public void sale(){
        OutputView.printProducts(products);
    }


}
