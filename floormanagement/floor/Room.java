package floormanagement.floor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a room within a floor plan.
 * This class provides functionality to manage room reservations and check availability.
 */
public class Room {
    private final int id;
    private final int capacity;
    private final String location;
    private final String description;
    private final List<ReservingRoom> reservedList;
    private boolean isBooked = false; // This should not be final as it can change

    /**
     * Constructs a new Room object with the provided parameters.
     *
     * @param id          The unique identifier for the room.
     * @param description A brief description of the room.
     * @param capacity    The maximum capacity of the room.
     * @param location    The location of the room.
     */
    public Room(int id, String description, int capacity, String location) {
        this.id = id;
        this.capacity = capacity;
        this.location = location;
        this.description = description;
        this.reservedList = new ArrayList<>();
    }

    /**
     * Retrieves the unique identifier of the room.
     *
     * @return The room's identifier.
     */
    public int getRoomId() {
        return this.id;
    }

    /**
     * Marks the room as booked.
     */
    public void markBooked() {
        this.isBooked = true;
    }

    /**
     * Checks if the room has enough capacity for the given number of members.
     *
     * @param totalMembers The total number of members expected to use the room.
     * @return True if the room has enough capacity, false otherwise.
     */
    public boolean hasEnoughCapacity(int totalMembers) {
        return this.capacity >= totalMembers;
    }

    /**
     * Retrieves the description of the room.
     *
     * @return The description of the room.
     */
    public String getRoomDescription() {
        return description;
    }

    /**
     * Checks if the room is available during the specified time period.
     *
     * @param start The start time of the period to check.
     * @param end   The end time of the period to check.
     * @return True if the room is available, false if it's already reserved during the specified period.
     */
    public boolean isAvailable(LocalDateTime start, LocalDateTime end) {
        for (ReservingRoom reserved : reservedList) {
            if (isConflicting(start, end, reserved.getStartTime(), reserved.getEndTime())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if two time periods overlap.
     *
     * @param start1 The start time of the first period.
     * @param end1   The end time of the first period.
     * @param start2 The start time of the second period.
     * @param end2   The end time of the second period.
     * @return True if the periods overlap, false otherwise.
     */
    private boolean isConflicting(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2) {
        return !(start1.isAfter(end2) || end1.isBefore(start2));
    }
}
