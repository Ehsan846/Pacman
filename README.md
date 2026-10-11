# Pacman Game
This is a collaborative project i did together with friends for a course.
The main focus of this project has been to put our knowledge of object-oriented programming into play. 

# STATES
The game has four states: Normal, Immune, Power, and Finished.
The game begins in the state Normal with a life counter of two.
– In the state Normal, the ghosts move such as to pursue Pac-Man.
– In the state Power, the ghosts move such as to avoid Pac-Man.

# POWER STATE
When Pac-Man collides with a power pellet, the pellet disappears, and the game state changes to the state Power for a number of seconds (e.g., 10-20 seconds) before reverting back to the state Normal.

# STATES DURING GHOST COLLISSION
When Pac-Man collides with a ghost, what happens depends on the state of the game.
– If the game is in the state Normal, the life counter decreases by one.
∗ If at this point the life counter is at 0, the game ends and changes to the state Finished.
∗ If at this point the life counter is larger than 0, the game changes to the state Immune for
a short period of time (e.g., 1-2 seconds) before reverting back to the state Normal.
– If the game is in the state Immune, nothing happens.
– If the game is in the state Power, the ghost disappears.

# FINISHED STATE
In the state Finished, the game displays the latest score, and offers the option to start a new game
(which would reset the score).

[ ] Newlevel når alle pellets er spist
[x] Portal (Ghosts er langsommere i portal området)
[x] Block pacman fra ghost room
