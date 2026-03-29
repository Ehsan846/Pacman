package main.java.dk.sdu.imada.oop26.Model;
//for reading the maze
import java.io.BufferedReader;
import java.io.FileReader;

    public class Maze {
        void readFile() throws IOException {  // add this
            BufferedReader reader = new BufferedReader(new FileReader("map.txt"));
            //Read file line by line
            String line = reader.readLine();
            while(line != null){
                //System.out.println(line);
                line = reader.readLine();                }

            reader.close();
        }
    }

            public enum Tiles {
            Empty,
            Wall,
            Pellet,
            PowerPellet
        }



            /*
            Maze.txt
                # = wall
                . = pellet
                o = power pellet
                - = 
                            
        }*/

