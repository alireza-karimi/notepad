package notepad;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * the main panel of the program which will be placed inside the main window
 * @author alireza
 *
 */
public class CMainPanel extends JPanel {
	
    private JTabbedPane tabbedPane;
    private JList<File> directoryList;

    /**
     * creating a brand new main panel
     */
    public CMainPanel() {

        setLayout(new BorderLayout());

        initDirectoryList(); // add JList to main Panel

        initTabbedPane(); // add TabbedPane to main panel

        addNewTab(); // open new empty tab when user open the application
    }
    
    /**
     * getting the tabbed pane
     * @return the tabbed pane
     */
    public JTabbedPane getTabbedPane(){
    	return tabbedPane;
    }

    /**
     * adding tabbedpane to panel
     */
    private void initTabbedPane() {
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    /**
     * adding directory list to panel
     */
    private void initDirectoryList() {
        File[] files = FileUtils.getFilesInDirectory();
        directoryList = new JList<>(files);

        directoryList.setBackground(new Color(211, 211, 211));
        directoryList.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        directoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        directoryList.setVisibleRowCount(-1);
        directoryList.setMinimumSize(new Dimension(130, 100));
        directoryList.setMaximumSize(new Dimension(130, 100));
        directoryList.setFixedCellWidth(130);
        directoryList.setCellRenderer(new MyCellRenderer());
        directoryList.addMouseListener(new MyMouseAdapter());

        add(new JScrollPane(directoryList), BorderLayout.WEST);
    }

    /**
     * open new empty tab when user open the application
     */
    public void addNewTab() {
        JTextArea textPanel = createTextPanel();
        textPanel.setText("Write Something here...");
        tabbedPane.addTab("Tab " + (tabbedPane.getTabCount() + 1), textPanel);
    }

    /**
     * opening a new tab with specified content
     * @param content
     */
    public void openExistingNote(String content) {
        JTextArea existPanel = createTextPanel();
        existPanel.setText(content);

        int tabIndex = tabbedPane.getTabCount() + 1;
        tabbedPane.addTab("Tab " + tabIndex, existPanel);
        tabbedPane.setSelectedIndex(tabIndex - 1);
    }
    
    /**
     * saving a note
     */
    public void saveNote() {
        JTextArea textPanel = (JTextArea) tabbedPane.getSelectedComponent();
        String content = textPanel.getText();
        String title = "Tab " + (tabbedPane.getTabCount());
        if (!content.isEmpty()) {
        	Note note = new Note(title, content, String.valueOf(java.time.LocalDate.now()));
            FileUtils.fileWriterObj(note);
        }
        updateListGUI();
    }
    
    /**
     * saving a note
     * @param index index of tab
     */
    public void saveNote(int index) {
        JTextArea textPanel = (JTextArea) tabbedPane.getComponentAt(index);
        String content = textPanel.getText();
        String title = "something";
        if (!content.isEmpty()) {
        	Note note = new Note(title, content, String.valueOf(System.currentTimeMillis()));
            FileUtils.fileWriterObj(note);
        }
        updateListGUI();
    }

    /**
     * creating a new text panel
     * @return text panel
     */
    private JTextArea createTextPanel() {
        JTextArea textPanel = new JTextArea();
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return textPanel;
    }

    /**
     * updating the directory list
     */
    private void updateListGUI() {
        File[] newFiles = FileUtils.getFilesInDirectory();
        directoryList.setListData(newFiles);
    }

    
    /**
     * handling double click on a file in directory list
     * @author alireza
     *
     */
    private class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent eve) {
            // Double-click detected
            if (eve.getClickCount() == 2) {
                int index = directoryList.locationToIndex(eve.getPoint());
                System.out.println("Item " + index + " is clicked...");
                File curr[] = FileUtils.getFilesInDirectory();
                String content = FileUtils.fileReaderObj(curr[index]);
                openExistingNote(content);
            }
        }
    }


    /**
     * directory list renderer
     * @author alireza
     *
     */
    private class MyCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object object, int index, boolean isSelected, boolean cellHasFocus) {
            if (object instanceof File) {
                File file = (File) object;
                setText(file.getName());
                setIcon(FileSystemView.getFileSystemView().getSystemIcon(file));
                if (isSelected) {
                    setBackground(list.getSelectionBackground());
                    setForeground(list.getSelectionForeground());
                } else {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }
                setEnabled(list.isEnabled());
            }
            return this;
        }
    }
    
}
