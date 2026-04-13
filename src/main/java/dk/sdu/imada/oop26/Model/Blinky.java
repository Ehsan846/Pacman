package dk.sdu.imada.oop26.Model;
import java.util.ArrayList;
import java.util.List;

public class Blinky extends Ghost {
    public Blinky() {
        type = GhostType.BLINKY;
        x = 13.5;
        y = 11;
    }
 

    

    public void update_direction(Pacman pacman, Maze maze, double dt) {

        double minDist = Double.POSITIVE_INFINITY; 

        for(Direction d :  Direction.values()){
            if ( d == Direction.NONE || d == Direction.oppositeDirection(getDirection())) {
                continue;
            }
            double Newx = x + d.dx; 
            double NewY = y + d.dy; 


            if(maze.getTile(Newx, NewY) != Tile.Wall){

                double xdiff = Newx - pacman.x; 
                double ydiff = NewY - pacman.y; 
                double dist = Math.sqrt(Math.pow(xdiff, 2) + Math.pow(ydiff, 2));
            
                if (minDist > dist){
                    System.out.println("Data: " +  Newx +" " + NewY + " "+ d + " " + minDist + " " + dist);
                    minDist = dist; 
                    setWantedDirection(d);
                }
            }
            
        }
        System.out.println(" ");
    }

}