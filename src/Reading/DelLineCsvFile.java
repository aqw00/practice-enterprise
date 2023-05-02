package Reading;

import java.util.List;

import static Reading.ReadCsvFiles.readFile;
import static Reading.ReadTxtFile.txtFileHandeling;

public class DelLineCsvFile {
    public static void deleteLine(String fileWebInfo, String fileButton, String siteName )
    {
        List<String[]> listWebInfo = readFile(fileWebInfo);
        List<String> listButton = txtFileHandeling(fileButton, false, "");

        int indexWebInfo = 0;
        int indexButton = 0;

        for(int i = 0; i < (listWebInfo.size()); i++) {
            if (ReadCsvFiles.readFile(fileWebInfo).get(i)[0].equals(siteName)) {
                indexWebInfo = i;
            }
        }

        listWebInfo.remove(indexWebInfo);

        for(int j = 0; j < listButton.size(); j++)
        {
            if(listButton.get(j).equals(siteName))
            {
                indexButton = j;
            }
        }

        listButton.remove(indexButton);

        System.out.println(listWebInfo+"\n\n"+listButton);
    }
}
