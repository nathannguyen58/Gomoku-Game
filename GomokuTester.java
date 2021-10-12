import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class GomokuTester
{
  @Test
  public void testNumberInLine()
  {
    /*Testing general integers in line for all directions*/
    Gomoku test = new Gomoku();
    
    int[][] board = {{0, 2, 4, 4, 4}, {1, 2, 4, 5, 9}, {3, 2, 4, 9, 7}, {2, 2, 2, 2, 2}, {5, 2, 4, 3, 2}};
    
    assertEquals("Method is testing checking numbers in line in the up direction", 5, test.numberInLine(board, 4, 1, "up"));
    assertEquals("Method is testing checking numbers in line in the down direction", 3, test.numberInLine(board, 0, 2, "down"));
    assertEquals("Method is testing checking numbers in line in the left direction", 3, test.numberInLine(board, 0, 4, "left"));
    assertEquals("Method is testing checking numbers in line in the right direction", 5, test.numberInLine(board, 3, 0, "right"));
    
    int[][] newBoard = {{3, 4, 6, 8}, {2, 3, 8, 9}, {1, 8, 3, 2}};
    
    assertEquals("Method is testing checking numbers in line in the left up diagonal direction", 3, test.numberInLine(newBoard, 2, 2, "left up diagonal"));
    assertEquals("Method is testing checking numbers in line in the right up diagonal direction", 3, test.numberInLine(newBoard, 2, 1, "right up diagonal"));
    assertEquals("Method is testing checking numbers in line in the left down diagonal direction", 3, test.numberInLine(newBoard, 0, 3, "left down diagonal"));
    assertEquals("Method is testing checking numbers in line in the right down diagonal direction", 3, test.numberInLine(newBoard, 0, 0, "right down diagonal"));
    
    
    /*Testing 0, 1, and many equal integers following the specified element in a particular direction*/
    
    /*Testing 0 integers following the specified element in a line*/
    int[][] newerBoard = {{1, 6, 3, 9}, {4, 5, 2, 8}, {7, 4, 10, 11}, {12, 3, 1, 2}};
    assertEquals("Method is testing checking 0 integers following the specified element in a particular direction", 1, test.numberInLine(newerBoard, 3, 1, "up"));
    
    /*Testing 1 integer following the specified element in a line*/
    int[][] aBoard = {{1, 6, 3, 9}, {4, 5, 2, 8}, {7, 3, 10, 11}, {12, 3, 1, 2}};
    assertEquals("Method is testing checking 1 integer following the specified element in a particular direction", 2, test.numberInLine(aBoard, 3, 1, "up"));
    
    /*Testing more than 1 integer following the specified element in a line*/
    int[][] bBoard = {{1, 3, 3, 9}, {4, 3, 2, 8}, {7, 3, 10, 11}, {12, 3, 1, 2}};
    assertEquals("Method is testing checking more than 1 integer following the specified element in a particular direction", 4, test.numberInLine(bBoard, 3, 1, "up"));
    
    
    /*Testing first, middle, and last integers being the integer that breaks the line*/
    
    /*Testing first integer being the integer that breaks the line*/
    int[][] cBoard = {{1, 4, 3, 9}, {4, 4, 2, 8}, {7, 4, 10, 11}, {12, 3, 1, 2}};
    assertEquals("Method is testing checking the first integer being the integer that breaks the line", 1, test.numberInLine(cBoard, 3, 1, "up"));
    
    /*Testing middle integer being the integer that breaks the line*/
    int[][] dBoard = {{1, 4, 3, 9}, {4, 0, 2, 8}, {7, 4, 10, 11}, {12, 4, 1, 2}};
    assertEquals("Method is testing checking the middle integer being the integer that breaks the line", 2, test.numberInLine(dBoard, 3, 1, "up"));
    
    /*Testing last integer being the integer that breaks the line*/
    int[][] eBoard = {{1, 0, 3, 9}, {4, 4, 2, 8}, {7, 4, 10, 11}, {12, 4, 1, 2}};
    assertEquals("Method is testing checking the last integer being the integer that breaks the line", 3, test.numberInLine(eBoard, 3, 1, "up"));
    
  }
  
  @Test
  public void testIsOpen()
  {
    /*Testing general is open for all directions*/
    Gomoku test = new Gomoku();
    
    int[][] board = {{10, 10, 10, 10}, {10, 10, 10, 10}};
    assertFalse("Method is testing when the board does not find an empty space in the up direction", test.isOpen(board, 0, 3, "up"));
    
    int[][] aBoard = {{10, 10, 10, 0}, {10, 10, 10, 10}};
    assertTrue("Method is testing finding an empty space in the up direction", test.isOpen(aBoard, 1, 3, "up"));
    
    int[][] bBoard = {{4, 3, 2}, {1, 5, 2}, {2, 6, 1}};
    assertFalse("Method is testing when the board does not find an empty space in the down direction", test.isOpen(bBoard, 0, 2, "down"));
    
    int[][] cBoard = {{4, 3, 2}, {1, 5, 2}, {2, 6, 0}};
    assertTrue("Method is testing finding an empty space in the down direction", test.isOpen(cBoard, 0, 2, "down"));
    
    int[][] dBoard = {{5, 0, 1, 4}, {2, 2, 2, 2}};
    assertFalse("Method is testing when the board does not find an empty space in the left direction", test.isOpen(dBoard, 1, 3, "left"));
    
    int[][] eBoard = {{5, 0, 1, 4}, {2, 0, 2, 2}};
    assertTrue("Method is testing finding an empty space in the left direction", test.isOpen(eBoard, 1, 3, "left"));
    
    int[][] fBoard = {{5, 0, 1, 4}, {2, 2, 9, 2}};
    assertFalse("Method is testing when the board does not find an empty space in the right direction", test.isOpen(fBoard, 1, 0, "right"));
    
    int[][] gBoard = {{5, 0, 1, 4}, {2, 2, 0, 2}};
    assertTrue("Method is testing finding an empty space in the right direction", test.isOpen(gBoard, 1, 0, "right"));
    
    int[][] hBoard = {{1, 6, 3, 9}, {4, 5, 2, 8}, {7, 4, 10, 11}, {12, 3, 1, 2}};
    assertFalse("Method is testing when the board does not find an empty space in the left up diagonal direction", test.isOpen(hBoard, 1, 1, "left up diagonal"));
    
    int[][] iBoard = {{0, 6, 3, 9}, {4, 5, 2, 8}, {7, 4, 10, 11}, {12, 3, 1, 2}};
    assertTrue("Method is testing finding an empty space in the right direction", test.isOpen(iBoard, 1, 1, "left up diagonal"));
    
    int[][] jBoard = {{1, 6, 3, 9}, {4, 5, 2, 8}, {7, 4, 10, 11}, {12, 3, 1, 2}};
    assertFalse("Method is testing when the board does not find an empty space in the left down diagonal direction", test.isOpen(jBoard, 1, 1, "left down diagonal"));
    
    int[][] kBoard = {{1, 5, 3, 9}, {4, 6, 2, 8}, {0, 4, 10, 11}, {12, 3, 1, 2}};
    assertTrue("Method is testing finding an empty space in the left down digaonal direction", test.isOpen(kBoard, 1, 1, "left down diagonal"));
    
    int[][] lBoard = {{1, 6, 3, 9}, {4, 5, 2, 8}, {7, 4, 10, 11}, {12, 3, 1, 2}};
    assertFalse("Method is testing when the board does not find an empty space in the right up diagonal direction", test.isOpen(lBoard, 1, 1, "right up diagonal"));
    
    int[][] mBoard = {{1, 6, 3, 9}, {4, 5, 2, 8}, {7, 4, 0, 11}, {12, 3, 1, 2}};
    assertTrue("Method is testing finding an empty space in the right up diagonal direction", test.isOpen(mBoard, 3, 1, "right up diagonal"));
    
    int[][] nBoard = {{1, 6, 3, 9}, {4, 5, 2, 8}, {7, 4, 10, 11}, {12, 3, 1, 2}};
    assertFalse("Method is testing when the board does not find an empty space in the right down diagonal direction", test.isOpen(nBoard, 1, 1, "right down diagonal"));
    
    int[][] oBoard = {{1, 6, 3, 9}, {4, 5, 2, 8}, {3, 4, 0, 11}, {12, 3, 1, 2}};
    assertTrue("Method is testing finding an empty space in the right down diagonal direction", test.isOpen(oBoard, 1, 1, "right down diagonal"));
    
    
    /*Testing 0, 1 empty spaces in a particular direction*/
    
    /*Testing 0 empty spaces in a particular direction*/
    int[][] pBoard = {{1, 6, 3, 9}, {4, 5, 2, 8}, {3, 4, 10, 11}, {12, 3, 1, 2}};
    assertFalse("Method is testing finding 0 enpty spaces in a particular direction", test.isOpen(pBoard, 1, 1, "up"));
    
    /*Testing 1 empty space in a particular direction*/
    int[][] rBoard = {{1, 0, 3, 9}, {4, 5, 2, 8}, {3, 4, 10, 11}, {12, 3, 1, 2}};
    assertTrue("Method is testing finding 1 enpty space in a particular direction", test.isOpen(rBoard, 1, 1, "up"));
    
    
    /*Testing empty space in the first, middle, and last position of a line of equal integers in a particular direction*/
    
    /*Testing empty space in the first position of a line of equal integers in a particular direction*/
    int[][] qBoard = {{1, 3, 3, 9}, {4, 3, 2, 8}, {3, 0, 10, 11}, {12, 3, 1, 2}};
    assertTrue("Method is testing finding empty space in first position of a line of equal integers in a particular direction", test.isOpen(qBoard, 3, 1, "up"));
    
    /*Testing an empty space in the middle position of a line of equal integers in a particular direction*/
    int[][] sBoard = {{1, 3, 3, 9}, {4, 0, 2, 8}, {3, 3, 10, 11}, {12, 3, 1, 2}};
    assertTrue("Method is testing finding empty space in middle position of a line of equal integers in a particular direction", test.isOpen(sBoard, 3, 1, "up"));
    
    /*Testing an empty space in the last position of a line of equal integers in a particular direction*/
    int[][] tBoard = {{1, 0, 3, 9}, {4, 3, 2, 8}, {3, 3, 10, 11}, {12, 3, 1, 2}};
    assertTrue("Method is testing finding empty space in last position of a line of equal integers in a particular direction", test.isOpen(tBoard, 3, 1, "up"));
  }
  
  @Test
  public void testThreeThree()
  {
    Gomoku test = new Gomoku();
    
    /*Verifying different directions*/
    
    /*Testing the up and down direction*/
    int[][] dBoard = {{1, 2, 3, 0, 5, 6, 7}, {8, 9, 10, 4, 11, 12, 13}, {14, 15, 16, 4, 17, 18, 19}, {20, 21, 22, 4, 23, 24, 25}, {26, 27, 42, 0, 29, 30, 31}, {31, 32, 33, 34, 35, 36}, {36, 37, 38, 39, 40, 41}};
    assertEquals("Method is ensuring up direction has 3 in a row", 3, test.numberInLine(dBoard, 3, 3, "up"));
    assertFalse("Method is testing a single up direction having a three in a row", test.threeInARow(dBoard, 3, 3));
    
    /*Verifying that the left direction works with up direction*/
    int[][] fBoard = {{1, 2, 3, 0, 5, 6, 7}, {8, 9, 10, 4, 11, 12, 13}, {14, 15, 16, 4, 17, 18, 19}, {0, 4, 4, 4, 0, 24, 25}, {26, 27, 28, 0, 42, 30, 31}, {31, 32, 33, 34, 35, 36, 37}, {37, 38, 39, 0, 41, 42}};
    assertTrue("Method is verifying that the left direction works with up direction", test.threeInARow(fBoard, 3, 3));
    
    /*Verifying that the right direction works with up direction*/
    int[][] gBoard = {{1, 2, 3, 0, 5, 6, 7}, {8, 9, 10, 4, 11, 12, 13}, {14, 15, 16, 4, 17, 18, 19}, {20, 21, 0, 4, 4, 4, 0}, {26, 27, 28, 0, 42, 30, 31}, {31, 32, 33, 34, 35, 36, 37}, {37, 38, 39, 0, 41, 42}};
    assertTrue("Method is verifying that the right direction works with up direction", test.threeInARow(gBoard, 3, 3));
    
    /*Verifying that the left up direction works with up direction*/
    int[][] hBoard = {{0, 2, 3, 0, 5, 6, 7}, {8, 4, 10, 4, 11, 12, 13}, {14, 15, 4, 4, 17, 18, 19}, {20, 21, 22, 4, 23, 24, 25}, {26, 27, 28, 0, 0, 30, 31}, {31, 32, 33, 34, 35, 36, 37}, {37, 38, 39, 0, 41, 42}};
    assertTrue("Method is verifying that the left up direction works with up direction", test.threeInARow(hBoard, 3, 3));
    
    /*Verifying that the right down direction works with up direction*/
    int[][] iBoard = {{1, 2, 3, 0, 5, 6, 7}, {8, 9, 10, 4, 11, 12, 13}, {14, 15, 0, 4, 17, 18, 19}, {20, 21, 22, 4, 23, 24, 25}, {26, 27, 28, 0, 4, 30, 31}, {32, 33, 34, 35, 36, 4, 37}, {38, 39, 40, 41, 42, 43, 0}};
    assertTrue("Method is verifying that the right down direction works with up direction", test.threeInARow(iBoard, 3, 3));
    
    /*Verifying that the right up direction works with up direction*/
    int[][] jBoard = {{1, 2, 3, 0, 5, 6, 0}, {8, 9, 10, 4, 11, 4, 13}, {14, 15, 0, 4, 4, 18, 19}, {20, 21, 22, 4, 23, 24, 25}, {26, 27, 0, 0, 28, 30, 31}, {32, 33, 34, 35, 36, 44, 37}, {38, 39, 40, 41, 42, 43, 50}};
    assertTrue("Method is verifying that the right up direction works with up direction", test.threeInARow(jBoard, 3, 3));
    
    /*Verifying that the left down direction works with up direction*/
    int[][] kBoard = {{1, 2, 3, 0, 5, 6, 7}, {8, 9, 10, 4, 11, 12, 13}, {14, 15, 16, 4, 0, 18, 19}, {20, 21, 22, 4, 23, 24, 25}, {26, 27, 4, 0, 28, 30, 31}, {32, 4, 33, 35, 36, 44, 37}, {0, 39, 40, 41, 42, 43, 50}};
    assertTrue("Method is verifying that the left down direction works with up direction", test.threeInARow(kBoard, 3, 3));
    
    /*Verifying different possible three-three scenarios*/
    
    /*Testing an integer at a position that does not cause any directions to have three straight equal integers with both ends empty*/
    int[][] board = {{1, 2, 3, 4, 5, 6, 7}, {8, 9, 10, 50, 11, 12, 13}, {14, 15, 16, 51, 17, 18, 19}, {20, 21, 22, 52, 23, 24, 25}, {26, 27, 53, 20, 54, 29, 30}, {31, 55, 32, 33, 34, 56, 35}, {57, 36, 37, 38, 39, 40, 58}};
    assertFalse("Method is testing an integer at a position that does not cause any directions to have three straight equal integers with both ends empty", test.threeInARow(board, 3, 3));
    
    /*Testing an integer at a position that causes one direction to have three straight equal integers with the opposite side being empty*/
    int[][] aBoard = {{1, 2, 3, 0, 5, 6, 7}, {8, 9, 10, 4, 11, 12, 13}, {14, 15, 16, 4, 17, 18, 19}, {20, 21, 22, 4, 23, 24, 25}, {26, 27, 53, 0, 54, 29, 30}, {31, 55, 32, 33, 34, 56, 35}, {57, 36, 37, 38, 39, 40, 58}};
    assertFalse("Method is testing an integer at a position that causes one direction to have three straight equal integers with both ends empty", test.threeInARow(aBoard, 3, 3));
    
    /*Testing an integer at a position that causes two directions to have three straight equal integers with the opposite ends of both groups of three being empty*/
    int[][] bBoard = {{1, 2, 3, 0, 5, 6, 7}, {8, 9, 10, 4, 11, 12, 13}, {14, 15, 16, 4, 17, 18, 19}, {20, 21, 0, 4, 4, 4, 0}, {26, 27, 53, 0, 54, 29, 30}, {31, 55, 32, 33, 34, 56, 35}, {57, 36, 37, 38, 39, 40, 58}};
    assertTrue("Method is testing an integer at a position that causes two directions to have three straight equal integers with the opposite ends of both groups of three being empty", test.threeInARow(bBoard, 3, 3));
    
    /*Testing an integer at a position that causes more than two directions to have three straight equal integers with the opposite ends of both groups of three being empty*/
    int[][] cBoard = {{0, 0, 0, 0, 0}, {0, 4, 4, 4, 0}, {0, 0, 4, 4, 0}, {0, 4, 0, 4, 0}, {0, 0, 0, 0, 0}};
    assertTrue("Method is testing an integer at a position that causes more than two directions to have three straight equal integers with the opposite ends of both groups of three being empty", test.threeInARow(cBoard, 1, 3));
  }
  
  @Test
  public void testFourFour()
  {
    Gomoku test = new Gomoku();
    
    
    /*Verifying different directions*/
    
    /*Testing the up and down direction*/
    int[][] dBoard = {{1, 2, 3, 4, 5, 6, 7}, {8, 9, 10, 4, 11, 12, 13}, {14, 15, 16, 4, 17, 18, 19}, {20, 21, 22, 4, 23, 24, 25}, {26, 27, 42, 0, 29, 30, 31}, {31, 32, 33, 52, 34, 35, 36}, {36, 37, 38, 52, 39, 40, 41}};
    assertEquals("Method is verifying that there are 4 in a row in the up direction", 4, test.numberInLine(dBoard, 3, 3, "up"));
    assertFalse("Method is testing a single up direction having three in a row", test.fourInARow(dBoard, 3, 3));
    
    /*Testing the left direction with up direction*/
    int[][] fBoard = {{1, 2, 3, 4, 5, 6, 7}, {8, 9, 10, 4, 11, 12, 13}, {14, 15, 16, 4, 17, 18, 19}, {4, 4, 4, 4, 23, 24, 25}, {26, 27, 42, 60, 29, 30, 31}, {31, 32, 33, 70, 34, 35, 36}, {36, 37, 38, 80, 39, 40, 41}};
    assertTrue("Method is verifying that the left direction works with the up direction", test.fourInARow(fBoard, 3, 3));
    
    /*Testing the right direction with up direction*/
    int[][] gBoard = {{1, 2, 3, 4, 5, 6, 7}, {8, 9, 10, 4, 11, 12, 13}, {14, 15, 16, 4, 17, 18, 19}, {12, 13, 14, 4, 4, 4, 4}, {26, 27, 42, 90, 29, 30, 31}, {31, 32, 33, 10, 34, 35, 36}, {36, 37, 38, 80, 39, 40, 41}};
    assertTrue("Method is verifying that the right direction works with the up direction", test.fourInARow(gBoard, 3, 3));
    
    /*Testing the left up direction with up direction*/
    int[][] hBoard = {{4, 2, 3, 4, 5, 6, 7}, {8, 4, 10, 4, 11, 12, 13}, {14, 15, 4, 4, 17, 18, 19}, {12, 13, 14, 4, 15, 16, 17}, {26, 27, 42, 67, 29, 30, 31}, {31, 32, 33, 89, 34, 35, 36}, {36, 37, 38, 10, 39, 40, 41}};
    assertTrue("Method is verifying that the left up direction works with the up direction", test.fourInARow(hBoard, 3, 3));
    
    /*Testing the right up direction with up direction*/
    int[][] iBoard = {{1, 2, 3, 4, 5, 6, 4}, {8, 9, 10, 4, 11, 4, 13}, {14, 15, 16, 4, 4, 18, 19}, {12, 13, 14, 4, 15, 16, 17}, {26, 27, 42, 10, 29, 30, 31}, {31, 32, 33, 30, 34, 35, 36}, {36, 37, 38, 50, 39, 40, 41}};
    assertTrue("Method is verifying that the left up direction works with the up direction", test.fourInARow(iBoard, 3, 3));
    
    /*Testing the left down direction with up direction*/
    int[][] jBoard = {{1, 2, 3, 4, 5, 6, 7}, {8, 9, 10, 4, 11, 12, 13}, {14, 15, 16, 4, 4, 18, 19}, {12, 13, 14, 4, 15, 16, 17}, {26, 27, 4, 20, 29, 30, 31}, {31, 4, 33, 30, 34, 35, 36}, {0, 37, 38, 60, 39, 40, 41}};
    assertTrue("Method is verifying that the left up direction works with the up direction", test.fourInARow(jBoard, 3, 3));
    
    /*Testing the right down direction with up direction*/
    int[][] kBoard = {{1, 2, 3, 4, 5, 6, 7}, {8, 9, 10, 4, 11, 12, 13}, {14, 15, 16, 4, 4, 18, 19}, {12, 13, 14, 4, 15, 16, 17}, {26, 27, 28, 29, 4, 30, 31}, {31, 32, 33, 30, 34, 4, 36}, {50, 37, 38, 60, 39, 40, 4}};
    assertTrue("Method is verifying that the left up direction works with the up direction", test.fourInARow(kBoard, 3, 3));
    
    /*Testing different possible four-four scenarios*/
    
    /*Testing an integer at a position that does not cause any directions to have four straight equal integers*/
    int[][] board = {{1, 5, 3, 15, 22}, {4, 6, 2, 31, 42}, {7, 4, 10, 40, 32}, {16, 12, 14, 55, 34}, {17, 21, 18, 24, 25}};
    assertFalse("Method is testing an integer at a position that does not cause any direction to have four straight equal integers", test.fourInARow(board, 2, 2));
    
    /*Testing an integer at a position that causes one direction to have four straight equal integers*/
    int[][] aBoard = {{1, 5, 3, 15, 22}, {4, 6, 2, 31, 42}, {7, 4, 10, 40, 32}, {55, 55, 55, 55, 34}, {17, 21, 18, 24, 25}};
    assertFalse("Method is testing an integer at a position that causes one direction to have four straight equal integers", test.fourInARow(aBoard, 3, 3));
    
    /*Testing an integer at a position that causes two directions to have four straight equal integers*/
    int[][] bBoard = {{1, 5, 3, 55, 22}, {4, 6, 2, 55, 42}, {7, 4, 10, 55, 32}, {55, 55, 55, 55, 34}, {17, 21, 18, 24, 25}};
    assertTrue("Method is testing an integer at a position that causes two directions to have four straight equal integers", test.fourInARow(bBoard, 3, 3));
    
    /*Testing an integer at a position that causes more than two directions to have four straight equal integers*/
    int[][] cBoard = {{1, 2, 3, 4, 5, 6, 7}, {8, 9, 10, 4, 11, 12, 13}, {14, 15, 16, 4, 17, 18, 19}, {20, 21, 22, 4, 23, 24, 25}, {26, 27, 4, 20, 4, 29, 30}, {31, 4, 32, 33, 34, 4, 35}, {4, 36, 37, 38, 39, 40, 4}};
    assertTrue("Method is testing an integer at a position that causes more than two directions to have four straight equal integers", test.fourInARow(cBoard, 3, 3));
    
  }
    
  @Test
  public void testCheckGameInPlay()
  {
    Gomoku test = new Gomoku();
    
    /*Testing if nobody has won yet and the game is still in play*/
    int[][] board = {{0, 1, 2, 3, 4, 5}};
    assertTrue("Method is testing if nobody has won yet and the game is still in play", test.checkGameInPlay(board, 0, 0));
    
    /*Testing if the white player has won*/
    int[][] aBoard = {{1, 1, 1, 1, 1}};
    assertFalse("Method is testing if white player has won and the game is still in play", test.checkGameInPlay(aBoard, 0, 0));
    
    /*Testing if the black player has won*/
    int[][] bBoard = {{2, 2, 2, 2, 2}};
    assertFalse("Method is testing if black player has won and the game is still in play", test.checkGameInPlay(bBoard, 0, 0));
    
  }
  
  
  
}
