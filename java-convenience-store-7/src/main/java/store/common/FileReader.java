package store.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    public List<String> readFile(FilePath filePath) {
        List<String> tokens = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filePath.getFilePath()));
            scanner.next();
            while (scanner.hasNext()) {
                String token = scanner.next();
                tokens.add(token);
            }
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] 파일 경로가 올바르지 않습니다.");
        }
        return tokens;
    }
}
