public class VipRoom extends Room {
    private boolean AC;

    public VipRoom(int roomNumber, boolean AC) {
        super(roomNumber, RoomType.VIP);
        this.AC = AC;
    }

    public boolean hasAC() {
        return AC;
    }
}


