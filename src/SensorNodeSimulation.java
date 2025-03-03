// SensorNode class representing a sensor that emits tuples
class SensorNode {
    private String name;
    private int emissionRate; // Tuples per second
    private FogNode3 connectedNode;

    public SensorNode(String name, int emissionRate) {
        this.name = name;
        this.emissionRate = emissionRate;
    }

    public void connectTo(FogNode3 node) {
        this.connectedNode = node;
        System.out.println(name + " connected to " + node.getNodeName());
    }

    public void emitTuples(int seconds) {
        if (connectedNode == null) {
            System.out.println("No FogNode connected. Cannot emit tuples.");
            return;
        }

        int totalTuples = emissionRate * seconds;
        System.out.println(name + " is emitting " + totalTuples + " tuples to " + connectedNode.getNodeName());

        connectedNode.processTuples(totalTuples);
    }
}

// FogNode class representing a node that processes sensor data
class FogNode3 {
    private String nodeName;
    private int cpuCapacity; // in MIPS
    private int ram; // in MB
    private int storage; // in MB
    private int bandwidth; // in Mbps

    public FogNode3(String nodeName, int cpuCapacity, int ram, int storage, int bandwidth) {
        this.nodeName = nodeName;
        this.cpuCapacity = cpuCapacity;
        this.ram = ram;
        this.storage = storage;
        this.bandwidth = bandwidth;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void processTuples(int tuples) {
        System.out.println(nodeName + " processing " + tuples + " tuples...");
        // Simulating processing time based on CPU capacity
        int processingTime = tuples / (cpuCapacity / 100); // Rough estimate
        System.out.println("Processing completed in " + processingTime + " seconds.");
    }
}

// Main application class
public class SensorNodeSimulation {
    public static void main(String[] args) {
        // Creating a SensorNode with an emission rate of 10 tuples/sec
        SensorNode sensor = new SensorNode("TempSensor", 10);

        // Creating a FogNode (processor) with specified resources
        FogNode3 processor = new FogNode3("Processor", 2000, 4096, 20000, 500);

        // Connecting sensor to processor
        sensor.connectTo(processor);

        // Simulating tuple emission for 10 seconds
        sensor.emitTuples(10);

        System.out.println("Sensor Node Simulation Completed");
    }
}
