package floormanagement;

import java.util.List; 
import java.util.ArrayList;
import java.time.LocalDateTime;
import floormanagement.floor.Room;
import floormanagement.users.Admin;
import floormanagement.floor.FloorPlan;
import floormanagement.booking.Conflict;

/**
 * Main class representing the Floor Plan Management System.
 */
public class FloorPlanManagementSystem {
    /**
     * Main method to demonstrate the floor plan management system.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Admin admin = new Admin(1, "<Admin name>", "<Admin password>");

        FloorPlan plan1 = new FloorPlan(1, "Floor1", 1.0, "This is floor 1", new ArrayList<Room>(), admin, LocalDateTime.now(), 1);
        FloorPlan plan2 = new FloorPlan(1, "Floor1", 1.1, "This is floor 1", new ArrayList<Room>(), admin, LocalDateTime.now(), 2);
        FloorPlan plan3 = new FloorPlan(1, "Floor2", 1.0, "This is floor 2", new ArrayList<Room>(), admin, LocalDateTime.now(), 1);

        try {
            Admin.resolveMergeConflict(plan1, plan2);
        } catch (Exception e) {
            System.err.println("Floor plan didn't get updated due to :- " + e.getMessage());
        }
    }
}
