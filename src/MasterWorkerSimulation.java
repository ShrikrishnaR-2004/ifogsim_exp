import org.fog.application.AppModule;
import org.fog.application.Application;
import org.fog.entities.Actuator;
import org.fog.entities.FogBroker;
import org.fog.entities.FogDevice;
import org.fog.entities.Sensor;
import org.fog.entities.Tuple;
import org.fog.placement.ModuleMapping;
import org.fog.placement.ModulePlacementEdgewards;
import org.fog.utils.FogUtils;
import org.fog.utils.Logger;
import org.cloudbus.cloudsim.core.CloudSim;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MasterWorkerSimulation {
    public static void main(String[] args) {
        Logger.ENABLED = true;

        int numUsers = 1;
        Calendar calendar = Calendar.getInstance();
        CloudSim.init(numUsers, calendar, false);

        // Create a broker to manage fog resources
        FogBroker broker = new FogBroker("Broker");

        // Define Master and Worker Nodes
        List<FogDevice> fogDevices = new ArrayList<>();
        FogDevice master = createFogDevice("Master", 4000, 8192, 50000, 1000);
        FogDevice worker1 = createFogDevice("Worker1", 2000, 4096, 20000, 500);
        FogDevice worker2 = createFogDevice("Worker2", 2000, 4096, 20000, 500);

        fogDevices.add(master);
        fogDevices.add(worker1);
        fogDevices.add(worker2);

        // Create application
        Application application = createApplication("FogApp", broker.getId());

        // **Fix: Define sensors and actuators (empty for now)**
        List<Sensor> sensors = new ArrayList<>();
        List<Actuator> actuators = new ArrayList<>();

        // **Fix: Create a ModuleMapping**
        ModuleMapping moduleMapping = ModuleMapping.createModuleMapping();
        moduleMapping.addModuleToDevice("MasterModule", "Master");
        moduleMapping.addModuleToDevice("WorkerModule", "Worker1");
        moduleMapping.addModuleToDevice("WorkerModule", "Worker2");

        // **Fix: Use correct constructor**
        new ModulePlacementEdgewards(fogDevices, sensors, actuators, application, moduleMapping);

        CloudSim.startSimulation();
        CloudSim.stopSimulation();

        System.out.println("Simulation Completed");
    }

    private static FogDevice createFogDevice(String name, int mips, int ram, long storage, int bw) {
        return new FogDevice(name, mips, ram, storage, bw, 0, 0, 0, 0);
    }

    private static Application createApplication(String appId, int userId) {
        Application application = new Application(appId, userId);

        // Define app modules (master & worker tasks)
        application.addAppModule("MasterModule", 10);
        application.addAppModule("WorkerModule", 5);

        // Define tuples representing tasks
        application.addAppEdge("MasterModule", "WorkerModule", 500, 100, "TASK", Tuple.UP, AppModule.PEER);

        return application;
    }
}
