package notepad;

import javax.swing.*;

/**
 * main class of the program
 * @author alireza
 *
 */
public class Main {

	/**
	 * main method
	 * @param args
	 */
    public static void main(String[] args) {
    	
        CFrame frame = new CFrame("iNote");
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    
}
