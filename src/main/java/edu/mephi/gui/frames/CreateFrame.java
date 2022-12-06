package edu.mephi.gui.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.mephi.exceptions.NullStrException;
import edu.mephi.gui.AutoTableModel;
import edu.mephi.gui.MyFrame;

public class CreateFrame extends JFrame {
    private static final int HEIGHT = 100;
    private static final int WIDTH = 400;
    private JLabel  markLabel;
    private JLabel  modelLabel;
    private JLabel  yearLabel;
    private JLabel  priceLabel;
    private JTextField  markField;
    private JTextField  modelField;
    private JTextField  yearField;
    private JTextField  priceField;
    private JPanel  panel;
    private JButton     confirmButton;
    private MyFrame     mainFrame;
    private JLabel      errLabel;

    public CreateFrame(String name) {
        super(name);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 4));
        markLabel = new JLabel("Марка");
        modelLabel = new JLabel("Модель");
        yearLabel = new JLabel("Год");
        priceLabel = new JLabel("Цена");
        markField = new JTextField();
        modelField = new JTextField();
        yearField = new JTextField();
        priceField = new JTextField();
        errLabel = new JLabel("");
        errLabel.setForeground(Color.RED);
        confirmButton = new JButton("Добавить");
        panel.add(markLabel);
        panel.add(modelLabel);
        panel.add(yearLabel);
        panel.add(priceLabel);
        panel.add(markField);
        panel.add(modelField);
        panel.add(yearField);
        panel.add(priceField);
        confirmButton.addActionListener(new confirmButtonActionListener());

        this.add(panel, BorderLayout.CENTER);
        this.add(confirmButton, BorderLayout.SOUTH);
        this.add(errLabel, BorderLayout.NORTH);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                mainFrame.setEditFlag(false);
                CreateFrame.this.dispose();
            }
        });

    }

    public void setMainframe(MyFrame frame) {
        this.mainFrame = frame;
    }

    private class confirmButtonActionListener implements ActionListener {

        private void performEndTask() {
            mainFrame.setEditFlag(false);
            CreateFrame.this.dispose();
        }

		@Override
		public void actionPerformed(ActionEvent e) {
            String[] newRow = new String[4];

            newRow[0] = markField.getText();
            newRow[1] = modelField.getText();
            newRow[2] = yearField.getText();
            newRow[3] = priceField.getText();
            try {
                for (int i = 0; i < newRow.length; i++) {
                    if (newRow[i].length() == 0)
                        throw new NullStrException("Text field can't be empty!");
                }
                Integer.parseInt(newRow[2]);
                Double.parseDouble(newRow[3]);
                AutoTableModel model = mainFrame.getModelTable();
                model.addRow(newRow);
            } catch (NullStrException exc) {
                errLabel.setText(exc.getMessage());
                return;
            } catch (NumberFormatException  exc) {
                errLabel.setText("Not a number!");
                return;
            }
            performEndTask();
		}
    }
}
