import java.util.Scanner;

/**
 * 
 * Controls the primary flow of the text-based horror game.
 * Responsible for starting the game, processing player input,
 * updating game state, and determining game endings.
 */
public class Game {

    private Player player;
    private Room currentRoom;
    private boolean isRunning;
    private int globalTimer;

    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a new Game instance.
     */
    public Game() {
        this.globalTimer = 0;
        this.isRunning = true;
    }

    /**
     * Begins the game and prepares initial state.
     */
    public void start() {
        System.out.println("Enter your name: ");
        player = new Player(name);

        /**
         * TEMP build basic world
         */
        Room foyer = new Room("The foyer is dusty and cold. A broken chandelier sways slightly.");
        Room hallway = new Room("A dim narrow hallway. Faint creaking can be heard at the end of the hall.");

        foyer.addExit("north", hallway);
        hallway.addEvent(new Event(true,5));

        currentRoom = foyer;
        currentRoom.enter(player);

        loop();
    }

    public void loop(){
        while(isRunning && player.getHealth() > 0){
            System.out.println("\n> ");
            String input = scanner.NextLine();
            processInput(input);

            updateGameState();
            globalTimer++;
        if (player.getHealth() <= 0){
            endGame("You succumbed to your injuries...");
        }
        }
    }

    /**
     * Processes a command entered by the player.
     * @param input the command string
     */
    public void processInput(String input) {
        String cmd = input.toLowerCase().trim();

        if (cmd.startsWith("go ")) {
            String direction = cmd.substring(3);
            Room next = currentRoom.getExit(direction);

            if (next != null) {
                currentRoom = next;
                currentRoom.enter(player);
            } else {
                System.out.println("You can't go that way.");
            }
        }
        else if (cmd.startsWith("take ")) {
            String itemName = cmd.substring(5);
            Item item = currentRoom.takeItem(itemName);
            if (item != null) {
                System.out.println("You picked up the " + itemName + ".");
                player.addItem(item);
            } else {
                System.out.println("There's no " + itemName + " here.");
            }
        }
        else if (cmd.startsWith("use ")) {
            String itemName = cmd.substring(4);
            player.useItem(itemName);
        }
        else if (cmd.equals("look")) {
            currentRoom.describe();
        }
        else if (cmd.equals("quit")) {
            endGame("You chose to leave the house early...");
        }
        else {
            System.out.println("Unrecognized command.");
        }
    }

    /**
     * Updates game elements such as player stats and active events.
     */
    public void updateGameState() {
        player.updateStats();

        currentRoom.updateEvents(player, globalTimer);
    }

    /**
     * Ends the game and displays the final outcome.
     */
    public void endGame(String reason) {
        System.out.println("\n=== GAME OVER ===");
        System.out.println(reason);
        isRunning = false;
    }

    /**
     * Entry point of the program.
     */
    public static void main(String[] args) {
        new Game().start();
    }
}
