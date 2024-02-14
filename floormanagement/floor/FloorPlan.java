package floormanagement.floor;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDateTime;
import floormanagement.users.Admin;
import floormanagement.floor.Room;

/**
 * Represents a floor plan in the floor management system.
 */
public class FloorPlan {
    private final int id;
    public final String planName;
    private float version;
    private String description;
    private final List<Room> rooms;
    private final LocalDateTime creationDate;
    private final Admin creator;
    private LocalDateTime lastModified;
    private final RoomCache roomsMap1;
    private int priority;

    /**
     * Constructs a FloorPlan object with the specified attributes.
     * 
     * @param id            The unique identifier of the floor plan.
     * @param planName      The name of the floor plan.
     * @param version       The version of the floor plan.
     * @param description   The description of the floor plan.
     * @param rooms         The list of rooms in the floor plan.
     * @param creator       The admin who created the floor plan.
     * @param lastModified  The last modified date of the floor plan.
     * @param priority      The priority of the floor plan.
     */
    public FloorPlan(int id, String planName, float version, String description, List<Room> rooms,  Admin creator, LocalDateTime lastModified, int priority) {
        this.id = id;
        this.planName = planName;
        this.version = version;
        this.description = description; 
        this.rooms = new ArrayList<>(); 
        this.creationDate = LocalDateTime.now();
        this.creator = creator;
        this.lastModified = lastModified;
        this.roomsMap1 = new RoomCache();
        this.priority = priority;
    }

    /**
     * Retrieves the unique identifier of the floor plan.
     * 
     * @return The floor plan's identifier.
     */
    public int getFloorPlanId() {
        return this.id;
    }

    /**
     * Retrieves the name of the floor plan.
     * 
     * @return The floor plan's name.
     */
    public String getPlanName() {
        return this.planName;
    }

    /**
     * Retrieves the version number of the floor plan.
     * 
     * @return The floor plan's version number.
     */
    public float getVersion() {
        return this.version;
    }

    /**
     * Retrieves the last modified date of the floor plan.
     * 
     * @return The last modified date of the floor plan.
     */
    public LocalDateTime getLastModified() {
        return this.lastModified;
    }

    /**
     * Retrieves the list of rooms in the floor plan.
     * 
     * @return The list of rooms in the floor plan.
     */
    public List<Room> getRooms() {
        return this.rooms;
    }

    /**
     * Retrieves the creation date of the floor plan.
     * 
     * @return The creation date of the floor plan.
     */
    public LocalDateTime getCreatedDate() {
        return this.creationDate;
    }

    /**
     * Retrieves the admin who created the floor plan.
     * 
     * @return The admin who created the floor plan.
     */
    public Admin getCreator() {
        return this.creator;
    }

    /**
     * Retrieves the description of the floor plan.
     * 
     * @return The description of the floor plan.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the floor plan.
     * 
     * @param description The new description of the floor plan.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the priority of the floor plan.
     * 
     * @param priority The new priority of the floor plan.
     */
    public void setPriority(int priority){
        this.priority = priority;
    }

    /**
     * Retrieves the priority of the floor plan.
     * 
     * @return The priority of the floor plan.
     */
    public int getPriority(){
        return this.priority;
    }

    /**
     * A cache for storing rooms by their unique identifiers.
     */
    class RoomCache {
        private Map<String, Room> roomMap;
        
        /**
         * Constructs a RoomCache object with an empty room map.
         */
        public RoomCache() {
            this.roomMap = new HashMap<>();
        }
    
        /**
         * Retrieves the room associated with the specified key from the cache.
         * 
         * @param key The key associated with the room.
         * @return The room associated with the key, or null if not found.
         */
        public Room get(String key) {
            return roomMap.get(key);
        }
    
        /**
         * Adds a room to the cache with the specified key.
         * 
         * @param key  The key to associate with the room.
         * @param data The room to be added.
         */
        public void add(String key, Room data) {
            roomMap.put(key, data);
        }
    
        /**
         * Removes the room associated with the specified key from the cache.
         * 
         * @param key The key associated with the room to be removed.
         */
        public void remove(String key) {
            roomMap.remove(key);
        }
    
        /**
         * Clears the cache, removing all rooms.
         */
        public void clear() {
            roomMap.clear();
        }
    }

    /**
     * Increments the version number of the floor plan and updates the last modified date.
     */
    public void uploadPlan() {
        this.version++;
        this.lastModified = LocalDateTime.now();
        System.out.println(this.version + " new version uploaded successfully on " + this.lastModified);
    }

    /**
     * Adds a room to the floor plan and updates the room cache.
     * 
     * @param room The room to be added.
     */
    public void addRoom(Room room) {
        rooms.add(room);
        roomsMap1.add("Room number - " + room.getId(), room);
    }

    /**
     * Removes a room from the floor plan and updates the room cache.
     * 
     * @param room The room to be removed.
     */
    public void removeRoom(Room room) {
        rooms.remove(room);
        roomsMap1.remove("Room number - " + room.getId());
    }

    /**
     * Retrieves a room from the floor plan by its unique identifier.
     * 
     * @param roomId The unique identifier of the room.
     * @return The room with the specified identifier, or null if not found.
     */
    public Room getRoomById(int roomId) {
        Room currentRoom = roomsMap1.get("Room number - " + roomId);
        if (currentRoom != null) {
            System.out.println("Room found");
            return currentRoom;
        } else {
            for (Room room : rooms) {
                if (room.getId() == roomId) {
                    return room;
                }
            }
        }
        return null;
    }
}