import java.io.Serializable;

public class DataB extends Data implements Serializable {
    long reserved;

    public DataB(long id, int srcid, long reserved) {
        super(id, srcid);
        this.reserved = reserved;
    }

    public long getReserved() {
        return reserved;
    }

    public void print() {
        System.out.println("id = " + this.getId() +
                ", srcid = " + this.getSrcid() +
                ", reserved = " + this.getReserved());
    }
}
