# PolyU-The-Jungle-Game
Course project of PolyU COMP3222 *Software Design Principle*. A Java written Jungle Game that accepts input through console and is displayed with a GUI.

## **Author**
Douglas Liu \
GitHub: <https://github.com/DouglasLiuGamer>

Venn Wen \
GitHub: <https://github.com/Venn006>

Finch Zhu \
GitHub: <https://github.com/FinchZHU>

Liam Yang \
GitHub: <https://github.com/LiamYANG>

## **Description**
**The Jungle Game** is a traditional Chinese board game, where two players take turns to move their chesses. Here is the detailed description:

### **Board**
A board of The Jungle Game consists of 7x9 squares. Some of the squares have unique properties, as illustrated below:

| Special Square | Property|
| --- | --- |
| Den | The game finishes when a player moves his or her chess in to the opponent's den. |
| Trap | An animal within the trap can be captured by any other opponent's animal. |
| River | Some animal's movement will be affect by the river. See the below description.

### **Animals**
Each player has eight different animals, each with a different rank. The list of animals and their ranks is listed as below:

| Animal | Rank |
| --- | --- |
| Elephant | 8 |
| Lion | 7 |
| Tiger | 6 |
| Leopard | 5 |
| Wolf | 4 |
| Dog | 3 |
| Cat | 2 |
| Rat | 1 |

### **Movements**
In each turn, the player must move an animal. Each animal moves one square horizontally or vertically (not diagonally). An animal may not move to its own den.

Related to the river square, the rat is the only animal that is allowed to go onto a river square. The lion and tiger may jump over a river by moving from one edge of the river to the other edge, horizontally or vertically, unless their is a rat (whether friendly or not) on any of the intervening water squares.

### **Capturing**
Animals capture the opponent's pieces by moving on to them. An animal can capture any enemy piece which has the same or lower rank, with the following rules:

1. A rat may capture an elephant but an elephant may not capture a rat.
2. If a rat is in a water square, it cannot capture animals on land. Similarly, a rat on land cannot capture the other rat in a water square.
3. An animal can capture any opponent's piece in any trap.
4. The lion and tiger may capture through jumping.

### **Objective**
The goal of the game is to move an animal onto opponent's den, or capture all opponent's animals.

## **Usage Manual**
### **Building**
All the Java source codes are in the folder /src. The `main` method is in Game.java.

### **Input**
All the input are through the console. The game will display appropriate prompts when input is needed.

Players are required to input their names when the game starts.

To move an animal, input the corresponding coordinates with a `->` to indicate directions. Like:
```
A2->C5
```

To save the game, input `Save` with a file name following.

To load the game, input `Load` with the file name following.

To exit the game, input `Exit`.

### **Display**
A GUI of the game board will be shown during the game.

Animals are represented by their name. Animals of the current player are in color black while the opponent's in gray.

Dens are in pink, rivers are in cyan and traps are in orange.

## **Update Plan**
Currently there is no update plan for this project, since the course has finished.

## **License**
This source code is unlicensed. Any user can distribute or modify it freely, although a reference to the authors would be appreciated.