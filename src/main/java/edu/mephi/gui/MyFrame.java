package edu.mephi.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import edu.mephi.gui.frames.CreateFrame;

public class MyFrame extends JFrame {
    private static final int HEIGHT = 380;
    private static final int WIDTH = 600;
    private JTable  autoTable;
    private JPanel  panel;
    private JScrollPane autoScrollTable;
    private AutoTableModel  modelTable;
    private JMenuBar        menuBar;
    private JMenu           menu1;
    private JMenu           menu2;
    private JMenuItem       create;
    private JMenuItem       delete;
    private JMenuItem       save;
    private JMenuItem       change;
    private JMenuItem       quit;
    private boolean         editFlag;

    public MyFrame(String name) {
        super(name);
        editFlag = false;
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        

        

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        modelTable = new AutoTableModel();
        autoTable = new JTable(modelTable);
        autoScrollTable = new JScrollPane(autoTable);
        autoScrollTable.setPreferredSize(new Dimension(300, 300));

        menuBar = new JMenuBar();
        menu1 = new JMenu("File");
        menu2 = new JMenu("Edit");
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.setPreferredSize(new Dimension(100, 20));
        create = new JMenuItem("Create");
        delete = new JMenuItem("Delete");
        save = new JMenuItem("Save");
        change = new JMenuItem("Change");
        quit = new JMenuItem("Quit");
        menu1.add(create);
        menu1.add(delete);
        menu1.add(save);
        menu2.add(change);
        menu2.add(quit);
        create.addActionListener(new confirmButtonActionListener());
        delete.addActionListener(new deleteActionListener());
        save.addActionListener(new saveActionListener());

        this.add(menuBar, BorderLayout.NORTH);
        panel.add(autoScrollTable, BorderLayout.CENTER);
        this.add(panel, BorderLayout.CENTER);
    }

    private class confirmButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

            if (editFlag)
                return;
            editFlag = true;
            CreateFrame frame = new CreateFrame("Add");
            frame.setMainframe(MyFrame.this);
            frame.setVisible(true);

		}
    }

    private class deleteActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
            int index = autoTable.getSelectedRow();
            if (index == -1)
                return;
            modelTable.deleteRow(index);
		}
    }
    
    private class saveActionListener implements ActionListener {
        private final String fileName = "fromtable.txt";

		@Override
		public void actionPerformed(ActionEvent e) {
            FileOutputStream outputStream;
            File             file;
            try {
                file = new File(fileName);
                file.createNewFile();
                outputStream = new FileOutputStream(file);
                for(int i = 0; i < modelTable.getRowCount(); i++) {
                    for(int j = 0; j < modelTable.getColumnCount(); j++) {
                        outputStream.write(" [ ".getBytes());
                        outputStream.write(((String) modelTable.getValueAt(i, j)).getBytes());
                        outputStream.write(" ] ".getBytes());
                        System.out.println(i + " " + j);
                    }
                    outputStream.write("\n".getBytes());
                }
                outputStream.close();
            JOptionPane.showMessageDialog(MyFrame.this, "File was saved", "ok", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception exc) {
                System.out.println("Sorry");
                exc.printStackTrace();
            }
		}
    }

    public void setEditFlag(boolean flag) {
        this.editFlag = flag;
    }

    public AutoTableModel   getModelTable() {
        return this.modelTable;
    }
}
