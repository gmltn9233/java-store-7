package store.common;

import store.controller.StoreController;
import store.domain.Products;
import store.domain.Promotions;
import store.service.StoreService;

public class StoreConfig {
    public StoreController provideController() {
        FileReader fileReader = new FileReader();
        Loader loader = new Loader(fileReader);
        Promotions promotions = loader.loadPromotions();
        Products products = loader.loadProducts(promotions);
        StoreService storeService = new StoreService(products, promotions);
        return new StoreController(storeService);
    }
}
