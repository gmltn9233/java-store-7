package store.domain.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import store.exception.StoreException;

public class OrderRequest {
    private static final String VALID_NUMBER_REGEX = "\\d+";
    public final Map<String, Integer> orderRequest;

    public OrderRequest(String input) {
        Map<String, Integer> orderRequest = new LinkedHashMap<>();
        List<String> orders = parse(input, ",");
        for (String order : orders) {
            order = order.replaceAll("\\[|\\]", "");
            List<String> tokens = parse(order, "-");
            valid(tokens);
            orderRequest.put(tokens.get(0), Integer.parseInt(tokens.get(1)));
        }
        this.orderRequest = orderRequest;
    }

    private List<String> parse(String input, String parser) {
        List<String> orders = new ArrayList<>();
        String[] tokens = input.split(parser);
        Collections.addAll(orders, tokens);
        return orders;
    }

    private void valid(List<String> input) {
        if (!isValidSize(input)) {
            throw new StoreException("주문 형식이 어긋납니다.");
        }
        if (!isDigit(input.get(1))) {
            throw new StoreException("주문 갯수는 숫자 형식이여야 합니다.");
        }
    }

    private boolean isValidSize(List<String> input) {
        return input.size() == 2;
    }

    private boolean isDigit(String input) {
        return Pattern.matches(VALID_NUMBER_REGEX, input);
    }

}
