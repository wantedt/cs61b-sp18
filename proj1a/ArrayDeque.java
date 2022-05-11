public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private int correctIndex(int index) {
        if (index < 0) {
            return index + items.length;
        } else if (index > items.length - 1) {
            return index % items.length;
        } else {
            return index;
        }
    }

    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = items[correctIndex(nextFirst + 1 + i)];
        }

        items = temp;
        nextFirst = temp.length - 1;
        nextLast = size;
    }

    public void addFirst(T t) {
        if (size == items.length) {
            resize(items.length * 2);
        }

        items[nextFirst] = t;
        nextFirst = correctIndex(nextFirst - 1);
        size++;
    }

    public void addLast(T t) {
        if (size == items.length) {
            resize(items.length * 2);
        }

        items[nextLast] = t;
        nextLast = correctIndex(nextLast + 1);
        size++;
    }

    public T get(int index) {
        if (size == 0 || index > size - 1 || index < 0) {
            return null;
        }
        return items[correctIndex(nextFirst + 1 + index)];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) {
            return;
        }

        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }

        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        T t = items[correctIndex(nextFirst + 1)];
//        items[correctIndex(nextFirst + 1)] = null;
        nextFirst = correctIndex(nextFirst + 1);
        size--;

        if (size < items.length * 0.25 && items.length >= 16) {
            resize(items.length / 2);
        }
        return t;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        T t = items[correctIndex(nextLast - 1)];
        nextLast = correctIndex(nextLast - 1);
        size--;

        if (size - 1 < items.length * 0.25 && items.length >= 16) {
            resize(items.length / 2);
        }

        return t;
    }

}
