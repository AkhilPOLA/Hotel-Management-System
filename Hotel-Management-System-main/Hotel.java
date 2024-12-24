//import jakarta.servlet.ServletException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import javax.swing.JOptionPane;

public class Hotel implements Booking {
    private Room[] rooms;
    private ArrayList<BookingInfo> bookings;
    private final int MAX_ROOMS = 5;

    public Hotel() {
        rooms = new Room[MAX_ROOMS];
        bookings = new ArrayList<BookingInfo>();
        // Initialize rooms
        for (int i = 0; i <MAX_ROOMS; i++) {
            if (i < 2) {
            rooms[i] = new SingleRoom(i+1, 1);
            } else if (i <4) {
            rooms[i] = new DoubleRoom(i+1, 2);
            } else {
            rooms[i] = new VipRoom(i+1, true);
            }
            }
            }
            public void bookRoom(String name, String phone, Date checkin, Date checkout, RoomType roomType) {
                // Find available room of the specified type
                Room room = null;
                for (int i = 0; i < rooms.length; i++) {
                    if (rooms[i].getRoomType() == roomType && rooms[i].isAvailable()) {
                        room = rooms[i];
                        break;
                    }
                }
                if (room == null) {
                    JOptionPane.showMessageDialog(null,"No rooms of the specified type are available.");
                    System.out.println("No rooms of the specified type are available.");
                    return;
                }
                // Book the room
                room.setAvailable(false);
                BookingInfo booking = new BookingInfo(name, phone, checkin, checkout, room);
                bookings.add(booking);
                // Save booking information to file
                saveBooking(booking);
                JOptionPane.showMessageDialog(null, "Room " + room.getRoomNumber() + " has been booked for " + name + ".");
                System.out.println("Room " + room.getRoomNumber() + " has been booked for " + name + ".");
                RoomAvailability(room, checkout);

            }
            
            public void displayBookings() {
                for (BookingInfo booking : bookings) {
                    System.out.println(booking);
                }
            }
            
            private void saveBooking(BookingInfo booking) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            String DB_URL = "jdbc:mysql://localhost:3306/hotel_booking";
            String DB_USER = "root";
            String DB_PASSWORD = "Akhil2004@";
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Prepare SQL query
            String sql = "INSERT INTO bookings (name, phone, checkin, checkout, room_type, room_number) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            // Set parameters
            preparedStatement.setString(1, booking.getName());
            preparedStatement.setString(2, booking.getPhone());
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(booking.getCheckin().getTime()));
            preparedStatement.setTimestamp(4, new java.sql.Timestamp(booking.getCheckout().getTime()));
            preparedStatement.setString(5, booking.getRoom().getRoomType().toString());
            preparedStatement.setInt(6, booking.getRoom().getRoomNumber());

            // Execute update
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            File file = new File("bookings.txt");
            FileWriter writer = new FileWriter(file, true);
            writer.write(booking.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

     private java.util.Date parseDate(String dateString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            return formatter.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
          

            private void RoomAvailability(Room room, Date checkout) {
                long currentTime = System.currentTimeMillis();
                long checkoutTime = checkout.getTime();
                long delay = checkoutTime - currentTime;
                
                if (delay > 0) {
                    new Thread(() -> {
                        try {
                            Thread.sleep(delay);
                            room.setAvailable(true);
                            System.out.println("Room " + room.getRoomNumber() + " is now available.");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
            }
            
        
        }
            