package floormanagement.users;

import floormanagement.floor.FloorPlan;
import floormanagement.booking.Conflict;
import floormanagement.encryption.Encryptdecrypt;

/**
 * Represents an administrator user in the floor management system.
 */
public class Admin extends User {
    private final int id;
    private final String username;
    private final String password;
    private final String role = "Admin";

    /**
     * Constructs an Admin object with the given ID, username, and password.
     * 
     * @param id       The unique identifier of the administrator.
     * @param username The username of the administrator.
     * @param password The password of the administrator.
     */
    public Admin(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = Encryptdecrypt.encrypt(password);
    }

    /**
     * Retrieves the ID of the administrator.
     * 
     * @return The ID of the administrator.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the username of the administrator.
     * 
     * @return The username of the administrator.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the role of the administrator.
     * 
     * @return The role of the administrator.
     */
    public String getRole() {
        return role;
    }

    /**
     * Resolves merge conflicts between two floor plans.
     * 
     * @param previousPlan The previous version of the floor plan.
     * @param currentPlan  The current version of the floor plan.
     * @throws IllegalArgumentException If either previousPlan or currentPlan is null.
     */
    public void resolveMergeConflict(FloorPlan previousPlan, FloorPlan currentPlan) throws IllegalArgumentException {
        if (previousPlan == null || currentPlan == null) {
            throw new IllegalArgumentException("One or both FloorPlans are null");
        }
        Conflict.resolve(previousPlan, currentPlan);
    }
}
