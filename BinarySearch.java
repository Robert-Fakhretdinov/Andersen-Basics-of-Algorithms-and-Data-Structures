public class BinarySearch {
    public static void main(String[] args) {
        System.out.println(binarySearch(new int[]{1, 6, 8, 14, 19, 23, 45, 78, 96}, 78)); // массив должен быть отсортирован
    }

    public static int binarySearch(int[] array, int key) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int middle  = low + (high - low) / 2; // найдем индекс среднего элемента массива

            if (key < array[middle]) {
                high = middle - 1;
            } else if (key > array[middle]) {
                low = middle + 1;
            } else {
                return middle;
            }
        }
        return -1; // если не найдено число то возвращаем -1
    }
}
