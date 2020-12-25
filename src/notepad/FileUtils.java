package notepad;

import java.io.File;

/**
 * handling the files part of the program
 * @author alireza
 *
 */
public class FileUtils {
	
    private static final String NOTES_PATH = "./notes/";

    //It's a static initializer. It's executed when the class is loaded.
    //It's similar to constructor
    static {
        boolean isSuccessful = new File(NOTES_PATH).mkdirs();
        System.out.println("Creating " + NOTES_PATH + " directory is successful: " + isSuccessful);
    }

    /**
     * getting all files in the notes directory
     * @return array of files in the directory
     */
    public static File[] getFilesInDirectory() {
        return new File(NOTES_PATH).listFiles();
    }

    /**
     * reading content from file
     * @param file file object
     * @return content of the file
     */
    public static String fileReader(File file) {
        //TODO: Phase1: read content from file
        return "";
    }

    /**
     * writing content on file
     * @param content
     */
    public static void fileWriter(String content) {
        //TODO: write content on file
        String fileName = getProperFileName(content);
    }

    //TODO: Phase1: define method here for reading file with InputStream
    //TODO: Phase1: define method here for writing file with OutputStream

    //TODO: Phase2: proper methods for handling serialization

    /**
     * getting a good name for the content
     * @param content
     * @return name of content
     */
    private static String getProperFileName(String content) {
        int loc = content.indexOf("\n");
        if (loc != -1) {
            return content.substring(0, loc);
        }
        if (!content.isEmpty()) {
            return content;
        }
        return System.currentTimeMillis() + "_new file.txt";
    }
	
}
