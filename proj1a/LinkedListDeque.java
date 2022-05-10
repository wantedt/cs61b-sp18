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
        if (size == 0) return;
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
        StuffNode s = first.next;
        StuffNode temp = s.next;
        first.next = temp;
        temp.front = first.next;
        size--;
        return (T) s.value;
    }

    public T removeLast() {
        if (size == 0 || end == null) {
            return null;
        }

        StuffNode s = end.front;
        StuffNode temp = s.front;
        end.front = temp;
        temp.next = end;
        size--;
        return (T) s.value;
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


    public static void main(String[] args) {
        LinkedListDeque<Integer> l = new LinkedListDeque<Integer>();
        l.addFirst(5);
        l.addFirst(10);
        l.addLast(15);
        l.addLast(20);
        l.printDeque();

        System.out.println(l.get(2));
        System.out.println(l.getRecursive(2));

        l.removeFirst();
        l.printDeque();

        l.removeLast();
        l.printDeque();
    }
}
