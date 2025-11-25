classDiagram

    class Game {
        <<controller>>
        +start()
        +processInput(input)
        +updateGameState()
        +endGame()
        %% Status: In progress
    }

    class Player {
        -health : int
        -stamina : int
        -cameraBattery : int
        +moveTo(room)
        +updateStats()
        %% Status: In progress
    }

    class Room {
        -description : String
        -exits : Map
        +enter(player)
        %% Status: In progress
    }

    class Item {
        -name : String
        -description : String
        +use(player)
        %% Status: In progress
    }

    class Event {
        -isTimed : bool
        +checkTrigger(player, room)
        +execute()
        %% Status: Stretch goal / Not sure how to do timed events yet
    }

    %% Associations
    Game --> Player : manages >
    Game --> Room : contains >
    Player --> Room : moves >
    Room --> Item : holds >
    Player --> Item : inventory >
    Room --> Event : triggers >
    Game --> Event : global >

    %% Dependencies
    Player ..> Event : affected by >
    Item ..> Player : modifies >
    Event ..> Player : checks >
    Event ..> Room : checks >
