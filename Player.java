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
    private int enemyHealth = 3;
    private boolean enemyAlive = true;

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
        stamina = Math.max(0, stamina - 1);
        cameraBattery = Math.max(0, cameraBattery - 1);

        if (stamina == 0) {
            health --;
        }
    }

    public void damage(int amount){
        health = Math.max(0, health - amount);
    }

    public void restoreStamina(int amount){
        stamina = Math.min(100, stamina + amount);
    }

    public void chargeCamera(int amount){
        cameraBattery = Math.min(100, cameraBattery + amount);
    }

    public int getHealth(){
        return health;
    }

    public int getCameraBattery(){
        return cameraBattery;
    }

    public void printStatus(){
        System.out.println("""
                === STATUS ===
                Health: %d
                Stamina: %d
                Camera: %d
                """.formatted(health,stamina,cameraBattery));
    }

    public boolean fightEnemy(Room hallway){
        if(!enemyAlive){
            System.out.println("The creature is already defeated.");
            return false;
        }
       System.out.println("You strike the creature!");
       enemyHealth--;
       damage(15);

       if(enemyHealth <= 0){
            enemyAlive = false;
            System.out.println("The creature collapses into dust. It drops a key and you take it.");
            addItem(new Item("key","A rusted key dropped by the creature."));
            hallway.unblockExit("north");
            return true;
       }else{
            System.out.println("The creature shrieks and strikes back!");
       }
       return false;
    }

    public boolean hasItem(String name){
        for(Item item : inventory){
            if(item.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
       return false;
    }
}

