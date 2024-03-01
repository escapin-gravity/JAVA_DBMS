package UI;

import logic.SimpleDBMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DBMSApp extends JFrame {
    private SimpleDBMS db;
    private JTextField keyField;
    private JTextField valueField;
    private JTextArea outputArea;

    public DBMSApp() {
        db = new SimpleDBMS();

        setTitle("Simple DBMS");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Key:"));
        keyField = new JTextField();
        inputPanel.add(keyField);
        inputPanel.add(new JLabel("Value:"));
        valueField = new JTextField();
        inputPanel.add(valueField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = keyField.getText();
                String value = valueField.getText();
                if (!key.isEmpty() && !value.isEmpty()) {
                    db.put(key, value);
                    outputArea.append("Added: " + key + " - " + value + "\n");
                } else {
                    JOptionPane.showMessageDialog(DBMSApp.this, "Key and value cannot be empty");
                }
            }
        });
        inputPanel.add(addButton);

        JButton getButton = new JButton("Get");
        getButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = keyField.getText();
                String value = db.get(key);
                if (value != null) {
                    outputArea.append("Value for key " + key + ": " + value + "\n");
                } else {
                    outputArea.append("No value found for key " + key + "\n");
                }
            }
        });
        inputPanel.add(getButton);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = keyField.getText();
                String newValue = valueField.getText();
                if (!key.isEmpty() && !newValue.isEmpty()) {
                    String oldValue = db.get(key);
                    if (oldValue != null) {
                        db.put(key, newValue);
                        outputArea.append("Updated: " + key + " - " + oldValue + " to " + newValue + "\n");
                    } else {
                        outputArea.append("No value found for key " + key + "\n");
                    }
                } else {
                    JOptionPane.showMessageDialog(DBMSApp.this, "Key and value cannot be empty");
                }
            }
        });
        inputPanel.add(updateButton);

        return inputPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DBMSApp().setVisible(true);
            }
        });
    }
}
