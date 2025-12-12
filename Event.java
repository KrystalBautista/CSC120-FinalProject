/**
 * 
 * Represents special interactions or timed occurrences in the game,
 * such as scares, environmental changes, or danger sequences.
 */
public class Event {

    private boolean isTimed;
    private int triggerTime;
    private boolean hasTriggered;

    /**
     * Constructs an Event.
     * @param isTimed whether the event is time-based
     */
    public Event(boolean isTimed, int triggerTime) {
        this.isTimed = isTimed;
        this.triggerTime = triggerTime;
        this.hasTriggered = false;
    }

    /**
     * Checks whether the event should trigger.
     * @param player the player involved
     * @param room the room where the event occurs
     * @return true if conditions are met, false otherwise
     */
    public boolean checkTrigger(Player player, Room room, int gameTime) {
        if(hasTriggered) return false;

        if (isTimes && gameTime >= triggerTime){
            return true;
        }
        return false;
    }

    /**
     * Executes the event's effect.
     * @param player the affected player
     * @param room the room where it triggers
     */
    public void execute(Player player, Room room) {
        hasTriggered = true;
        System.out.println("\nA loud thud echoes through the hallway. Something is here with you...");
        player.heal(-10); // small damage
    }
}
