package store.domain;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.util.List;

public class Promotion {
    private final String name;
    private final int buy;
    private final int get;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(List<String> input) {
        this.name = input.get(0);
        this.buy = Integer.parseInt(input.get(1));
        this.get = Integer.parseInt(input.get(2));
        this.startDate = LocalDate.parse(input.get(3));
        this.endDate = LocalDate.parse(input.get(4));
    }

    public boolean isInTime() {
        LocalDate now = DateTimes.now().toLocalDate();
        return (!now.isBefore(startDate) && !now.isAfter(endDate));
    }

    public int getGet() {
        return get;
    }

    public int getBuy(int quantity) {
        int count = quantity / get;
        return count * buy + quantity;
    }

    public int getMaxPurchase(int quantity) {
        if (quantity < buy + get) {
            return quantity;
        }
        return quantity - (quantity % (buy + get));
    }

    public boolean freeGiftCheck(int quantity) {
        int noPromotion = quantity % (buy + get);
        if (noPromotion != 0 && noPromotion % buy == 0) {
            return true;
        }
        return false;
    }

    public int getGiftCount(int quantity) {
        return quantity / (buy + get);
    }

    public String getName() {
        return name;
    }
}
