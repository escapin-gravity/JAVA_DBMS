package UI;

import logic.SimpleDBMS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class DBMSApp extends JFrame {
    private SimpleDBMS database;
    private JTextField keyField;
    private JTextField valueField;
    private JTextArea logArea;
    private JTable dataTable;

    public DBMSApp() {
        database = new SimpleDBMS();

        setTitle("Relational Database");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.WEST);

        JPanel logPanel = createLogPanel();
        add(logPanel, BorderLayout.CENTER);

        JPanel dataPanel = createDataPanel();
        add(dataPanel, BorderLayout.EAST);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Key:"));
        keyField = new JTextField();
        inputPanel.add(keyField);

        inputPanel.add(new JLabel("Value:"));
        valueField = new JTextField();
        inputPanel.add(valueField);

        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = keyField.getText();
                String value = valueField.getText();
                if (!key.isEmpty() && !value.isEmpty()) {
                    database.insertData(key, value);
                    logArea.append("Inserted: " + key + " - " + value + "\n");
                    clearFields();
                    updateData();
                } else {
                    JOptionPane.showMessageDialog(DBMSApp.this, "Key and value cannot be empty");
                }
            }
        });
        inputPanel.add(insertButton);

        JButton retrieveButton = new JButton("Retrieve");
        retrieveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = keyField.getText();
                if (!key.isEmpty()) {
                    String value = database.getData(key);
                    if (value != null) {
                        logArea.append("Retrieved value for key " + key + ": " + value + "\n");
                    } else {
                        logArea.append("No value found for key " + key + "\n");
                    }
                } else {
                    JOptionPane.showMessageDialog(DBMSApp.this, "Key cannot be empty");
                }
            }
        });
        inputPanel.add(retrieveButton);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = keyField.getText();
                String value = valueField.getText();
                if (!key.isEmpty() && !value.isEmpty()) {
                    database.updateData(key, value);
                    logArea.append("Updated value for key " + key + ": " + value + "\n");
                    clearFields();
                    updateData();
                } else {
                    JOptionPane.showMessageDialog(DBMSApp.this, "Key and value cannot be empty");
                }
            }
        });
        inputPanel.add(updateButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = keyField.getText();
                if (!key.isEmpty()) {
                    database.deleteData(key);
                    logArea.append("Deleted key: " + key + "\n");
                    clearFields();
                    updateData();
                } else {
                    JOptionPane.showMessageDialog(DBMSApp.this, "Key cannot be empty");
                }
            }
        });
        inputPanel.add(deleteButton);

        return inputPanel;
    }

    private JPanel createLogPanel() {
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        logArea = new JTextArea();
        logArea.setEditable(false);
        logPanel.add(new JScrollPane(logArea), BorderLayout.CENTER);
        return logPanel;
    }

    private JPanel createDataPanel() {
        JPanel dataPanel = new JPanel(new BorderLayout());
        dataPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        DefaultTableModel model = new DefaultTableModel(new String[]{"Key", "Value"}, 0);
        dataTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        dataPanel.add(scrollPane, BorderLayout.CENTER);

        return dataPanel;
    }

    private void updateData() {
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        model.setRowCount(0);
        Map<String, String> data = database.getAllData();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            model.addRow(new String[]{entry.getKey(), entry.getValue()});
        }
    }

    private void clearFields() {
        keyField.setText("");
        valueField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DBMSApp gui = new DBMSApp();
            gui.setVisible(true);
        });
    }
}

