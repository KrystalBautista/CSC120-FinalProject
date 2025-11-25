/**
 * 
 * Represents an object that the player can collect and use.
 * Items may later affect player stats or unlock new paths.
 */
public class Item {

    private String name;
    private String description;

    /**
     * Constructs an Item with a name and description.
     * @param name the name of the item
     * @param description what the item looks like or does
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Uses the item on the player.
     * @param player the player using the item
     */
    public void use(Player player) {
    }
}
