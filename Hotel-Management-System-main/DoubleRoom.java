public class DoubleRoom extends Room {
    private int numBeds;

    public DoubleRoom(int roomNumber, int numBeds) {
        super(roomNumber, RoomType.DOUBLE);
        this.numBeds = numBeds;
    }

    public int getNumBeds() {
        return numBeds;
    }
}