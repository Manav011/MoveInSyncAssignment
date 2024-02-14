package floormanagement.booking;

import floormanagement.floor.FloorPlan;
import floormanagement.encryption.Encryption;

/**
 * Represents a conflict resolution mechanism for handling conflicting floor plans.
 * This class provides methods to resolve conflicts between two floor plans.
 */
public class Conflict {

    /**
     * Resolves a conflict between two floor plans based on priority and timestamp.
     *
     * @param currentPlan  The current version of the floor plan.
     * @param previousPlan The previous version of the floor plan.
     * @throws IllegalStateException If admin intervention is required but admin is not provided.
     */
    public static void resolve(FloorPlan currentPlan, FloorPlan previousPlan) throws IllegalStateException {
        
        int prioritize = currentPlan.getPriority() - previousPlan.getPriority();
            
        System.out.print("Resolving Conflict :- ");
        if (prioritize > 0) {
            System.out.println("Previous Task has more priority, So previous plan will be synced");
            previousPlan.uploadPlan();

        } else if (prioritize < 0) {
            System.out.println("Current Task has more priority, So current plan will get uploaded");
            currentPlan.uploadPlan();

        } else {
            int timePriority = currentPlan.getLastModified().compareTo(previousPlan.getLastModified());

            System.out.println("Resolving Conflict :- ");
            if (timePriority > 0) {
                System.out.println("Previous Task gets priority based on timestamp");
                previousPlan.uploadPlan();

            } else if (timePriority < 0) {
                System.out.println("Current Task gets priority based on timestamp");
                currentPlan.uploadPlan();

            } else {
                throw new IllegalStateException("Admin intervention required");
            }
        }
    }
}
