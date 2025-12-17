This file will contain documentation for all commands available in your game.

Note:  It's a good idea to also make this list available inside the game, in response to a `HELP` command.


# SPOILER ALERT

If your game includes challenges that must be overcome to win, also list them below.

Basic Commands:
-HELP displays a list of all available commands
-STATUS shows current player stats: health, stamina, and camera battery
-LOOK re-describes the current room, including visible items and exits 
-QUIT ends the game immediately

Actions:
-GO <direction> moves the player in a specified direction, some exits may be blocked until certain conditions are met
-TAKE <item> picks up an item from the current room and adds it to your inventory
-USE <item> uses an item from your inventory (flashlight restores camera battery, snack restores stamina)
-FIGHT attacks an enemy if one is present in the room. Defeating the enemy puts the key necessary to escape in your inventory

Game Layout:
-Foyer: starting room 
-Hallway: dangerous area with an enemy encounter 
-Exit: locked door that requires a key to escape

Winning the game:
-Walk into the hallway
-Defeat the enemy with the FIGHT action (x3)
-Go to the locked door, you'll get through and escape with the footage

Losing Conditions:
-Health reaches 0
-Camera battery reaches 0
-Quitting the game