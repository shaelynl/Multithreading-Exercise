import java.util.Comparator;

public class DataACompare implements Comparator<DataA> {
    @Override
    public int compare (DataA data1, DataA data2) {
        return Double.compare(data2.getPrice(), data1.getPrice());
    }
}
