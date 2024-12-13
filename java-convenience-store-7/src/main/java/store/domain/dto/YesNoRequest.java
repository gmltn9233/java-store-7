package store.domain.dto;

import store.exception.StoreException;

public class YesNoRequest {
    private final boolean response;

    public YesNoRequest(String response) {
        this.response = toBoolean(response);
    }

    public boolean getResponse() {
        return response;
    }

    private boolean toBoolean(String response) {
        if (response.equals("Y")) {
            return true;
        }
        if (response.equals("N")) {
            return false;
        }
        throw new StoreException("응답은 Y/N 으로 해주세요.");
    }
}
