import java.util.ArrayList;

/**
 * A simple implementation of a Priority Queue using a Min-Heap.
 * The smallest element has the highest priority.
 *
 * @param <T> The type of elements in this priority queue. Must implement Comparable.
 */
public class PriorityQueue<T extends Comparable<T>> {
    private final ArrayList<T> heap;

    public PriorityQueue() {
        heap = new ArrayList<>();
    }

    /**
     * Inserts an element into the priority queue while maintaining heap order.
     *
     * @param value The element to be added.
     */
    public void offer(T value) {
        heap.add(value); // Add the new element at the end of the heap.
        heapifyUp(); // Restore heap order.
    }

    /**
     * Removes and returns the element with the highest priority (smallest value).
     *
     * @return The highest priority element, or null if the queue is empty.
     */
    public T poll() {
        if (heap.isEmpty()) return null; // Return null if the heap is empty.
        if (heap.size() == 1) return heap.remove(0); // Return the only element.

        T root = heap.get(0); // Store the root (smallest element).
        heap.set(0, heap.remove(heap.size() - 1)); // Move the last element to the root.
        heapifyDown(); // Restore heap order.
        return root; // Return the removed smallest element.
    }

    /**
     * Retrieves, but does not remove, the element with the highest priority.
     *
     * @return The highest priority element, or null if the queue is empty.
     */
    public T peek() {
        return heap.isEmpty() ? null : heap.get(0); // Return the root element or null if empty.
    }

    /**
     * Restores heap order after inserting an element by moving it up the heap.
     */
    private void heapifyUp() {
        int index = heap.size() - 1; // Start at the last inserted element.

        while (index > 0) { // Continue until reaching the root.
            int parentIndex = (index - 1) / 2; // Get the parent index.

            if (heap.get(index).compareTo(heap.get(parentIndex)) >= 0) {
                break; // Heap order is satisfied.
            }

            swap(index, parentIndex); // Swap with the parent.
            index = parentIndex; // Move up.
        }
    }

    /**
     * Restores heap order after removing the root by pushing the new root down.
     */
    private void heapifyDown() {
        int index = 0; // Start at the root.

        while (2 * index + 1 < heap.size()) { // Ensure at least one child exists.
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = leftChild; // Assume left child is the smallest.

            if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(leftChild)) < 0) {
                smallest = rightChild; // Right child is smaller.
            }

            if (heap.get(index).compareTo(heap.get(smallest)) <= 0) {
                break; // Heap order is satisfied.
            }

            swap(index, smallest); // Swap with the smallest child.
            index = smallest; // Move down.
        }
    }

    /**
     * Swaps two elements in the heap.
     *
     * @param i Index of the first element.
     * @param j Index of the second element.
     */
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
