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
        switch (name.toLowerCase()) {
            case "flashlight":
                System.out.println("You turn on the flashlight, it makes you feel slightly better.");
                player.chargeCamera(10);
                break;
            
            case "snack" :
                System.out.println("You eat the snack and regain some strength.");
                player.restoreStamina(15);
                break;
            
            default :
                System.out.println("Nothing happens.");
        }
    }

    public String getName(){
        return name;
    }
}
