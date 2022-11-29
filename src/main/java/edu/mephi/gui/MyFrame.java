package edu.mephi.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MyFrame extends JFrame {
    private static final int HEIGHT = 600;
    private static final int WIDTH = 900;
    private JTable  autoTable;
    private JScrollPane autoScrollTable;
    private AutoTableModel  modelTable;

    public MyFrame(String name) {
        super(name);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        

        modelTable = new AutoTableModel();
        autoTable = new JTable(modelTable);
        autoScrollTable = new JScrollPane(autoTable);
        autoScrollTable.setPreferredSize(new Dimension(600, 600));

        this.add(autoScrollTable, BorderLayout.CENTER);
    }
}
