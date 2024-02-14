package floormanagement.users;

import floormanagement.encryption.Encryptdecrypt;

/**
 * Represents a user in the floor management system.
 */
public class User {
    private final int id;
    private final String username;
    private final String hashedPassword;
    private final String role = "User";

    /**
     * Constructs a User object with the given ID, username, and password.
     * 
     * @param id       The unique identifier of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.hashedPassword = Encryptdecrypt.encrypt(password);
    }

    /**
     * Retrieves the ID of the user.
     * 
     * @return The ID of the user.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Retrieves the username of the user.
     * 
     * @return The username of the user.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Retrieves the role of the user.
     * 
     * @return The role the user.
     */
    public String getRole() {
        return this.role;
    }
}
