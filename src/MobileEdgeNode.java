class MobileEdgeNode {
    private String nodeName;
    private int cpuCapacity; // in MIPS (Million Instructions Per Second)
    private int ram; // in MB
    private int storage; // in MB
    private int bandwidth; // in Mbps
    private RandomWaypointModel mobilityModel;

    public MobileEdgeNode(String nodeName, int cpuCapacity, int ram, int storage, int bandwidth) {
        this.nodeName = nodeName;
        this.cpuCapacity = cpuCapacity;
        this.ram = ram;
        this.storage = storage;
        this.bandwidth = bandwidth;
    }

    public void setMobilityModel(RandomWaypointModel mobilityModel) {
        this.mobilityModel = mobilityModel;
        System.out.println("Mobility model set: " + mobilityModel);
    }

    public void executeTask2(Task2 Task2) {
        if (Task2.getRequiredCpu() <= cpuCapacity) {
            System.out.println("Executing Task2: " + Task2.getTask2Name() + " on node: " + nodeName);
        } else {
            System.out.println("Task2 " + Task2.getTask2Name() + " requires more CPU than available on " + nodeName);
        }
    }

    public static void main(String[] args) {
        // Create a Mobile Edge Node
        MobileEdgeNode edgeNode = new MobileEdgeNode("MobileEdge1", 3000, 2048, 15000, 500);

        // Simulate mobility
        edgeNode.setMobilityModel(new RandomWaypointModel());

        // Task2 assignment
        Task2 mobileTask2 = new Task2("MobileTask21", 400);
        edgeNode.executeTask2(mobileTask2);

        System.out.println("Mobile Edge Node Simulation Completed");
    }
}

class RandomWaypointModel {
    @Override
    public String toString() {
        return "Random Waypoint Mobility Model";
    }
}

class Task2 {
    private String Task2Name;
    private int requiredCpu; // in MIPS

    public Task2(String Task2Name, int requiredCpu) {
        this.Task2Name = Task2Name;
        this.requiredCpu = requiredCpu;
    }

    public String getTask2Name() {
        return Task2Name;
    }

    public int getRequiredCpu() {
        return requiredCpu;
    }
}
