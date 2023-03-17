package Reading;

public class ReadBrowser {
    public static String chooseBrowser (String button)
    {
        for(int i = 0 ; i < ReadPasswordFile.readFile().size() ; i++)
        {
            String test = ReadPasswordFile.readFile().get(i)[2];
            if(test.equals(button))
            {
                String test1 = ReadPasswordFile.readFile().get(i)[0];
                String test2 = ReadPasswordFile.readFile().get(i)[1];
                String test3 = test1 + " " + test2;
                return test3;
            }


        }

        return "Not working pal";
    }

}
