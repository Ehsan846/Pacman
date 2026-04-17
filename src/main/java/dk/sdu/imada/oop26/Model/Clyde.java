package dk.sdu.imada.oop26.Model;

public class Clyde extends Ghost {

    private int last_x;
    private int last_y;
    private int last_px;
    private int last_py;

    public Clyde() {
        type = GhostType.CLYDE;
        x = 15.5;
        y = 14;
    }
        @Override
        public void update_direction(Pacman pacman, Maze maze) {
            if ((int)x == last_x && (int)y == last_y && (int)pacman.x == last_px && (int)pacman.y == last_py) {
                return;
        }

        
        int dist = (int)Math.sqrt(Math.pow(pacman.getx() - x, 2) + Math.pow(pacman.gety() - y, 2));

   
        if(dist <= 8){
            setWantedDirection(AStar.astar(maze, (int)x, (int)y, 1, 1));
            System.out.println(dist);
        }

        else  {
            setWantedDirection(AStar.astar(maze, (int)x, (int)y, (int)pacman.x, (int)pacman.y));    
        }
        

        last_x = (int)x;
        last_y = (int)y;
        last_px = (int)pacman.x;
        last_py = (int)pacman.y;
    }
}
