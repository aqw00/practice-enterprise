package Reading;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCsvFiles
{
    public static List<String[]> readFile(String fileName)
    {
        String line = "";
        String splitBy = ";";

        List<String> temps = new ArrayList<>();
        List<String[]> loginCredentials = new ArrayList<>();

        try
        {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null)
            {
                temps.add(line);
            }

            for (String str:temps)
            {
               loginCredentials.add(str.split(splitBy));
            }
            br.close();

            return loginCredentials;
        }

        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
            System.out.println(ex);
        }

        catch (IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }
}