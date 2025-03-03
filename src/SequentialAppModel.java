// Task class representing a computational task
class Task1 {
    private String name;
    private int computationLoad; // in MIPS

    public Task1(String name, int computationLoad) {
        this.name = name;
        this.computationLoad = computationLoad;
    }

    public String getName() {
        return name;
    }

    public int getComputationLoad() {
        return computationLoad;
    }
}

// FogNode class representing a node that executes tasks
class FogNode2 {
    private String nodeName;
    private int cpuCapacity; // in MIPS
    private int ram; // in MB
    private int storage; // in MB
    private int bandwidth; // in Mbps

    public FogNode2(String nodeName, int cpuCapacity, int ram, int storage, int bandwidth) {
        this.nodeName = nodeName;
        this.cpuCapacity = cpuCapacity;
        this.ram = ram;
        this.storage = storage;
        this.bandwidth = bandwidth;
    }

    public void executeTask(Task1 task) {
        if (task.getComputationLoad() > cpuCapacity) {
            System.out.println("Task " + task.getName() + " cannot be executed due to insufficient CPU capacity.");
        } else {
            System.out.println("Executing " + task.getName() + " with load " + task.getComputationLoad() + " MIPS on " + nodeName);
        }
    }
}

// Main application class
public class SequentialAppModel {
    public static void main(String[] args) {
        // Creating a FogNode with specified resources
        FogNode2 master = new FogNode2("Master", 400, 8192, 50000, 1000);

        // Creating tasks
        Task1 task1 = new Task1("Task1", 300);
        Task1 task2 = new Task1("Task2", 400);

        // Executing tasks sequentially
        master.executeTask(task1);
        master.executeTask(task2);

        System.out.println("Unidirectional Sequential Execution Completed.");
    }
}
