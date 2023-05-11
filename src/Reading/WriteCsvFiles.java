package Reading;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteCsvFiles
{
    public static void writeCsv(String fileName, List<String> list, boolean yAppend)
    {
        try (FileWriter fw = new FileWriter(fileName, yAppend))
        {
            if(yAppend)
                fw.write("\n");

            for(int i = 0; i < list.size() ; i++)
            {
                if(i %7 == 0 && i != 0)
                    fw.write("\n");

                fw.write(list.get(i) + ";");
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}