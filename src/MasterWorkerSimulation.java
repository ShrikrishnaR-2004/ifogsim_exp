import java.util.ArrayList;
import java.util.List;

class Fognode1 {
    String name;
    int processingPower; // MIPS
    int memory; // MB
    int storage; // MB
    int bandwidth; // Mbps
    List<Task> tasks;

    public Fognode1(String name, int processingPower, int memory, int storage, int bandwidth) {
        this.name = name;
        this.processingPower = processingPower;
        this.memory = memory;
        this.storage = storage;
        this.bandwidth = bandwidth;
        this.tasks = new ArrayList<>();
    }

    public void assignTask(Task task) { // This one is for the Master to receive tasks
        tasks.add(task);
        System.out.println(name + " assigned " + task.name);
    }

    public void assignTask(Fognode1 worker, Task task) { // This one is for the Master to distribute
        worker.assignTask(task); // Delegate to the worker
        System.out.println(name + " assigned " + task.name + " to " + worker.name);
    }

    public void executeTasks() {
        for (Task task : tasks) {
            System.out.println(name + " executing " + task.name);
            int executionTime = task.processingRequirement / processingPower; // Simplified execution time calculation
            try {
                Thread.sleep(executionTime * 1000); // Simulate execution time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " finished " + task.name);
        }
        tasks.clear(); // clear the task after execution.
    }
}

class Task {
    String name;
    int processingRequirement; // MI (Million Instructions)

    public Task(String name, int processingRequirement) {
        this.name = name;
        this.processingRequirement = processingRequirement;
    }
}

public class MasterWorkerSimulation {
    public static void main(String[] args) {
        // Master node setup
        Fognode1 master = new Fognode1("Master", 4000, 8192, 50000, 1000);

        // Worker nodes setup
        Fognode1 worker1 = new Fognode1("Worker1", 2000, 4096, 20000, 500);
        Fognode1 worker2 = new Fognode1("Worker2", 2000, 4096, 20000, 500);

        // Task distribution
        Task task1 = new Task("Task1", 500);
        Task task2 = new Task("Task2", 700);

        master.assignTask(task1); // Master receives tasks
        master.assignTask(task2);

        master.assignTask(worker1, task1); // Master distributes to workers
        master.assignTask(worker2, task2);

        System.out.println("Simulation Completed");
    }
}