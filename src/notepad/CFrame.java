package notepad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * the main frame of the program
 * @author alireza
 *
 */
public class CFrame extends JFrame implements ActionListener {

    private CMainPanel mainPanel;

    private JMenuItem newItem;
    private JMenuItem saveItem;
    private JMenuItem exitItem;

    /**
     * creating a new CFrame
     * @param title title of the main window
     */
    public CFrame(String title) {
        super(title);

        initMenuBar(); //create menuBar

        initMainPanel(); //create main panel
    }

    /**
     * creating the menu of the program
     */
    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu jmenu = new JMenu("File");

        newItem = new JMenuItem("New");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        newItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        jmenu.add(newItem);
        jmenu.add(saveItem);
        jmenu.add(exitItem);

        menuBar.add(jmenu);
        setJMenuBar(menuBar);
    }
    
    /**
     * adding the main panel of the program to main window
     */
    private void initMainPanel() {
        mainPanel = new CMainPanel();
        add(mainPanel);
    }
    
    /**
     * event handler of the menu items
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newItem) {
            mainPanel.addNewTab();
        } else if (e.getSource() == saveItem) {
            mainPanel.saveNote();
        } else if (e.getSource() == exitItem) {
        	for(int i = 0; i < mainPanel.getTabbedPane().getTabCount(); i++){
        		mainPanel.saveNote(i);
        	}
            System.exit(0);
        } else {
            System.out.println("Nothing detected...");
        }
    }
    
}
