public class SingleRoom extends Room {
    private int numBeds;

    public SingleRoom(int roomNumber, int numBeds) {
        super(roomNumber, RoomType.SINGLE);
        this.numBeds = numBeds;
    }

    public int getNumBeds() {
        return numBeds;
    }
}
