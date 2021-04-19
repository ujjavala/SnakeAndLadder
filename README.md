# Welcome to SnakeAndLadder game

Our application mimics the popular snake and ladder board game and has support for multiple players.

## Understanding the flow.

### Entry

_Game.java_ :
The entry point/main method of the application is in here. 
From here we are injecting various input services and board services to our GameService in order to  get our final winner

### Services

_GameInputService.java_ :
This class is responsible to read the input from the input. Currently, we are supporting input form console.
This is how the constructor looks like:
 

_BoardService_ :
We initialise the board in here. It takes GameInputService as the constructor param
``
        BoardService(GameInputService gameInputService)
``

_DiceService_ :
This is mainly responsible to roll a fair or unfair dice.

_GameService_ :
This form the  brain of our application. It is responsible to move the players across the board and also decides eho the final winner is.
This is how the constructor looks like:
``
public GameService(BoardService boardService)
``

### Models

We have the following models

1. Board: 
``
public Board(int size, List<Snake> snakes, List<Ladder> ladders, Map<String, Integer> playerPieces)
``

1. Ladder:
``
public Ladder(int start, int end) 
``

1. Player:
``
 public Player(String name)
``

1. Snake:
``
    public Snake(int start, int end)
``


### Util

Currently, we have one util class for storing constants.