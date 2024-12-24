
import java.util.Date;
class BookingInfo {
    private String name;
    private String phone;
    private Date checkin;
    private Date checkout;
    private Room room;
    public BookingInfo(String name, String phone, Date checkin, Date checkout, Room room) {
        this.name = name;
        this.phone = phone;
        this.checkin = checkin;
        this.checkout = checkout;
        this.room = room;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public Date getCheckin() {
        return checkin;
    }
    
    public Date getCheckout() {
        return checkout;
    }
    
    public Room getRoom() {
        return room;
    }
    
    public String toString() {
        return "Name: " + name + ", Phone: " + phone + ", Check-in: " + checkin + ", Check-out: " + checkout + ", Room: " + room.getRoomNumber();
    }
}    