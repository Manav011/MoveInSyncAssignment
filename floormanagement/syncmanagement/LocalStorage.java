package floormanagement.syncmanagement;

import java.util.List;
import java.util.ArrayList;
import floormanagement.floor.FloorPlan;

/**
 * Manages local storage of floor plans.
 */
class LocalStorage {
    private List<FloorPlan> localStorage;

    /**
     * Constructs a LocalStorage object with an empty list of floor plans.
     */
    public LocalStorage() {
        this.localStorage = new ArrayList<>();
    }

    /**
     * Saves a floor plan locally.
     * 
     * @param floorPlan The floor plan to be saved.
     */
    public void saveLocally(FloorPlan floorPlan) {
        localStorage.add(floorPlan);
        System.out.println(floorPlan.getPlanName() + " saved successfully in the local storage.");
    }

    /**
     * Retrieves a copy of the list of floor plans stored locally.
     * 
     * @return A copy of the list of floor plans stored locally.
     */
    public List<FloorPlan> getLocalStoragePlans() {
        return new ArrayList<>(localStorage);
    }

    /**
     * Clears the local storage after uploading plans to the server.
     */
    public void storageClear() {
        System.out.println("Uploading local plans to server and clearing local storage.");
        localStorage.clear();
    }
}
