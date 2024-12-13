package store;

import store.common.StoreConfig;
import store.controller.StoreController;

public class Application {
    public static void main(String[] args) {
        StoreConfig config = new StoreConfig();
        StoreController storeController = config.provideController();
        storeController.sale();
    }
}
