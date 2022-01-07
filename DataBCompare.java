import java.util.Comparator;

public class DataBCompare implements Comparator<DataB> {
    @Override
    public int compare (DataB data1, DataB data2) {
        return Double.compare(data2.getReserved(), data1.getReserved());
    }
}
