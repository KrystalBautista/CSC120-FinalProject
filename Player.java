/**
 * 
 * Represents the player character exploring the game world.
 * Stores player statistics (health, stamina, camera battery) 
 * and manages the player's inventory and interactions.
 */

import java.util.ArrayList;

public class Player {

    private String name;
    private int health;
    private int stamina;
    private int cameraBattery;
    private ArrayList<Item> inventory;

    /**
     * Constructs a new Player with default stats.
     * @param name the player's chosen name
     */
    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.stamina = 100;
        this.cameraBattery = 100;
        this.inventory = new ArrayList<>();
    }

    /**
     * Moves the player to a different room.
     * @param room the room to move into
     */
    public void moveTo(Room room) {
    }

    /**
     * Adds an item to the player's inventory.
     * @param item the item to add
     */
    public void addItem(Item item) {
        inventory.add(item);
    }

    /**
     * Uses an item from the player's inventory by name.
     * @param itemName the name of the item
     */
    public void useItem(String itemName) {
    }

    /**
     * Updates player stats as time passes.
     */
    public void updateStats() {
    }
}
