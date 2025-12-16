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
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        player = new Player(name);

        /**
         * Build world
         */
        Room foyer = new Room("The foyer is dusty and cold. A broken chandelier sways slightly. You've witnessed the death of your friend at the hands of something sinister that resides in the home. You must escape with the footage.");
        Room hallway = new Room("A dim narrow hallway. The air feels heavy.");
        Room exit = new Room("A locked door leading outside. You need a key to escape.");

        foyer.addExit("north", hallway);
        hallway.addExit("south", foyer);
        hallway.addExit("north", exit);
        hallway.addExit("north", exit);
        hallway.blockExit("north");

        foyer.addItem(new Item("flashlight", "A dim but useable flashlight"));
        hallway.addItem(new Item("snack", "A half eaten energy bar"));


        hallway.addEvent(new Event(true,5));
        hallway.addEvent(new Event(true, 10));

        currentRoom = foyer;
        currentRoom.enter(player);

        loop();
    }

    public void loop(){
        while(isRunning){
            System.out.print("\n>");
            String input = scanner.nextLine();
            processInput(input);

            updateGameState();
            globalTimer++;

            if (player.getHealth() <= 0){
            endGame("You collapse as darkness consumes you..");
            }
            if (player.getCameraBattery() <= 0){
                endGame("Your camera dies. The footage is lost. Nobody will believe you.");
            }
        }
    }

    /**
     * Processes a command entered by the player.
     * @param input the command string
     */
    private void processInput(String input) {
        String cmd = input.toLowerCase().trim();

        if (cmd.equals("help")) {
            showHelp();
    }   else if (cmd.equals("status")){
            player.printStatus();
    }   else if (cmd.startsWith("go ")){
            String direction = cmd.substring(3);
            Room next = currentRoom.getExit(direction);
            if (next != null){
                currentRoom = next;
                currentRoom.enter(player);

                if(currentRoom.getDescription().contains("hallway")){
                    System.out.println("A supernatural presence blocks your path...");
                }

                if(currentRoom.getDescription().contains("locked")){
                    if(player.hasItem("key")){
                        endGame("You unlock the door and escape with the footage. It goes viral.");
                    }else{
                        System.out.println("The door is locked. You need a key");
                    }
                }
            }else{
                System.out.println("You cant go that way.");
            }
        }else if (cmd.startsWith("take ")){
            String itemName = cmd.substring(5);
            Item item = currentRoom.takeItem(itemName);
            if (item != null){
                player.addItem(item);
                System.out.println("You picked up the " + itemName + ".");
            }else{
                System.out.println("That item isnt here");
            }
        }else if (cmd.startsWith("use ")){
            player.useItem(cmd.substring(4));
        }else if (cmd.equals("look")){
            currentRoom.describe();
        }else if (cmd.equals("quit")){
            endGame("You abandon the investigation.");
        }else if(cmd.equals("fight")){
            if(currentRoom.getDescription().contains("hallway")){
                player.fightEnemy(currentRoom);
            }else{
            System.out.println("There is nothing to fight here.");
            }
        }
    }

    /**
     * Updates game elements such as player stats and active events.
     */
    public void updateGameState() {
        player.updateStats();
        currentRoom.updateEvents(player, globalTimer);
    }


    private void showHelp(){
        System.out.println("""
                Commands:
                GO <direction>
                LOOK
                TAKE <item>
                USE <item>
                STATUS
                HELP
                QUIT
                """);
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
