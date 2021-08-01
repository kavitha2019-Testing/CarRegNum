import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFiles {

    private Scanner scan;
    private static String regexp = "(?![\\£0-9]+)([A-Z0-9][A-Z 0-9]+[A-Z0-9])";
    public static List<String> regexInputList = new ArrayList<>();

    /**
     * Opens File
     * @param fileName
     */
    private void openFile(String fileName) {
        try {
            scan = new Scanner(new File(System.getProperty("user.dir") + "/files/" + fileName));
        } catch (Exception e) {
            System.out.println(" *** File not found *** ");
        }
    }

    /**
     * Read Input File and add all Car REGISTRATION numbers to list if it matches to RegExp Pattern
     */
    public void readInputFile() {
        // Open File
        openFile("car_input.txt");

        // Get each line from input file and match with RegEx pattern. If any matches, add them to them List
        Pattern pt = Pattern.compile(regexp);
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            Matcher m = pt.matcher(line);
            while (m.find()) {
                regexInputList.add(m.group());
            }
        }
        System.out.println(" *** Car Reg numbers matches with Pattern in the Input File *** ");
        regexInputList.forEach(System.out::println);
        closeFile();
    }

    /**
     * Reads Output File
     */
    public boolean readOutputFile(String carAttrFromSite) {
        boolean check = false;
        openFile("car_output.txt");
        String line = null;
        while(scan.hasNextLine()) {
            line = scan.nextLine(); // Gets each line from text file
            if (carAttrFromSite.equalsIgnoreCase(line)) {
                check = true;
                break;
            }
        }
        return check;
    }

    public void closeFile() {
        // Closes file
        scan.close();
    }
}
