import java.util.*;

class Application {
    private String name;
    private List<AppModule> modules;
    private List<Edge> edges;

    public Application(String name) {
        this.name = name;
        this.modules = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public void addModule(AppModule module) {
        modules.add(module);
    }

    public void addEdge(AppModule from, AppModule to, int bandwidth, int delay) {
        edges.add(new Edge(from, to, bandwidth, delay));
    }

    @Override
    public String toString() {
        return "Application{Name='" + name + "', Modules=" + modules + ", Edges=" + edges + '}';
    }
}

class AppModule {
    private String name;
    private int cost;
    private int resourceUsage;

    public AppModule(String name, int cost, int resourceUsage) {
        this.name = name;
        this.cost = cost;
        this.resourceUsage = resourceUsage;
    }

    @Override
    public String toString() {
        return "AppModule{Name='" + name + "', Cost=" + cost + ", ResourceUsage=" + resourceUsage + '}';
    }
}

class Edge {
    private AppModule from;
    private AppModule to;
    private int bandwidth;
    private int delay;

    public Edge(AppModule from, AppModule to, int bandwidth, int delay) {
        this.from = from;
        this.to = to;
        this.bandwidth = bandwidth;
        this.delay = delay;
    }

    @Override
    public String toString() {
        return "Edge{From='" + from + "', To='" + to + "', Bandwidth=" + bandwidth + ", Delay=" + delay + '}';
    }
}

public class ApplicationModel {
    public static void main(String[] args) {
        // Create Application
        Application app = new Application("MyApp");

        // Add modules to the application
        AppModule sensorModule = new AppModule("Sensor", 100, 500);
        AppModule processingModule = new AppModule("Processor", 2000, 1024);
        app.addModule(sensorModule);
        app.addModule(processingModule);

        // Define communication between modules
        app.addEdge(sensorModule, processingModule, 100, 10);

        System.out.println("Application Model Created: " + app);
    }
}
