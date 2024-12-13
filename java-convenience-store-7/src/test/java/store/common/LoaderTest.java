package store.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.domain.Products;
import store.domain.Promotions;

public class LoaderTest {

    private Loader loader;

    @BeforeEach
    public void setLoader() {
        FileReader fileReader = new FileReader();
        this.loader = new Loader(fileReader);
    }

    @Test
    public void 상품목록을_읽어온다() {
        Promotions promotions = loader.loadPromotions();
        Products products = loader.loadProducts(promotions);
        System.out.println(products.toString());
    }

    @Test
    public void 프로모션목록을_읽어온다() {

    }
}
