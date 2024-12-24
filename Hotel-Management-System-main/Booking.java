import java.util.Date;

public interface Booking {
    public void bookRoom(String name, String phone, Date checkin, Date checkout, RoomType roomType);
    public void displayBookings();
}
