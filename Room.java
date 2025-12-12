/**
 * 
 * Represents a location in the game world. Each room contains
 * a description, possible exits, items that can be collected,
 * and events that may trigger when the player enters.
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Room {

    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items;
    private ArrayList<Event> events;

    /**
     * Constructs a Room with a description.
     * @param description the text describing the room
     */
    public Room(String description) {
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    /**
     * Adds an exit to another room.
     * @param direction direction of exit (e.g., "north")
     * @param room the connected room
     */
    public void addExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public Room getExit(String direction){
        return exits.get(direction);
    }

    /**
     * Places an item in the room.
     * @param item the item to add
     */
    public void addItem(Item item) {
        items.add(item);
    }

    public Item takeItem(String name){
        for (Item i : items){
            items.remove(i);
            return i;
        }
        return null;
    }

    /**
     * Adds an event that can occur in this room.
     * @param event the event to add
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Triggered when the player enters this room.
     * @param player the entering player
     */
    public void enter(Player player) {
        System.out.println("\n== " + description + " ==");
        describe();
    }

    public void describe() {
        if (!items.isEmpty()) {
            System.out.print("Items here: ");
            items.forEach(i -> System.out.print(i.getName() + " "));
            System.out.println();
        }

        System.out.print("Exits: ");
        exits.keySet().forEach(d -> System.out.print(d + " "));
        System.out.println();
    }

    public void updateEvents(Player player, int gameTime) {
        for (Event e : events) {
            if (e.checkTrigger(player, this, gameTime)) {
                e.execute(player, this);
            }
        }
    }
}
