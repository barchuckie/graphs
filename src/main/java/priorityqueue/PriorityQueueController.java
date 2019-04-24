package priorityqueue;

import controller.Controller;

import java.util.Scanner;

public class PriorityQueueController implements Controller {

    private PriorityQueue priorityQueue;

    public PriorityQueueController() {
        priorityQueue = new PriorityQueue();
    }

    @Override
    public void start() {
        System.out.print("Insert number of operations: ");
        Scanner scanner = new Scanner(System.in);
        int operationCount = scanner.nextInt();
        scanner.nextLine();

        for(int i = 0; i < operationCount; i++) {
            String operation = scanner.nextLine();
            String [] opElements = operation.split(" ");

            if (opElements.length == 0) {
                System.out.println("Insert operation name!");
                i--;
                continue;
            }

            if (!executeOperation(opElements)) {
                System.out.println("Try again");
                i--;
            }
        }
    }

    private boolean executeOperation(String [] operationElements) {
        String operation = operationElements[0];
        int val, priority;

        switch (operation) {
            case "insert":
                if (operationElements.length >= 2) {
                    val = Integer.parseInt(operationElements[1]);
                    priority = Integer.parseInt(operationElements[2]);
                    priorityQueue.insert(val, priority);
                    return true;
                } else return false;

            case "empty":
                System.out.println(priorityQueue.isEmpty());
                return true;

            case "top":
                Integer top = priorityQueue.top();
                System.out.println(top == null ? "" : top);
                return true;

            case "pop":
                Integer popped = priorityQueue.pop();
                System.out.println(popped == null ? "" : popped);
                return true;

            case "priority":
                if (operationElements.length >= 2) {
                    val = Integer.parseInt(operationElements[1]);
                    priority = Integer.parseInt(operationElements[2]);
                    priorityQueue.setPriority(val, priority);
                    return true;
                } else return false;

            case "print":
                priorityQueue.print();
                return true;

                default:
                    return false;

        }
    }

}
