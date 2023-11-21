import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class ReadFile {

    private static final String FILE_NAME = "Kennzeichen.txt";

    public String readFile(String kz) throws FileNotFoundException {
        URL filePath = ReadFile.class.getClassLoader().getResource(FILE_NAME);

        if (filePath == null) {
            return "Kennzeichen-Datei nicht gefunden!";
        }

        return searchInFile(new File(filePath.getFile()), kz);
    }

    private String searchInFile(File file, String searchKey) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.contains(searchKey)) {
                    return line.substring(3);
                }
            }
        }
        return "Kennzeichen nicht gefunden!";
    }
}
