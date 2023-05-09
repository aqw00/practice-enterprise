package Reading;

import java.util.ArrayList;
import java.util.List;

import static Reading.ReadCsvFiles.readFile;

public class DelLineCsvFile {
    public static void deleteLine(String pathWebsiteInfo, String pathButtonWebsites, String siteName, String email, String password )
    {
        List<String[]> listWebInfo = readFile(pathWebsiteInfo);
        List<String> list = new ArrayList<>(List.of("Website", "url", "login_email", "login_password", "email_location", "password_location", "login_button"));

        int i;
        int j;
        int indexWebInfo = 0;

        for(i = 0; i < (listWebInfo.size()); i++) {
            if (ReadCsvFiles.readFile(pathWebsiteInfo).get(i)[0].equals(siteName)) {
                indexWebInfo = i;
                for(j = 0 ; j < 7; j++)
                {
                    if(j == 2)
                    {
                        list.add(email);
                    } else if (j == 3)
                    {
                        list.add(password);
                    } else
                    {
                        list.add(listWebInfo.get(i)[j]);
                    }
                }
            }
        }

        listWebInfo.remove(indexWebInfo);

        for(i = 0; i < (listWebInfo.size()-1); i++)
        {
            for(j = 0 ; j < 7; j++)
            {
                list.add(listWebInfo.get(i+1)[j]);
            }
        }

        WriteCsvFiles.writeCsv(pathWebsiteInfo, list, false);
    }
}