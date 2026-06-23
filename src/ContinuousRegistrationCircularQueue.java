public class ContinuousRegistrationCircularQueue {
    private String[] arr;
    private int front, rear, size, capacity;

    public ContinuousRegistrationCircularQueue(int capacity) {
        this.capacity = capacity;
        arr = new String[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    // Look at the next student in line without removing them
    public String peek() {
        if (isEmpty()) return null;
        return arr[front];
    }

    // Dynamic Resizing: Doubles the queue capacity when full
    private void resize() {
        int newCapacity = capacity * 2;
        String[] newArr = new String[newCapacity];

        int i = front;
        for (int n = 0; n < size; n++) {
            newArr[n] = arr[i];
            i = (i + 1) % capacity;
        }

        this.arr = newArr;
        this.front = 0;
        this.rear = size - 1;
        this.capacity = newCapacity;
        System.out.println(">> Queue automatically resized to capacity: " + newCapacity);
    }

    public boolean enqueue(String studentId) {

        if (size == capacity) {
            System.out.println("Circular Queue FULL. Cannot add " + studentId);

            return false;

        }

        rear = (rear + 1) % capacity;
        arr[rear] = studentId;
        size++;
        return true;

    }


    public String dequeue() {

        if (size == 0) return null;
        String s = arr[front];
        front = (front + 1) % capacity;
        size--;

        return s;

    }


    public void display() {

        System.out.println("\n--- Continuous Registration (Circular Queue) ---");
        int i = front;

        for (int n = 0; n < size; n++) {

            System.out.print(arr[i] + " ");
            i = (i + 1) % capacity;

        }

        System.out.println();

    }

}