package store.common;

public enum FilePath {
    PRODUCTS("src/main/resources/products.md"),
    PROMOTIONS("src/main/resources/promotions.md");
    private final String filePath;

    FilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
