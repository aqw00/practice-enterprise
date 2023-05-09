package Reading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadTxtFile
{
    public static List<String> txtFileHandeling(String fileName, boolean readOrWrite, String newValue)
    {
        List<String> myArray = new ArrayList<>();
        if(readOrWrite)
        {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String value = line.trim();
                    myArray.add(value);
                }
                return myArray;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return myArray;
        }
        else
        {
            try (FileWriter fw = new FileWriter(fileName, true)) {
                fw.write("\n" + newValue );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}