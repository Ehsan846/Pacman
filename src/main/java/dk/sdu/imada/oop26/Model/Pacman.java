package dk.sdu.imada.oop26.Model;


public class Pacman extends MovingEntities {
    public Pacman() {
        this.x = 100;
        this.y = 100;
        this.speed = 125;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        }


    @Override
    public PacmanEvent update(double dt, Maze maze) {
        super.update(dt, maze); // bruger parent methode til at rykke pacman;

        if(maze.getTile(x, y) == Tile.Pellet){
            maze.emptyTile(x, y);
            return PacmanEvent.pacPellet;   
        }

        else if (maze.getTile(x, y) == Tile.PowerPellet){ 
            maze.emptyTile(x,y);
            return PacmanEvent.pacPower; 
    
        }

        return PacmanEvent.pacNone; 

        // add for ghost


        // add for win (pacman ate all pellets)
    }
    
}