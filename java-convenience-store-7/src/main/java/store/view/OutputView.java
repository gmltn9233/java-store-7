package store.view;

public class OutputView {
    public static void printProducts(String products) {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.\r\n");
        System.out.println(products);
    }

    public static void print(String output) {
        System.out.println(output);
    }
}
