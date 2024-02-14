package floormanagement.syncmanagement;

import java.util.List;
import floormanagement.floor.FloorPlan;
import floormanagement.storage.LocalStorage;

/**
 * Manages synchronization of floor plans with the server.
 */
public class Synchronization {
    private final LocalStorage localStorage;

    /**
     * Constructs a Synchronization object with the specified local storage.
     * 
     * @param localStorage The local storage to be synchronized with the server.
     */
    public Synchronization(LocalStorage localStorage) {
        this.localStorage = localStorage;
    }

    /**
     * Synchronizes floor plans with the server.
     * 
     * @throws IllegalStateException If synchronization fails due to no internet connection.
     */
    public void serverSync() throws IllegalStateException {
        boolean isConnected = isConnectedToInternet();

        if (isConnected) {
            List<FloorPlan> plans = localStorage.getLocalStoragePlans();

            for (FloorPlan plan : plans) {
                updatingServer(plan);
                System.out.println(plan.getPlanName() + " synced with server successfully.");
            }

            localStorage.localStorageClear();
        } else {
            throw new IllegalStateException("Synchronization failed due to no internet connection. Please try again later.");
        }
    }

    /**
     * Checks if the device is connected to the internet. (Assuming logic of connection is already implemented)
     * 
     * @return true if connected to the internet, false otherwise.
     */
    private boolean isConnectedToInternet() {
        if (Math.random() < 0.5) {
            return false;
        }
        return true;
    }

    /**
     * Updates the floor plan on the server. (Assuming server uploading is aleready implemented)
     * 
     * @param plan The floor plan to be updated on the server.
     */
    public void updatingServer(FloorPlan plan) {
        System.out.println(plan + " : Updating on server...");
    }
}
