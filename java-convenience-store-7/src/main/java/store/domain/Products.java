package store.domain;

import java.util.List;

public class Products {
    private final List<Product> products;
    public Products(List<Product> products){
        this.products = products;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for(Product product : products){
            output.append(product.toString());
        }
        return output.toString();
    }
}
