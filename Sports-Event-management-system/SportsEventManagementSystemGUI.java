
package project;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;





class Event {
    String name;
    String date;

    Event(String name, String date) {
        this.name = name;
        this.date = date;
    }
}

public class SportsEventManagementSystemGUI extends JFrame {
    private JTextField eventNameField;
    private JTextField eventDateField;
    private JTable eventTable;
    private DefaultTableModel tableModel;
    private ArrayList<Event> events;

    public SportsEventManagementSystemGUI() {
        events = new ArrayList<>();
        setTitle("Sports Event Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        eventNameField = new JTextField(15);
        eventDateField = new JTextField(10);
        JButton addButton = new JButton("Add Event");
        JButton deleteButton = new JButton("Delete Event");

        inputPanel.add(new JLabel("Event Name:"));
        inputPanel.add(eventNameField);
        inputPanel.add(new JLabel("Event Date:"));
        inputPanel.add(eventDateField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        // Table setup
        tableModel = new DefaultTableModel(new String[]{"Event Name", "Event Date"}, 0);
        eventTable = new JTable(tableModel);
        
        // Action listeners for buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEvent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEvent();
            }
        });

        // Add components to the main panel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(eventTable), BorderLayout.CENTER);
        
        // Add main panel to the frame
        add(mainPanel);
    }

    private void addEvent() {
        String eventName = eventNameField.getText().trim();
        String eventDate = eventDateField.getText().trim();
        if (!eventName.isEmpty() && !eventDate.isEmpty()) {
            Event event = new Event(eventName, eventDate);
            events.add(event);
            tableModel.addRow(new Object[]{event.name, event.date});
            eventNameField.setText("");
            eventDateField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter both event name and date.");
        }
    }

    private void deleteEvent() {
        int selectedRow = eventTable.getSelectedRow();
        if (selectedRow != -1) {
            events.remove(selectedRow);
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an event to delete.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SportsEventManagementSystemGUI frame = new SportsEventManagementSystemGUI();
            frame.setVisible(true);
        });
    }
}