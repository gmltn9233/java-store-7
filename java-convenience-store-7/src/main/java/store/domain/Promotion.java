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
    public Promotion(List<String> input){
        this.name = input.get(0);
        this.buy = Integer.parseInt(input.get(1));
        this.get= Integer.parseInt(input.get(2));
        this.startDate = LocalDate.parse(input.get(3));
        this.endDate = LocalDate.parse(input.get(4));
    }

    public String getName() {
        return name;
    }
}
