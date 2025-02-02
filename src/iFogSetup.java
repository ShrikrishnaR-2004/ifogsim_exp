import org.cloudbus.cloudsim.core.CloudSim;

import java.util.Calendar;

public class iFogSetup {
    public static void main(String[] args) {
        System.out.println("Starting iFog Simulator setup...");

        // Step 1: Initialize CloudSim
        int numUsers = 1; // Number of users
        Calendar calendar = Calendar.getInstance(); // Current date and time
        boolean traceFlag = false; // Disable event tracing

        // Initialize CloudSim library
        CloudSim.init(numUsers, calendar, traceFlag);

        System.out.println("iFog Simulator setup completed successfully!");
    }
}
