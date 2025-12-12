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
        for (Item item : inventory){
            if (item.getName().equalsIgnoreCase(itemName)){
                item.use(this);
                return;
            }
        }
        System.out.println("You dont have that item.");
    }

    /**
     * Updates player stats as time passes.
     */
    public void updateStats() {
        stamina -= 1;
        cameraBattery -= 1;

        if (stamina <= 0) {
            health -= 1; //exhaustion damage
            stamina = 0
        }
    }

    public int getHealth(){
        return health;
    }

    public void heal(int amount){
        health = Math.min(100, stamina + amount);
    }

    public void restoreStamina(int amount) { stamina = Math.min(100, stamina + amount); }

    public void chargeCamera(int amount) { cameraBattery = Math.min(100, cameraBattery + amount); }
}



