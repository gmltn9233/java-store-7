package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.domain.Product;
import store.domain.dto.OrderRequest;
import store.domain.dto.YesNoRequest;

public class InputView {
    public static OrderRequest orderRequest(){
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        return new OrderRequest(Console.readLine());
    }

    public static YesNoRequest noPromotionCheck(Product product, int gift){
        System.out.println("현재 "+product.getName()+" "+gift+"개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)");
        return yesNoRequest();
    }

    public static YesNoRequest freePromotionCheck(String name, int gift){
        System.out.println("현재 "+name+"은(는) "+gift+"개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)");
        return yesNoRequest();
    }

    public static YesNoRequest membershipCheck(){
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
        return yesNoRequest();
    }

    public static YesNoRequest repurchaseCheck(){
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
        return yesNoRequest();
    }

    private static YesNoRequest yesNoRequest(){
        return new YesNoRequest(Console.readLine());
    }

}
