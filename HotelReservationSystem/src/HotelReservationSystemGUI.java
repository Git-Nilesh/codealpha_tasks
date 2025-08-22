import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Room {
    String type;
    int roomNumber;
    boolean isBooked;

    Room(String type, int roomNumber) {
        this.type = type;
        this.roomNumber = roomNumber;
        this.isBooked = false;
    }
}

class Booking {
    String customerName;
    int roomNumber;

    Booking(String customerName, int roomNumber) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
    }
}

public class HotelReservationSystemGUI extends JFrame {
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Booking> bookings = new ArrayList<>();

    private JTextArea displayArea;
    private JTextField nameField, roomField;

    public HotelReservationSystemGUI() {
        setTitle("Hotel Reservation System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeRooms();

        JPanel panel = new JPanel(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Customer Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Room Number:"));
        roomField = new JTextField();
        inputPanel.add(roomField);

        JPanel buttonPanel = new JPanel();
        JButton viewButton = new JButton("View Rooms");
        JButton bookButton = new JButton("Book Room");
        JButton cancelButton = new JButton("Cancel Booking");
        JButton viewBookingsButton = new JButton("View Bookings");

        buttonPanel.add(viewButton);
        buttonPanel.add(bookButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(viewBookingsButton);

        viewButton.addActionListener(e -> viewAvailableRooms());
        bookButton.addActionListener(e -> bookRoom());
        cancelButton.addActionListener(e -> cancelBooking());
        viewBookingsButton.addActionListener(e -> viewAllBookings());

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void initializeRooms() {
        rooms.add(new Room("Standard", 101));
        rooms.add(new Room("Standard", 102));
        rooms.add(new Room("Deluxe", 201));
        rooms.add(new Room("Deluxe", 202));
        rooms.add(new Room("Suite", 301));
        rooms.add(new Room("Suite", 302));
    }

    private void viewAvailableRooms() {
        displayArea.setText("Available Rooms:\n");
        for (Room room : rooms) {
            if (!room.isBooked) {
                displayArea.append("Room " + room.roomNumber + " (" + room.type + ")\n");
            }
        }
    }

    private void bookRoom() {
        String name = nameField.getText().trim();
        String roomNumStr = roomField.getText().trim();

        if (name.isEmpty() || roomNumStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both name and room number.");
            return;
        }

        try {
            int roomNumber = Integer.parseInt(roomNumStr);
            for (Room room : rooms) {
                if (room.roomNumber == roomNumber && !room.isBooked) {
                    room.isBooked = true;
                    bookings.add(new Booking(name, roomNumber));
                    displayArea.setText("Room " + roomNumber + " booked for " + name + ".\n");
                    return;
                }
            }
            displayArea.setText("Room not available or invalid room number.\n");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid room number format.");
        }
    }

    private void cancelBooking() {
        String roomNumStr = roomField.getText().trim();

        if (roomNumStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the room number.");
            return;
        }

        try {
            int roomNumber = Integer.parseInt(roomNumStr);
            for (Booking booking : bookings) {
                if (booking.roomNumber == roomNumber) {
                    bookings.remove(booking);
                    for (Room room : rooms) {
                        if (room.roomNumber == roomNumber) {
                            room.isBooked = false;
                            break;
                        }
                    }
                    displayArea.setText("Booking for room " + roomNumber + " has been cancelled.\n");
                    return;
                }
            }
            displayArea.setText("No booking found for that room.\n");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid room number format.");
        }
    }

    private void viewAllBookings() {
        if (bookings.isEmpty()) {
            displayArea.setText("No bookings found.\n");
            return;
        }
        displayArea.setText("Current Bookings:\n");
        for (Booking booking : bookings) {
            displayArea.append("Room " + booking.roomNumber + " booked by " + booking.customerName + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HotelReservationSystemGUI app = new HotelReservationSystemGUI();
            app.setVisible(true);
        });
    }
}

