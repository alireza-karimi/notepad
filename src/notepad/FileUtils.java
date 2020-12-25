package notepad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

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
     * reading content from file using bufferedReader
     * @param file file object
     * @return content of the file
     */
    public static String fileReader(File file) {
        
    	String result = "";
    	
    	BufferedReader reader = null;

    	try {
    	    reader = new BufferedReader(new FileReader(file));
    	    String newLine;
    	    while ((newLine = reader.readLine()) != null) {
    	        result = result.concat(newLine);
    	    }

    	} catch (IOException e) {
    	    e.printStackTrace();
    	} finally {
    	    try {
    	        reader.close();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	}
    	
        return result;
    }

    /**
     * writing content on file using bufferedWriter
     * @param content
     */
    public static void fileWriter(String content) {

        String fileName = getProperFileName(content);
        BufferedWriter writer = null;
        try
        {
            File file = new File(fileName);
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.newLine();    
        }
        catch(IOException e)
        {
        	e.printStackTrace();
        }
        finally{
        	try{
        		writer.flush();
            	writer.close();	
        	}
        	catch(IOException e){
        		e.printStackTrace();	
        	}     	
        }
    }

    /**
     * reading file using inputstream
     * @param file file
     * @return string containing file content
     */
    public static String fileReaderInputStream(File file){
        
    	String result = "";
    	
    	BufferedReader reader = null;

    	try {
    	    reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    	    String newLine;
    	    while ((newLine = reader.readLine()) != null) {
    	        result = result.concat(newLine);
    	    }

    	} catch (IOException e) {
    	    e.printStackTrace();
    	} finally {
    	    try {
    	        reader.close();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	}
    	
        return result;
    }
    
    /**
     * writing on file using outputstream
     * @param content content of file
     */
    public static void fileWriterOutputStream(String content){
    	
        String fileName = getProperFileName(content);
        BufferedWriter writer = null;
        try
        {
            File file = new File(fileName);
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.write(content);
            writer.newLine();    
        }
        catch(IOException e)
        {
        	e.printStackTrace();
        }
        finally{
        	try{
        		writer.flush();
            	writer.close();	
        	}
        	catch(IOException e){
        		e.printStackTrace();	
        	}     	
        }
    }
    
    
    /**
     * reading a file containing note object
     * @param file file containing note object
     * @return content of note
     */
    public static String fileReaderObj(File file){
    	
    	try (FileInputStream fs = new FileInputStream(file)){

			ObjectInputStream os = new ObjectInputStream(fs);

			Note note = (Note) os.readObject();
			
			return note.getContent();

    	} catch (Exception e) { 
    		e.printStackTrace();
    	}
    	
    	return "";
    }
    
    /**
     * writing note object on a file
     * @param note
     */
    public static void fileWriterObj(Note note){
    	
    	String fileName = NOTES_PATH + note.toString();
    	System.out.println(fileName);
    	try (FileOutputStream fs = new FileOutputStream(fileName)){

    			ObjectOutputStream os = new ObjectOutputStream(fs);

    			os.writeObject(note);

    	} catch (Exception e) { 
    		e.printStackTrace();
    	}
    }
    

    /**
     * getting a good name for the content
     * @param content
     * @return name of content
     */
    private static String getProperFileName(String content) {
        int loc = content.indexOf("\n");
        if (loc != -1) {
            return NOTES_PATH + content.substring(0, loc) + "_new file.txt";
        }
        if (!content.isEmpty()) {
            return NOTES_PATH + content + "_new file.txt";
        }
        return NOTES_PATH + System.currentTimeMillis() + "_new file.txt";
    }
	
}
