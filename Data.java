import java.io.Serializable;

public class Data implements Serializable {
    long id;
    int srcid;

    Data (long id, int srcid) {
        this.id = id;
        this.srcid = srcid;
    }

    public long getId() {
        return id;
    }

    public int getSrcid() {
        return srcid;
    }
}
