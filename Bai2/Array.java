package DesignPatern.Bai2;

public class Array {

    private int[] array;

    public Array(int[] array) {
        this.array = array;
    }

    public void sort(Strategy strategy) {
        strategy.sort(array);
    }

    public void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
