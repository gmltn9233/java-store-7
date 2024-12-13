package store.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import store.domain.Product;
import store.domain.Products;
import store.domain.Promotion;
import store.domain.Promotions;

public class Loader {
    private final FileReader fileReader;
    public Loader(FileReader fileReader){
        this.fileReader = fileReader;
    }
    public Promotions loadPromotions(){
        List<Promotion> promotions = new ArrayList<>();
        FilePath filePath = FilePath.PROMOTIONS;
        List<String> tokens = fileReader.readFile(filePath);
        for(String token : tokens){
            List<String> promotion = parseByComma(token);
            promotions.add(new Promotion(promotion));
        }
        return new Promotions(promotions);
    }

    public Products loadProducts(){
        List<Product> products = new ArrayList<>();
        FilePath filePath = FilePath.PRODUCTS;
        List<String> tokens = fileReader.readFile(filePath);
        for(String token : tokens){
            List<String> product = parseByComma(token);
            products.add(new Product(product));
        }
        return new Products(products);
    }

    private List<String> parseByComma(String token){
        String[] tokens = token.split(",");
        List<String> output = new ArrayList<>();
        Collections.addAll(output,tokens);
        return output;
    }





}
