import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class HotelBookingUI extends JFrame implements ActionListener {
private final Hotel hotel;
private final JLabel nameLabel, phoneLabel, checkinLabel, checkoutLabel, roomTypeLabel;
private final JTextField nameField, phoneField, checkinField, checkoutField;
private final JComboBox<RoomType> roomTypeComboBox;
private final JButton bookButton, displayButton;
private final SimpleDateFormat dateFormat;
public HotelBookingUI(Hotel hotel) {
    this.hotel = hotel;
    // Set window properties
    setTitle("Hotel Booking");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new GridLayout(6, 2));

    // Initialize components
    nameLabel = new JLabel("Name:");
    phoneLabel = new JLabel("Phone:");
    checkinLabel = new JLabel("Check-in (dd/MM/yyyy HH:mm):");
    checkoutLabel = new JLabel("Check-out (dd/MM/yyyy HH:mm):");
    roomTypeLabel = new JLabel("Room Type:");
    nameField = new JTextField();
    phoneField = new JTextField();
    checkinField = new JTextField();
    checkoutField = new JTextField();
    roomTypeComboBox = new JComboBox<>(RoomType.values());
    bookButton = new JButton("Book Room");
    displayButton = new JButton("Display Bookings");
    dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    // Add components to window
    add(nameLabel);
    add(nameField);
    add(phoneLabel);
    add(phoneField);
    add(checkinLabel);
    add(checkinField);
    add(checkoutLabel);
    add(checkoutField);
    add(roomTypeLabel);
    add(roomTypeComboBox);
    add(bookButton);
    add(displayButton);

    // Attach event listeners
    bookButton.addActionListener(this);
    displayButton.addActionListener(this);

    // Show window
    setVisible(true);
}

@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == bookButton) {
        String name = nameField.getText();
        String phone = phoneField.getText();
        RoomType roomType = (RoomType) roomTypeComboBox.getSelectedItem();
        Date checkin, checkout;
        try {
            checkin = dateFormat.parse(checkinField.getText());
            checkout = dateFormat.parse(checkoutField.getText());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        hotel.bookRoom(name, phone, checkin, checkout, roomType);
    } else if (e.getSource() == displayButton) {
        hotel.displayBookings();
    }
}

public static void main(String[] args) {
    Hotel hotel1 = new Hotel();
    HotelBookingUI fiveStar=new HotelBookingUI(hotel1);
}
}