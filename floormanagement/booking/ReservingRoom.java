package floormanagement.booking;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import floormanagement.floor.Room;
import floormanagement.users.Admin;

/**
 * Represents the process of reserving a room for a specific event or activity.
 * This class handles booking rooms, checking availability, and retrieving rooms from a database.
 */
public class ReservingRoom {

    private final int id;
    private final Admin creator;
    private final String description;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final int totalMembers;
    private List<Room> rooms;

    /**
     * Constructs a new ReservingRoom object with the provided parameters.
     *
     * @param id            The unique identifier for this reservation.
     * @param room          The room to be reserved.
     * @param creator       The admin responsible for creating this reservation.
     * @param description   A brief description of the reservation.
     * @param startTime     The start time of the reservation.
     * @param endTime       The end time of the reservation.
     * @param totalMembers  The total number of members expected for the reservation.
     */
    public ReservingRoom(int id, Room room, Admin creator, String description, String startTime, String endTime,
                            int totalMembers) {
        this.id = id;
        this.creator = creator;
        this.startTime = LocalDateTime.parse(startTime);
        this.endTime = LocalDateTime.parse(endTime);
        this.description = description;
        this.totalMembers = totalMembers;
    }

    /**
     * Retrieves the start time of the reservation.
     *
     * @return The start time of the reservation.
     */
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * Retrieves the end time of the reservation.
     *
     * @return The end time of the reservation.
     */
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Adds a room booking to the reservation.
     *
     * @param newBooking The room to be added to the reservation.
     */
    public void addBooking(Room newBooking) {
        rooms.add(newBooking);
    }

    /**
     * Books the room by checking availability and capacity, then marking it as booked if suitable.
     */
    public void bookRoom() {
        this.rooms = getRoomsFromDB();
        for (Room room : rooms) {
            if (room.isAvailable(startTime, endTime) && room.hasEnoughCapacity(totalMembers)) {
                System.out.println("Booked Room Id: " + room.getRoomName());
                room.markBooked();
            } else {
                System.out.print("Booking failed due to :- ");
                if (!room.isAvailable(startTime, endTime)) {
                    System.out.println(room.getRoomId() + " is not Available");
                } else {
                    System.out.println("Room with enough capacity is not available");
                }
            }
        }
    }

    /**
     * Retrieves the list of available rooms from the database.
     *
     * @return A list of available rooms.
     */
    public List<Room> getRoomsFromDB() {
        try {
            Connection c = getConnection("<URL to Database>");
            String getData = "SELECT Room FROM floorPlan";

            try {
                return executeQuery(getData);
            } catch (SQLException e) {
                System.err.println("Couldn't load Rooms from Database due to :- " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Couldn't connect to DataBase :- " + e.getMessage());
        }
        return null;
    }
}
