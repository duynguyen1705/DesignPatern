package DesignPatern.Bai2;

public class SelectionSortStrategy extends Strategy {

    @Override
    public void sort(int[] array) {
        // Sắp xếp chọn
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }
}
