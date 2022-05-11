public class LinkedListDeque<T> {
    private static class StuffNode<item> {
        public StuffNode front;
        public StuffNode next;
        public item value;

        public StuffNode(item i) {
            value = i;
            front = null;
            next = null;
        }
    }

    private StuffNode first;
    private StuffNode end;
    private int size;

    public LinkedListDeque() {
        first = new StuffNode(0);
        end = new StuffNode(0);

        first.next = end;
        end.front = first;
        size = 0;
    }

    public void addFirst(T t) {
        StuffNode s = new StuffNode(t);
        StuffNode temp = first.next;
        s.next = temp;
        temp.front = s;
        s.front = first;
        first.next = s;
        size++;
    }

    public void addLast(T t) {
        StuffNode s = new StuffNode(t);
        StuffNode temp = end.front;
        s.next = end;
        s.front = temp;
        end.front = s;
        temp.next = s;
        size++;
    }

    public T get(int index) {
        if (this.size == 0 || index >= this.size || index < 0) {
            return null;
        }
        StuffNode s = first.next;
        for (int i = 0; i < index; i++) {
            s = s.next;
        }
        return (T) s.value;
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
        StuffNode s = first;
        for (int i = 0; i < size; i++) {
            s = s.next;
            System.out.print(s.value + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0 || first == null) {
            return null;
        }
        T s = (T) first.next.value;
        StuffNode temp = first.next.next;
        first.next = temp;
        temp.front = first;
        size--;
        return s;
    }

    public T removeLast() {
        if (size == 0 || end == null) {
            return null;
        }

        T s = (T) end.front.value;
        StuffNode temp = end.front.front;
        end.front = temp;
        temp.next = end;
        size--;
        return s;
    }

    private T getRecursiveHelper(int index, StuffNode s) {
        if (index == 0) {
            return (T) s.value;
        }
        return (T) getRecursiveHelper(index - 1, s.next);
    }

    public T getRecursive(int index) {
        if (size == 0) {
            return null;
        } else if (index < 0 || index >= size) {
            return null;
        }
        return (T) getRecursiveHelper(index, first.next);
    }
}
