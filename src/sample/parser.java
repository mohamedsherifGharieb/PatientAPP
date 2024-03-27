package sample;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class parser
{

    public static void main(String[]args) throws FileNotFoundException, IOException {

        try {
            BufferedReader br = new BufferedReader(new FileReader("startMenuApps.txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
  		    Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "utf-8"));

            while (line != null) {
            	if (line.length()>4){
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            	}

            }
            String everything = sb.toString();
            writer.write(everything);
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader("fileTxt.txt"))) {
            while ((line = br.readLine()) != null) {
           	 	System.out.println(line);
           	 	System.out.println(Arrays.deepToString(line.split(Matcher.quoteReplacement(System.getProperty("file.separator")))));
            }
        }
 

    }

    public static void parseToLnk(String inputText) throws FileNotFoundException, UnsupportedEncodingException {

        String pattern = "(.*\\.lnk|.*\\.exe)";
        Pattern pat = Pattern.compile(pattern);
        Matcher m = pat.matcher(inputText);
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
        while(m.find()) {
            if(m.group(0).length() > 4)
                writer.println(m.group(0));
        }

        writer.close();

    }

}
