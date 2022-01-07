import java.util.Comparator;

public class SrcidCompare implements Comparator<Data> {
    @Override
    public int compare (Data data1, Data data2) {
        return Integer.compare(data1.getSrcid(), data2.getSrcid());
    }
}
