import java.io.Serializable;

public class DataA extends Data implements Serializable {
    double price;

    public DataA(long id, int srcid, double price) {
        super(id, srcid);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void print() {
        System.out.println("id = " + this.getId() +
                ", srcid = " + this.getSrcid() +
                ", price = " + String.format("%.2f", this.getPrice()));
    }
}
