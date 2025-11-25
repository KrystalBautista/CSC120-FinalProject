/**
 * 
 * Represents special interactions or timed occurrences in the game,
 * such as scares, environmental changes, or danger sequences.
 */
public class Event {

    private boolean isTimed;

    /**
     * Constructs an Event.
     * @param isTimed whether the event is time-based
     */
    public Event(boolean isTimed) {
        this.isTimed = isTimed;
    }

    /**
     * Checks whether the event should trigger.
     * @param player the player involved
     * @param room the room where the event occurs
     * @return true if conditions are met, false otherwise
     */
    public boolean checkTrigger(Player player, Room room) {
        return false;
    }

    /**
     * Executes the event's effect.
     * @param player the affected player
     * @param room the room where it triggers
     */
    public void execute(Player player, Room room) {
    }
}
