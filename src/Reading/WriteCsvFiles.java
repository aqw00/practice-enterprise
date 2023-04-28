package Reading;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteCsvFiles {
    public static void writeCsv(String fileName, List<String> list)
    {
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.write("\n");
            for(int i = 0; i < list.size() ; i++)
            {
                fw.write(list.get(i) + ";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
