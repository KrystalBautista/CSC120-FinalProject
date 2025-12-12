classDiagram

    class Game {
        <<controller>>
        -Player player
        -Room currentRoom
        -boolean isRunning
        -int globalTimer
        +start()
        +loop()
        +processInput(input)
        +updateGameState()
        +endGame(reason)
        %% Status: In progress
    }

    class Player {
        -name : String
        -health : int
        -stamina : int
        -cameraBattery : int
        -inventory : ArrayList<item>
        +move(direction)
        +addItem(item)
        +useItem(name)
        +updateStats()
        %% Status: In progress
    }

    class Room {
        -description : String
        -exits : HashMap<String, Room>
        -items : ArrayList<item>
        -events : ArrayList<Event>
        +enter(player)
        +describe()
        +takeItem(itemName)
        %% Status: In progress
    }

    class Item {
        -name : String
        -description : String
        +use(player)
        %% Status: In progress
    }

    class Event {
        -isTimed : boolean
        -triggerTime : int
        -hasTriggered : boolean
        +checkTrigger(player, room, gameTime)
        +execute(player, room)
        %% Status: In progress
    }

    %% Associations
    Game --> Player : manages >
    Game --> Room : controls current >
    Player --> Room : moves >
    Room --> Item : contains >
    Player --> Item : inventory >
    Room --> Event : hosts >
    Game --> Event : updates >

    %% Dependencies
    Item ..> Player : modifies >
    Event ..> Player : affects >
    Event ..> Room : tied to >
