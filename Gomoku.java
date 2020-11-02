import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Background;
import java.util.*;

/**
 * Nathan Nguyen - A class that makes a game of Gomoku.
 * @author <em>Nathan Nguyen</em>
 */

public class Gomoku extends Application
{
  /*stores the primary stage of the JavaFX application*/
  private Stage primaryStage;
  /*stores the length of the board*/
  private int rowLength;
  /*stores the width of the board*/
  private int colLength;
  /*stores the number of pieces needed in a row to win*/
  private int numForWin = 5;
  /*stores the length and width of a space on the board*/
  private int spaceSize = 40;
  /*stores the inner array for processing of the game*/
  private int[][] innerArray;
  /*stores the outer array interface of the game*/
  private Button[][] buttonArray;
  /*stores the background fill of an empty space*/
  private BackgroundFill emptySpace = new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, new Insets(1));
  /*stores the background fill of a white space*/
  private BackgroundFill whiteSpace = new BackgroundFill(Color.WHITE, new CornerRadii(60), new Insets(6,6,6,6));
  /*stores the background fill of a black space*/
  private BackgroundFill blackSpace = new BackgroundFill(Color.BLACK, new CornerRadii(60), new Insets(6,6,6,6));
  /*stores boolean indicating the correct player turn*/
  private Boolean whiteTurn = true;
  /*stores boolean indicating if a game is in play or not*/
  private Boolean gameInPlay = true;
  /*stores value of number of groups of three white player has*/
  private int whiteThreeCounter = 0;
  /*stores value of number of groups of three black player has*/
  private int blackThreeCounter = 0;
  /*stores value of number of groups of four white player has*/
  private int whiteFourCounter = 0;
  /*stores value of number of groups of four black player has*/
  private int blackFourCounter = 0;
  
 /** 
  * Overrides the start method of Application to create the GUI with a grid of buttons for the starting game board.
  * @param primaryStage the JavaFX main window
  * @exception NumberFormatException exception if the user enters strings
  * @exception ArrayIndexOutOfBoundsException exception if the user enters unrealistic dimensions
  */
 public void start(Stage primaryStage)
 {
   try
   {
     /*stores a list of parameters inputted by user*/
     List<String> parameters = getParameters().getRaw();
     /*stores iterator of the lsit of paramters*/
     ListIterator<String> parameterIterator = parameters.listIterator();
     
     if (parameterIterator.hasNext())
     {
       numForWin = Integer.parseInt(parameterIterator.next());
       if (parameterIterator.hasNext())
       {
         rowLength = Integer.parseInt(parameterIterator.next());
         if (parameterIterator.hasNext())
         {
           colLength = Integer.parseInt(parameterIterator.next());
           System.out.println("Launching game with dimensions of " + rowLength + "x" + colLength + " and win condition of " + numForWin);
         }
         else                       /*if the user only inputs two numbers*/
         {
           colLength = rowLength;
           rowLength = numForWin;
           numForWin = 5;
           System.out.println("No win condition inputted, launching game with dimensions of " + rowLength + "x" + colLength + " and win condition of 5");
         }
       }
       else                        /*if the user only inputs one number*/
       {
         rowLength = 19;
         colLength = 19;
         System.out.println("No dimensions inputted, launching game with default board dimensions of 19x19 and win condition of " + numForWin);
       }
     }
     else                          /*if the user does not input any numbers*/
     { 
       rowLength = 19;
       colLength = 19;
       numForWin = 5;
       System.out.println("No dimensions or win condition inputted, launching game with default board dimensions of 19x19 and win condition of 5");
     }
   }
   
   
   
   catch (NumberFormatException e) /*if the user enters strings for dimensions*/
   {
     System.out.println("Invalid dimensions inputted, launching game with default board dimensions of 19x19 and win condition of 5.");
   }
     
   catch (ArrayIndexOutOfBoundsException e) /*if the user enters unrealistic numbers for dimensions*/
   {
     System.out.println("Invalid dimensions inputted, launching game with default board dimensions of 19x19 and win condition of 5.");
   }
      
   if (numForWin <= 0 || (numForWin > rowLength && numForWin > colLength))  /*if there is no win condition*/
   {
     numForWin = 5;
     System.out.println("Invalid win condition inputted, launching game with default win condition of 5");
   }
     
   if (rowLength <= 0 || colLength <= 0) /*if there is no width or height of board*/
   {
     rowLength = 19;
     colLength = 19;
     System.out.println("Invalid dimensions inputted, launching game with default board dimensions of 19x19.");
   }
   
   buttonArray = new Button[rowLength][colLength];
   innerArray = new int[rowLength][colLength];
     
   /*stores the interface that will hold the button*/
   GridPane gridPane = new GridPane();
     
   /*The purpose of this loop is to fill all spaces of the board with empty space*/
   /*Iteration subgoal: Fill in all spaces of a specific row*/
   /*Preconditions: None*/
   for (int i = 0; i < buttonArray.length; i++)
   {
     /*The purpose of this loop is to fill all spaces of a row with an empty space*/
     /*Interation subgoal: Fill in a space in a row*/
     /*Preconditions: None*/
     for (int j = 0; j < buttonArray[i].length; j++)
     {
       buttonArray[i][j] = new Button();
       buttonArray[i][j].setOnAction(new ButtonAction());
       buttonArray[i][j].setMinWidth(spaceSize);
       buttonArray[i][j].setMinHeight(spaceSize);
       buttonArray[i][j].setBackground(new Background(emptySpace));
       gridPane.add(buttonArray[i][j], i, j);
     }
   }
     
   /*Create a "scene" that contains this border area*/
   Scene scene = new Scene(gridPane);       
     
   primaryStage.setTitle("Gomoku");
   primaryStage.setScene(scene);            /*Add the "scene" to the main window*/
   primaryStage.show();                     /*Display the window*/
   this.primaryStage = primaryStage;
 }
  
  
 public class ButtonAction implements EventHandler<ActionEvent>
 {
   /** 
    *React to a button click
    *@param e information about the button click event that occurred
    */
   
   public void handle (ActionEvent e)
   {
     Button b = (Button) e.getSource(); // this points to what b1 points to!
     
     /*The purpose of this loop is to search through all of the rows for a button click*/
     /*Iteration subgoal: Search through a specific row for a button click*/
     /*Preconditions: None*/
     for (int i = 0; i < buttonArray.length; i++)
     {
       /*The purpose of this loop is to search through all of the elements in a row for a button click*/
       /*Iteration subgoal: Check if a specific element in a row stores the position of the button click*/
       /*Preconditions: None*/
       for (int j = 0; j < buttonArray[i].length; j++)
       {
         if (b == buttonArray[i][j] && b.getBackground().equals(new Background(emptySpace)))                            /*if the button position is found, and it is empty*/
         {
          
           
           if (whiteTurn == true)                                                                                       /*if it is the white player's turn*/
           {
             innerArray[i][j] = 1;
             printArray();
           }
           
           else
           {
             innerArray[i][j] = 2;
           }
           
           if (threeInARow(innerArray, i, j) == true || fourInARow(innerArray, i, j) == true || gameInPlay == false)    /*if the game is over, a three-three, or a four-four condition has been found*/ 
           {
             innerArray[i][j] = 0;
           }
           
           else                                                                                                         /*if the move is valid*/
           {
             if (whiteTurn == true)
             {
               innerArray[i][j] = 1;
               buttonArray[i][j].setBackground(new Background (emptySpace, whiteSpace));
               whiteTurn = false;
               gameInPlay = checkGameInPlay(innerArray, i, j);
             }
             else
             {
               innerArray[i][j] = 2;
               buttonArray[i][j].setBackground(new Background (emptySpace, blackSpace));
               whiteTurn = true;
               gameInPlay = checkGameInPlay(innerArray, i, j);
             }
           }
         }
       }
     }
   }
 }
 
 /**
  * Checks to see how many of the same element are in a straight line
  * @param board the board that is being checked for a straight line
  * @param row the row index of the element that is being checked
  * @param col the column index of the element that is being checked
  * @param direction the direction in which to check for a straight line
  * @return the number of pieces in a straight line at a specific position in a specific direction
  */
 public int numberInLine (int board[][], int row, int col, String direction)
 {
   /*flag that stores if a straight line still exists*/
   Boolean stillInLine = true;
   /*stores how many pieces are in a straight line*/
   int counter = 0;
   /*stores the row index for use in the method*/
   int useRow = row;
   /*stores the column index for use in the method*/
   int useCol = col;
   
   if (direction.equals("up"))                                  /*if direction is up*/
   {
     /*The purpose of this loop is to execute the if conditions if there is still a straight line and there are more pieces to check in the board in the up direction*/
     /*Iteration subgoal: Check if there is still a straight line and more pieces to place*/
     /*Preconditions: None*/
     while (stillInLine == true && useRow >= 0)
     {
       if (board[useRow][useCol] == (board[row][col]))          /*if the piece following the previous piece is the same color*/
       {
         counter += 1;
         useRow -= 1;
       }
       else
       {
         stillInLine = false;
       }
     }
   }
   
   if (direction.equals("down"))                                /*if direction is down*/
   {
     /*The purpose of this loop is to execute the if conditions if there is still a straight line and there are more pieces to check in the board in the down direction*/
     /*Iteration subgoal: Check if there is still a straight line and more pieces to place*/
     /*Preconditions: None*/
     while (stillInLine == true && useRow < board.length)
     {
       if (board[useRow][useCol] == (board[row][col]))          /*if the piece following the previous piece is the same color*/
       {
         counter += 1;
         useRow += 1;
       }
       else
       {
         stillInLine = false;
       }
     }
   }
   
   if (direction.equals("left")) //if direction is left
   {
     /*The purpose of this loop is to execute the if conditions if there is still a straight line and there are more pieces to check in the board in the left direction*/
     /*Iteration subgoal: Check if there is still a straight line and more pieces to place*/
     /*Preconditions: None*/
     while (stillInLine == true && useCol >= 0)
     {
       if (board[useRow][useCol] == (board[row][col]))       /*if the piece following the previous piece is the same color*/
       {
         counter += 1;
         useCol -= 1;
       }
       else
       {
         stillInLine = false;
       }
     }
   }
   
   if (direction.equals("right")) //if direction is right
   {
     /*The purpose of this loop is to execute the if conditions if there is still a straight line and there are more pieces to check in the board in the right direction*/
     /*Iteration subgoal: Check if there is still a straight line and more pieces to place*/
     /*Preconditions: None*/
     while (stillInLine == true && useCol < board[useRow].length)
     {
       if (board[useRow][useCol] == (board[row][col]))     /*if the piece following the previous piece is the same color*/
       {
         counter += 1;
         useCol += 1;
       }
       else
       {
         stillInLine = false;
       }
     }
   }
   
   if (direction.equals("left up diagonal"))
   {
     /*The purpose of this loop is to execute the if conditions if there is still a straight line and there are more pieces to check in the board in the left up diagonal direction*/
     /*Iteration subgoal: Check if there is still a straight line and more pieces to place*/
     /*Preconditions: None*/
     while (stillInLine == true && useCol >= 0 && useRow >= 0)
     {
       if (board[useRow][useCol] == (board[row][col]))
       {
         counter += 1;
         useRow -= 1;
         useCol -= 1;
       }
       else
       {
         stillInLine = false;
       }
     }
   }
   
   if (direction.equals("right up diagonal"))
   {
     /*The purpose of this loop is to execute the if conditions if there is still a straight line and there are more pieces to check in the board in the right up diagonal direction*/
     /*Iteration subgoal: Check if there is still a straight line and more pieces to place*/
     /*Preconditions: None*/
     while (stillInLine == true && useRow >= 0 && useCol < board[useRow].length)
     {
       if (board[useRow][useCol] == (board[row][col]))
       {
         counter += 1;
         useRow -= 1;
         useCol += 1;
       }
       else
       {
         stillInLine = false;
       }
     }
   }
   
   if (direction.equals("left down diagonal"))
   {
     /*The purpose of this loop is to execute the if conditions if there is still a straight line and there are more pieces to check in the board in the left down direction*/
     /*Iteration subgoal: Check if there is still a straight line and more pieces to place*/
     /*Preconditions: None*/
     while (stillInLine == true && useRow < board.length && useCol >= 0)
     {
       if (board[useRow][useCol] == (board[row][col]))
       {
         counter += 1;
         useRow += 1;
         useCol -= 1;
       }
       else
       {
         stillInLine = false;
       }
     }
   }
   
   if (direction.equals("right down diagonal"))
   {
     /*The purpose of this loop is to execute the if conditions if there is still a straight line and there are more pieces to check in the board in the right down diagonal direction*/
     /*Iteration subgoal: Check if there is still a straight line and more pieces to place*/
     /*Preconditions: None*/
     while (stillInLine == true && useRow < board.length && useCol < board[useRow].length)
     {
       if (board[useRow][useCol] == (board[row][col]))
       {
         counter += 1;
         useRow += 1;
         useCol += 1;
       }
       else
       {
         stillInLine = false;
       }
     }
   }
   return counter;
 }
 
 /**
  * Checks if there is an open space following a straight line starting from a specific element in a specific direction
  * @param board board the board that is being checked for an empty space following a straight line
  * @param row the row index of the element that is being checked
  * @param col the column index of the element that is being checked
  * @param direction the direction in which to check for an empty space following a straight line
  * @return boolean that indicates whether or not an empty space follows a straight line given the position of a specific element and a specific direction to check for
  */
 public Boolean isOpen(int board[][], int row, int col, String direction)
 {
   /*stores the row index for use in the method*/
   int useRow = row;
   /*stores the column index for use in the method*/
   int useCol = col;
   
   if (direction.equals("up"))                         /*if direction is up*/
   {
     /*The purpose of this loop is to execute if conditions while there are still pieces on the board to check in the up direction*/
     /*Iteration subgoal: Check to see if there are still pieces to be checked on the board*/
     /*Preconditions: None*/
     while (useRow >= 0)
     {
       if (board[useRow][useCol] == (board[row][col])) /*if the piece following the previous piece is the same color*/
       {
         useRow -= 1;
       }
       else if (board[useRow][useCol] == 0)           /*if there is an empty space on the board*/
       {
         return true;
       }
       else
       {
         return false;                               /*return false for any other scenario*/
       }
     }
   }
   
   if (direction.equals("down"))                        /*if direction is down*/
   {
     /*The purpose of this loop is to execute if conditions while there are still pieces on the board to check in the down direction*/
     /*Iteration subgoal: Check to see if there are still pieces to be checked on the board*/
     /*Preconditions: None*/
     while (useRow < board.length)
     {
       if (board[useRow][useCol] == (board[row][col]))  /*if the piece following the previous piece is the same color*/
       {
         useRow += 1;
       }
       else if (board[useRow][useCol] == 0)
       {
         return true;
       }
       else
       {
         return false;
       }
     }
   }
   
   if (direction.equals("left")) //if direction is left
   {
     /*The purpose of this loop is to execute if conditions while there are still pieces on the board to check in the left direction*/
     /*Iteration subgoal: Check to see if there are still pieces to be checked on the board*/
     /*Preconditions: None*/
     while (useCol >= 0)
     {
       if (board[useRow][useCol] == (board[row][col])) /*if the piece following the previous piece is the same color*/
       {
         useCol -= 1;
       }
       else if (board[useRow][useCol] == 0)
       {
         return true;
       }
       else
       {
         return false;
       }
     }
   }
   
   if (direction.equals("right")) //if direction is right
   {
     /*The purpose of this loop is to execute if conditions while there are still pieces on the board to check in the right direction*/
     /*Iteration subgoal: Check to see if there are still pieces to be checked on the board*/
     /*Preconditions: None*/
     while (useCol < board[useRow].length)
     {
       if (board[useRow][useCol] == (board[row][col])) /*if the piece following the previous piece is the same color*/
       {
         useCol += 1;
       }
       else if (board[useRow][useCol] == 0)
       {
         return true;
       }
       else
       {
         return false;
       }
     }
   }
   
   if (direction.equals("left up diagonal"))
   {
     /*The purpose of this loop is to execute if conditions while there are still pieces on the board to check in the left up diagonal direction*/
     /*Iteration subgoal: Check to see if there are still pieces to be checked on the board*/
     /*Preconditions: None*/
     while (useCol >= 0 && useRow >= 0)
     {
       if (board[useRow][useCol] == (board[row][col]))
       {
         useRow -= 1;
         useCol -= 1;
       }
       else if (board[useRow][useCol] == 0)
       {
         return true;
       }
       else
       {
         return false;
       }
     }
   }
   
   if (direction.equals("right up diagonal"))
   {
     /*The purpose of this loop is to execute if conditions while there are still pieces on the board to check in the right up digonal direction*/
     /*Iteration subgoal: Check to see if there are still pieces to be checked on the board*/
     /*Preconditions: None*/
     while (useRow >= 0 && useCol < board[useRow].length)
     {
       if (board[useRow][useCol] == (board[row][col]))
       {
         useRow -= 1;
         useCol += 1;
       }
       else if (board[useRow][useCol] == 0)
       {
         return true;
       }
       else
       {
         return false;
       }
     }
   }
   
   if (direction.equals("left down diagonal"))
   {
     /*The purpose of this loop is to execute if conditions while there are still pieces on the board to check in the left down diagonal direction*/
     /*Iteration subgoal: Check to see if there are still pieces to be checked on the board*/
     /*Preconditions: None*/
     while (useRow < board.length && useCol >= 0)
     {
       if (board[useRow][useCol] == (board[row][col]))
       {
         useRow += 1;
         useCol -= 1;
       }
       else if (board[useRow][useCol] == 0)
       {
         return true;
       }
       else
       {
         return false;
       }
     }
   }
   
   if (direction.equals("right down diagonal"))
   {
     /*The purpose of this loop is to execute if conditions while there are still pieces on the board to check in the right down diagonal direction*/
     /*Iteration subgoal: Check to see if there are still pieces to be checked on the board*/
     /*Preconditions: None*/
     while (useRow < board.length && useCol < board[useRow].length)
     {
       if (board[useRow][useCol] == (board[row][col]))
       {
         useRow += 1;
         useCol += 1;
       }
       else if (board[useRow][useCol] == 0)
       {
         return true;
       }
       else
       {
         return false;
       }
     }
   }
   return false;
 }
 
 /**
  * Check to see if an element being placed will create two or more groups of three of the same elements in a row with the ends of the groups of three having empty spaces
  * @param board the board checked for a three-three condition
  * @param row the row index of the element being checked
  * @param col the column index of the element being checked
  * @return the boolean value indicating whether or not the three-three condition occurs
  */
 public Boolean threeInARow(int[][] board, int row, int col)
 {
   /*stores the amount of times an element makes a group of three pieces in a row with an empty space at both ends of the line*/
   int counter = 0;
   
   if ((numberInLine(board, row, col, "up") + numberInLine(board, row, col, "down") == numForWin - 1) && (isOpen(board, row, col, "up") == true) && (isOpen(board, row, col, "down") == true))      /*if there are three integers in a row that are equal in the vertical direction, and there is an open space at both ends of the line*/
   {
     counter += 1;
   }
   
   if ((numberInLine(board, row, col, "left") + numberInLine(board, row, col, "right") == numForWin - 1) && (isOpen(board, row, col, "left") == true) && (isOpen(board, row, col, "right") == true))    /*if there are three integers in a row that are equal in the horizontal direction, and there is an open space at both ends of the line*/
   {
     counter += 1;
   }
   
   if ((numberInLine(board, row, col, "left up diagonal") + numberInLine(board, row, col, "right down diagonal") == numForWin - 1 ) && (isOpen(board, row, col, "left up diagonal") == true) && (isOpen(board, row, col, "right down diagonal") == true))  /*if there are three integers in a row that are equal in the left up diagonal/right down direction, and there is an open space at both ends of the line*/
   {
     counter += 1;
   }
   
   if ((numberInLine(board, row, col, "left down diagonal") + numberInLine(board, row, col, "right up diagonal") == numForWin - 1) && (isOpen(board, row, col, "left down diagonal") == true) && (isOpen(board, row, col, "right up diagonal") == true)) /*if there are three integers in a row that are equal in the left down diagonal/right up diagonal direction, and there is an open space at both ends of the line*/
   {
     counter += 1;
   }
   
   
   if (counter >= 2)                  /*if there are 2 or more groups of three integers in a row with empty spaces at both ends of the groups*/
   {
     System.out.println("Three-three scenario.  Please choose a different space");
     return true;
   }
   
   return false;
 }
 
 /**
  * Check to see if an element being placed will create two or more groups of four of the same elements in a straight line
  * @param board the board being checked for the four-four condition
  * @param row the row index of the element being checked
  * @param col the column index of the element being checked
  * @return the boolean value indicating whether or not the four-four condition occurs
  */
 public Boolean fourInARow(int[][] board, int row, int col)
 {
   /*stores the amount of times an element makes a group of four pieces in a row*/
   int counter = 0;
   
   if (numberInLine(board, row, col, "up") + numberInLine(board, row, col, "down") == numForWin)                               /*if there are four integers in a row that are equal in vertical direction*/
   {
     counter += 1;
   }
   
   if (numberInLine(board, row, col, "left") + numberInLine(board, row, col, "right") == numForWin)                           /*if there are four integers in a row that are equal in horizontal direction*/
   {
     counter += 1;
   }
   
   if (numberInLine(board, row, col, "left up diagonal") + numberInLine(board, row, col, "right down diagonal") == numForWin) /*if there are four integers in a row that are equal in left up diagonal/right down diagonal direction*/
   {
     counter += 1;
   }
   
   if (numberInLine(board, row, col, "left down diagonal") + numberInLine(board, row, col, "right up diagonal") == numForWin) /*if there are four integers in a row that are equal in left down diagonal/right up diagonal direction*/
   {
     counter += 1;
   }
   
   if (counter >= 2)                                 /*if there are 2 or more groups of four integers in a row that are equal*/
   {
     System.out.println("Four-four scenario.  Please choose a different space ");
     return true;
   }
   
   return false;
 }
 
 /**
  * Checks for a victory whenever there are five equal integers in a straight line in a specific direction
  * @param board the board to check for the five-five condition
  * @param row the row index of the element being checked
  * @param col the column index of the elemnt being checked
  * @return boolean value of whether or not the game is still in play
  */
 public Boolean checkGameInPlay(int[][] board, int row, int col)
 {  
   if ((numberInLine(board, row, col, "up") + numberInLine(board, row, col, "down") == numForWin + 1) || (numberInLine(board, row, col, "left") + numberInLine(board, row, col, "right") == numForWin + 1) || (numberInLine(board, row, col, "left up diagonal") + numberInLine(board, row, col, "right down diagonal") == numForWin + 1) || (numberInLine(board, row, col, "left down diagonal") + numberInLine(board, row, col, "right up diagonal") == numForWin + 1))
   {
     if (board[row][col] == 1)
     {
       System.out.println("Player White Wins!");
     }
     else
     {
       System.out.println("Player Black Wins!");
     }
     return false;
   }
   
   return true;
 }
 
 public void printArray()
 {
   for(int i = 0; i < innerArray.length; i++)
   {
     for(int j = 0; j < innerArray[0].length; j++)
     {
       System.out.print(innerArray[i][j] + " ");
     }
     System.out.println();
   }
 }
 
 /**
  * The method to launch the program.
  * @param args The command line arguments.  The arguments are passed on to the JavaFX application.
  */
 public static void main(String[] args) 
 {
   Application.launch(args);
 }
 
}
       
       
       
   