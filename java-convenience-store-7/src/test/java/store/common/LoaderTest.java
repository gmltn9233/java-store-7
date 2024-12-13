package store.common;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.domain.Product;
import store.domain.Products;

public class LoaderTest {

    private Loader loader;

    @BeforeEach
    public void setLoader(){
        FileReader fileReader = new FileReader();
        this.loader = new Loader(fileReader);
    }

    @Test
    public void 상품목록을_읽어온다(){
        Products products = loader.loadProducts();
        System.out.println(products.toString());
    }

    @Test
    public void 프로모션목록을_읽어온다(){

    }
}
