import java.util.*;
import java.io.*;

public class Exercise21_03 {

    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            System.out.println("Usage: java Exercise21_03 filename.java");
            System.exit(1);
        }

        File file = new File(args[0]);

        if (!file.exists()) {
            System.out.println("File does not exist.");
            System.exit(2);
        }

        Set<String> keywords = new HashSet<>(Arrays.asList(
            "abstract","assert","boolean","break","byte","case","catch",
            "char","class","const","continue","default","do","double",
            "else","enum","extends","final","finally","float","for",
            "goto","if","implements","import","instanceof","int",
            "interface","long","native","new","package","private",
            "protected","public","return","short","static","strictfp",
            "super","switch","synchronized","this","throw","throws",
            "transient","try","void","volatile","while"
        ));

        Scanner input = new Scanner(file);

        boolean inBlockComment = false;
        int count = 0;

        System.out.println("The program is");

        while (input.hasNextLine()) {

            String line = input.nextLine();
            System.out.println(line);

            String processed = line;

            if (inBlockComment) {
                if (processed.contains("*/")) {
                    inBlockComment = false;
                    processed = processed.substring(processed.indexOf("*/") + 2);
                } else {
                    continue;
                }
            }

            if (processed.contains("/*")) {
                inBlockComment = true;
                processed = processed.substring(0, processed.indexOf("/*"));
            }

            if (processed.contains("//")) {
                processed = processed.substring(0, processed.indexOf("//"));
            }

            processed = processed.replaceAll("\".*?\"", " ");

            String[] words = processed.split("\\W+");

            for (String word : words) {
                if (keywords.contains(word)) {
                    count++;
                }
            }
        }

        input.close();

        System.out.println("The number of keywords in the program is " + count);
    }
}