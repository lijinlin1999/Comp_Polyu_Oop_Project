package JungleGame.Game;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;

public class Board {
    public Board() {}

    public int getWidth() {
        return cells.get(0).size();
    }

    public int getHeight() {
        return cells.size();
    }

    public Cell getCell(int row, int column) {
        if (row < 0 || row >= getHeight() || column < 0 || column >= getWidth()) {
            return null;
        }
        return cells.get(row).get(column);
    }

    boolean save(String saveFile) {
        // return false when fails
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile));
            // write the current player index
            writer.write(String.valueOf(Game.getCurPlayerIndex()));
            writer.newLine();

            // write  player0's information
            Player player0 = Game.getPlayers().get(0);
            writer.write(player0.getName() + " " + player0.getAnimalNum());
            writer.newLine();

            // write player1's information
            Player player1 = Game.getPlayers().get(1);
            writer.write(player1.getName() + " " + player1.getAnimalNum());
            writer.newLine();

            // write the number of row and column
            writer.write(cells.size() + " " + cells.get(0).size());
            writer.newLine();
            // write the information about each cell
            for(ArrayList<Cell> rowCell : cells){
                for(Cell cell : rowCell){

                    // write the information about cell
                    writer.write(cell.getType());
                    if(cell instanceof  DenCell){
                        writer.write((((DenCell) cell).getOwner().getName().equals(Game.getPlayers().get(0).getName()) ?'0' : '1'));
                    }

                    // write the information about animal on the cell
                    writer.newLine();
                    Animal animal = cell.getAnimal();
                    if(animal != null){
                        writer.write(animal.getType() + " " + animal.getName() + " " + animal.getRank() + " "+ (cell.getAnimal().getOwner().getName().equals(Game.getPlayers().get(0).getName()) ?0 : 1));
                    }
                    writer.newLine();
                }
            }

            writer.close();
        }
        catch (Exception e){
            System.out.println(e.fillInStackTrace());
            return false;
        }
        return true;
    }

    boolean load(String loadFile) {
        // return false when fails
        try {
            BufferedReader br = new BufferedReader(new FileReader(loadFile));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            int lineNo = 0;

            // get the curentPlayerIndex
            int currentPlayer = Integer.parseInt(line);

            //get the information of player0
            line = br.readLine();
            String player0Name = line.split(" ")[0];
            int play0NumAnimal = Integer.parseInt(line.split(" ")[1]);
            ArrayList<Player> playerArrayList = new ArrayList<Player>();
            playerArrayList.add(new Player(player0Name,play0NumAnimal));

            //get the information of player1
            line = br.readLine();
            String player1Name = line.split(" ")[0];
            int play1NumAnimal = Integer.parseInt(line.split(" ")[1]);
            playerArrayList.add(new Player(player1Name,play1NumAnimal));

            line = br.readLine();
            int row = Integer.parseInt(line.split(" ")[0]);
            int column = Integer.parseInt(line.split(" ")[1]);
            ArrayList<ArrayList<Cell>> cells = new ArrayList<ArrayList<Cell>>();
            for(int i = 0; i < row; i++){
                //System.out.println("Cell: " + line);
                cells.add(new ArrayList<Cell>());
                for(int j = 0; j < column; j++){
                    line = br.readLine();
                    Cell cell = null;
                    switch(line.split(" ")[0]){
                        case("LAND"):
                            cell = new LandCell(null);
                            break;
                        case("TRAP"):
                            cell = new TrapCell(null);
                            break;
                        case("DEN"):
                            cell = new DenCell(null,playerArrayList.get(Integer.parseInt(line.split(" ")[1])));
                            break;
                        case("RIVER"):
                            cell = new RiverCell(null);
                            break;
                    }
                    // read animal
                    line = br.readLine();
                    if(line.split(" ").length == 4){
                        //System.out.println("Animal: " + line);
                        Animal animal= null;
                        switch (line.split(" ")[1]){
                            case("ELEPHANT"):
                                animal = new Elephant(line.split(" ")[1], Integer.parseInt(line.split(" ")[2]), playerArrayList.get(Integer.parseInt(line.split(" ")[3])));
                                break;
                            case("WOLF"):
                                animal = new Wolf(line.split(" ")[1], Integer.parseInt(line.split(" ")[2]), playerArrayList.get(Integer.parseInt(line.split(" ")[3])));
                                break;
                            case("LEOPARD"):
                                animal = new Leopard(line.split(" ")[1], Integer.parseInt(line.split(" ")[2]), playerArrayList.get(Integer.parseInt(line.split(" ")[3])));
                                break;
                            case("RAT"):
                                animal = new Rat(line.split(" ")[1], Integer.parseInt(line.split(" ")[2]), playerArrayList.get(Integer.parseInt(line.split(" ")[3])));
                                break;
                            case("CAT"):
                                animal = new Cat(line.split(" ")[1], Integer.parseInt(line.split(" ")[2]), playerArrayList.get(Integer.parseInt(line.split(" ")[3])));
                                break;
                            case("DOG"):
                                animal = new Dog(line.split(" ")[1], Integer.parseInt(line.split(" ")[2]), playerArrayList.get(Integer.parseInt(line.split(" ")[3])));
                                break;
                            case("TIGER"):
                                animal = new Tiger(line.split(" ")[1], Integer.parseInt(line.split(" ")[2]), playerArrayList.get(Integer.parseInt(line.split(" ")[3])));
                                break;
                            case("LION"):
                                animal = new Lion(line.split(" ")[1], Integer.parseInt(line.split(" ")[2]), playerArrayList.get(Integer.parseInt(line.split(" ")[3])));
                                break;

                        }
                        cell.setAnimal(animal);
                    }
                    else if(line.equals("")){
                        //System.out.println("no animal");
                        cell.setAnimal(null);
                    }
                    else{
                        //System.out.println("Invalid line: " + line);
                        return false;
                    }
                    cells.get(i).add(cell);
                }

            }

            for(ArrayList<Cell> rowcell : cells){
                //System.out.println("Row size: " + rowcell.size());
                for(Cell cell : rowcell){
                    if(cell.getAnimal() != null){
                        //System.out.println(cell.getAnimal());
                    }
                    else{
                        //System.out.println("No animal");
                    }
                }
            }

            Game.setCurPlayerIndex(currentPlayer);
            Game.setPlayers(playerArrayList);
            this.cells = cells;
            return true;
        }
        catch (IOException e){
            System.out.println(e.fillInStackTrace());
            return false;
        }

    }

    // below methods are only called by Command and Board itself
    void putAnimal(int row, int column, Animal animal) {
        Cell cell = getCell(row, column);
        if (cell.getAnimal() != null) {
            cell.getAnimal().getOwner().decreaseAnimalNum();
        }
        cell.setAnimal(animal);
    }

    void removeAnimal(int row, int column) {
        Cell cell = getCell(row, column);
        cell.setAnimal(null);
    }

    void moveAnimal(int fromX, int fromY, int toX, int toY) {
        Animal animal = getCell(fromX, fromY).getAnimal();
        removeAnimal(fromX, fromY);
        putAnimal(toX, toY, animal);
    }

    private ArrayList<ArrayList<Cell>> cells;
}
