package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.domain.dto.OrderRequest;

public class InputView {
    public static OrderRequest orderRequest(){
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        return new OrderRequest(Console.readLine());
    }

}
