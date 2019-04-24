package priorityqueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PriorityQueue {

    private class Element {
        int value;
        int key;

        private Element(int value, int key) {
            this.value = value;
            setKey(key);
        }

        private void setKey(int key) {
            if (key >= 0) this.key = key;
        }
    }

    private ArrayList<Element> queue;
    private HashMap<Integer, List<Integer>> valueIndexes;
    private int heapSize;

    public PriorityQueue() {
        queue = new ArrayList<>();
        valueIndexes = new HashMap<>();
        heapSize = 0;
    }

    public PriorityQueue(int capacity) {
        queue = new ArrayList<>(capacity);
        valueIndexes = new HashMap<>();
        heapSize = 0;
    }

    private int parent(int idx) {
        return (idx - 1)/2;
    }

    private int leftChild(int idx) {
        return (idx + 1)*2 - 1;
    }

    private int rightChild(int idx) {
        return (idx + 1)*2;
    }

    private void swap(int i, int j) {
        Element elementI = queue.get(i);
        Element elementJ = queue.get(j);

        List<Integer> iIndexes = valueIndexes.get(elementI.value);
        List<Integer> jIndexes = valueIndexes.get(elementJ.value);

        iIndexes.set(iIndexes.indexOf(i), j);
        jIndexes.set(jIndexes.indexOf(j), i);

        queue.set(i, elementJ);
        queue.set(j, elementI);
    }

    private void bubbleUp(int i) {
        int parent = parent(i);

        if(i > 0) {
            if(queue.get(i).key < queue.get(parent).key) {
                swap(i, parent);
                bubbleUp(parent);
            }
        }
    }

    private void siftDown(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int min;

        if (left < heapSize) {
            if (queue.get(left).key < queue.get(i).key) {
                min = left;
            } else {
                min = i;
            }
            if (right < heapSize) {
                if (queue.get(right).key < queue.get(min).key) {
                    min = right;
                }
            }
        } else {
            min = i;
        }

        if (min != i) {
            swap(i, min);
            siftDown(min);
        }
    }

    public void insert(int value, int priority) {
        Element newElement = new Element(value, priority);
        queue.add(newElement);

        if (!valueIndexes.containsKey(value)) {
            LinkedList<Integer> list = new LinkedList<>();
            list.add(heapSize);
            valueIndexes.put(value, list);
        } else {
            valueIndexes.get(value).add(heapSize);
        }

        bubbleUp(heapSize);
        heapSize++;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public Integer top() {
        if (!isEmpty()) {
            return queue.get(0).value;
        }

        return null;
    }

    public Integer pop() {
        if (!isEmpty()) {
            Element root = queue.get(0);
            valueIndexes.get(root.value).remove(0);

            Element newRoot = queue.remove(heapSize - 1);
            if (heapSize > 1) {
                List<Integer> newRootIndexes = valueIndexes.get(newRoot.value);
                newRootIndexes.set(newRootIndexes.indexOf((heapSize - 1)), 0);
                queue.set(0, newRoot);
            }

            heapSize--;
            siftDown(0);

            return root.value;
        }

        return null;
    }

    public void setPriority(int value, int priority) {
        List<Integer> indexes = valueIndexes.get(value);

        if (indexes == null) return;

        for (int i = 0; i < indexes.size(); i++) {
            Integer idx = indexes.get(i);
            Element element = queue.get(idx);
            if (priority < element.key) {
                element.key = priority;
                bubbleUp(idx);
            }
        }
    }

    public void print() {
        for(int i = 0; i < heapSize; i++) {
            Element e = queue.get(i);
            System.out.print("(" + e.value + ", " + e.key + ") ");
        }
        System.out.println();
    }

}
