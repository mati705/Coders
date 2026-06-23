public class ContinuousRegistrationCircularQueue {

    private String[] arr;
    private int front;
    private int rear;
    private int size;
    private int capacity;

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

    public String peek() {
        if (isEmpty()) {
            return null;
        }

        return arr[front];
    }

    private void resize() {
        int newCapacity = capacity * 2;
        String[] newArr = new String[newCapacity];

        int index = front;

        for (int i = 0; i < size; i++) {
            newArr[i] = arr[index];
            index = (index + 1) % capacity;
        }

        arr = newArr;
        front = 0;
        rear = size - 1;
        capacity = newCapacity;
    }

    public boolean enqueue(String studentId) {
        if (isFull()) {
            resize();
        }

        rear = (rear + 1) % capacity;
        arr[rear] = studentId;
        size++;
        return true;
    }

    public String dequeue() {
        if (isEmpty()) {
            return null;
        }

        String value = arr[front];
        arr[front] = null;
        front = (front + 1) % capacity;
        size--;

        return value;
    }

    public void display() {
        System.out.println("\n--- Continuous Registration Circular Queue ---");

        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        int index = front;

        for (int i = 0; i < size; i++) {
            System.out.print(arr[index] + " ");
            index = (index + 1) % capacity;
        }

        System.out.println();
    }
}