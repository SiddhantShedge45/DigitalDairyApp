package digitaldiary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.Timer;

public class DateAndTime {
    private JTextField noteTextField;
    private JTextArea notesTextArea;
    private ArrayList<String> notesList;
    private JLabel timeLabel;

    public DateAndTime() {
        notesList = new ArrayList<>();
        
        // Create the frame
        JFrame frame = new JFrame("Digital Diary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create the note input field
        noteTextField = new JTextField(20);
        JButton addNoteButton = new JButton("Add Note");

        // Create the notes display area
        notesTextArea = new JTextArea(10, 30);
        notesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(notesTextArea);

        // Create the time display
        timeLabel = new JLabel();
        updateDateTime(); // Initial time update

        // Add action listener to the button
        addNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNote();
            }
        });

        // Set up a timer to update time every second
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDateTime();
            }
        });
        timer.start();

        // Create a panel for input
        JPanel inputPanel = new JPanel();
        inputPanel.add(noteTextField);
        inputPanel.add(addNoteButton);

        // Add components to the frame
        frame.add(timeLabel, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);

        // Final frame setup
        frame.pack();
        frame.setVisible(true);
    }

    private void addNote() {
        String noteText = noteTextField.getText();
        if (!noteText.trim().isEmpty()) {
            notesList.add(noteText);
            noteTextField.setText(""); // Clear input field
            updateNotesDisplay();
        } else {
            JOptionPane.showMessageDialog(null, "Note cannot be empty.");
        }
    }

    private void updateNotesDisplay() {
        notesTextArea.setText("");
        for (String note : notesList) {
            notesTextArea.append(note + "\n");
        }
    }

    private void updateDateTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
        String dayOfWeek = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}[calendar.get(Calendar.DAY_OF_WEEK) - 1];

        String timeString = String.format("%s %s %d, %02d:%02d:%02d", dayOfWeek, monthNames[month], date, hour, minute, second);
        timeLabel.setText(timeString);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DigitalDiary());
    }
}
