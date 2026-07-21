import java.util.Arrays;

public class IntList {
    private int[] data;
    private static int GROW_FACTOR = 3;
    private static int SIZE = 1;
    private int size;
    public IntList() {
        this(SIZE);
    }
    public IntList(int sizeOfList) {
        data = new int[sizeOfList];
        size = 0;
    }
    public void add(int value) {
        if (size == data.length) {
            data = Arrays.copyOf(data,data.length*GROW_FACTOR);
        }
        data[size++] = value;
    }
    public int get(int index) {
        return data[index];
    }
    public int size() {
        return size;
    }
    public void set(int index, int value) {
        data[index] = value;
    }
}
