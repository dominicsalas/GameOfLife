# GameOfLife

CS 351 Lab 3 Game Of Life Project.

This is the game of life in 3d. The program will start off in settings page. If you press
Start the Game, it will use default settings of 5,5,6,3. If you fill in the fields, then 
those settings will be used for the rest of the game. 

There is a button in the upper left hand corner during game play that will take you back to the 
Settings page.

Cube: This is the Box/Cell/Cube objects. They store all the information for each cube within the
overall grid.

Grid: This is the Grid class that builds the grid and maintains info about about the grid.

GUI: Handles the GUI of the game along with tracking input. I couldn't figure out an easy way 
to integrate validating input.

Loop: This is the Loop class and it's responsible for looping the game and updating each state of the game.

gui.fxml: This is the First screen.
