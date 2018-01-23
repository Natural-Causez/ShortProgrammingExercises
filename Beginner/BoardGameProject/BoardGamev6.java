//Author - Oliver Etherington
//Trivia Question-based board game
//v6 changelog - Addition of active Leaderboard (Updates every move)
//             - Feature to load a previously saved game or start a new game
//Plays until all questions have been answered or one player reaches the final position on the board.
//Leaderboard is sorted by number of questions answered correctly.

////////// LIBRARIES ///////////
import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/////////////////////////////////


class BoardGamev6 {
  public static void main (String [] param) {
    newOrLoad();
    System.exit(0);
  }//END main

  public static void newOrLoad() {
    ///////////////////////////// BLANK ARGUMENTS FOR game() ///////////////////////////
    String[] loadplayers = {""}; int[] loadscores = {0}; int[] lplayerposition = {0};
    int quno = 1; int playerturn = 0; Boolean load = false;
    ////////////////////////////////////////////////////////////////////////////////////

    String command = input("Would you like to load a previous save (Load) or " +
                            "start a new game (New)? (Load/New):");
    if (command.equalsIgnoreCase("Load")) {
      loadGame();
    }
    else if (command.equalsIgnoreCase("New")) {
      File file = new File("savegame.txt");
      game(load, loadplayers, loadscores, lplayerposition, quno, playerturn);
    }//END if
  }//END newOrLoad()

  public static void loadGame() {
    String fileName = "savegame.txt"; //File to be opened

    try {
      Scanner fileData = new Scanner(new File(fileName));
      //Load slot1
      String line = fileData.nextLine();
      line = line.trim();
      String[] slot1 = line.split(" ");

      //Load slot2
      line = fileData.nextLine();
      line = line.trim();
      String[] slot2 = line.split(" ");

      //Load slot3
      line = fileData.nextLine();
      line = line.trim();
      String[] slot3 = line.split(" ");

      //Load playerboardposition
      line = fileData.nextLine();
      line = line.trim();
      String[] tempboardpos = line.split(" ");
      int[] lplayerposition = new int[tempboardpos.length];
      for (int i = 0; i <= (tempboardpos.length-1); i++) {
        lplayerposition[i] = Integer.parseInt(tempboardpos[i]);
      }//END for

      //Loads playernames
      line = fileData.nextLine();
      line = line.trim();
      String[] loadplayers = line.split(" ");
      //for (int i = 0; i <= (loadplayers.length-1); i++) {
      //  System.out.println(loadplayers[i]);
      //}//END for

      //Loads current scores
      line = fileData.nextLine();
      line = line.trim();
      String[] strloadscores = line.split(" ");
      int[] loadscores = new int[strloadscores.length];
      for (int s = 0; s <= (strloadscores.length-1); s++) {
        //Converts from String[] to int[]
        loadscores[s] = Integer.parseInt(strloadscores[s]);
      }//END for

      //Load current question number
      line = fileData.nextLine();
      line = line.trim();
      int lquno = Integer.parseInt(line);

      //Load current player turn
      line = fileData.nextLine();
      line = line.trim();
      int lplayerturn = Integer.parseInt(line);


      fileData.close(); //Close file

      Boolean load = true;
      game(load, loadplayers, loadscores, lplayerposition, lquno, lplayerturn);
    } //END try

    catch (IOException e) {
      //Create new txt file
      e.printStackTrace();
      File file = new File("savegame.txt");
      try {
        ///////////////// BLANK ARGUMENTS FOR game() ///////////////////
        String[] loadplayers = {""}; int[] loadscores = {0}; int[] lplayerposition = {0};
        int quno = 1; int playerturn = 0; Boolean load = false;
        ////////////////////////////////////////////////////////////////
        file.createNewFile();
        game(load, loadplayers, loadscores, lplayerposition, quno, playerturn);
      }//END try
      catch (IOException ioe) {
        ioe.printStackTrace();
        print("Error creating file.");
      }//END catch
    } //END catch
  }//END loadGame

  //Primary function of executing the game
  public static void game(Boolean load, String[] loadplayers, int[] loadscores, int[] lplayerposition, int lquno, int lplayerturn) {
    ////////////////////// STARTING BOARD CONSTANTS /////////////////////////////////
    //Slot# references the 'row' within the space on the board
    //Each element in each array wraps to next line in corresponing 'slot'
    String[] slot1 = new String[] {"\t", "\t", "\t", "\t",
                                   "\t", "\t", "\t", "\t",
                                   "\t", "\t", "\t", "\t",
                                   "\t", "\t", "\t", "\t", "\t"};

    String[] slot2 = new String[] {"\t", "\t", "\t", "\t",
                                   "\t", "\t", "\t", "\t",
                                   "\t", "\t", "\t", "\t",
                                   "\t", "\t", "\t", "\t", "\t"};

    String[] slot3 = new String[] {"\t", "\t", "\t", "\t",
                                   "\t", "\t", "\t", "\t",
                                   "\t", "\t", "\t", "\t",
                                   "\t", "\t", "\t", "\t", "\t"};
    String piece1 = "\t 1\t|\t2\t|\t3\t|\t4\t|\n" +
                    "\t\t|\t\t|\t\t|\t\t|\n" +
                    "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n" +
                    "\t\t|\t\t|\t\t|\t\t|\n" +
                    "1\t\t|\t\t|\t\t|\t\t|\n" +
                    "\t\t|\t\t|\t\t|\t\t|\n" +
                    "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n" +
                    "\t\t|\t\t|\t\t|\t\t|\n" +
                    "2\t\t|\t\t|\t\t|\t\t|\n" +
                    "\t\t|\t\t|\t\t|\t\t|\n" +
                    "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n" +
                    "\t\t|\t\t|\t\t|\t\t|\n" +
                    "3\t\t|\t\t|\t\t|\t\t|\n" +
                    "\t\t|\t\t|\t\t|\t\t|\n" +
                    "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n" +
                    "\t\t|\t\t|\t\t|\t\t|\n" +
                    "4\t\t|\t\t|\t\t|\t\t|\n" +
                    "\t\t|\t\t|\t\t|\t\t|\n";

    //Create GUI to display board graphic
    JFrame f = new JFrame("Board");
    JPanel upperPanel = new JPanel();
    f.getContentPane().add(upperPanel, "North");
    upperPanel.add(new JTextArea(piece1));

    f.pack();
    //f.setVisible(true);
    //////////////////////////////////////////////////////////////////////////////////

    ////////// STARTING LEADERBOARD CONSTANTS /////////////////
    String score = "CURRENT SCORES:\n";
    JFrame l = new JFrame("Leader");
    JPanel leaderPanel = new JPanel();
    leaderPanel.setPreferredSize(new Dimension(150, 200));
    l.getContentPane().add(leaderPanel, "North");
    leaderPanel.add(new JTextArea(score));

    l.pack();
    ///////////////////////////////////////////////////////////

    //////////////////////// VARIABLES ////////////////////////////////////
    int noplayers = 1;
    //playerqucorrect tracks number of questions answered correctly
    //For both playerboardposition & playerqucorrect - Declaration like this
    //automatically assigns all positions to 0
    final int numberofquestions = 13; //Use as CONSTANT - to run game until
                                      //a certain number of questions have been answered
    int maxplayers = 5; //Change number to edit maximum number of players
    String[] playernames = new String[maxplayers];
    int[] playerboardposition = new int[maxplayers]; //Index values correspond to playernames
    int[] playerqucorrect = new int[maxplayers]; //Index values correspond to playernames
    int questionno = 1; boolean gameactive = true; int playerturn = 0;
    //playerturn is an index reference to whose turn it is
    //- references index value NOT playername
    ///////////////////////////////////////////////////////////////////////

    if (!load) {
      Boolean playersformat = false;
      while (!playersformat) {
        try {
          noplayers = Integer.parseInt(input("Hi! Welcome to Trivial board!\nHow many players will be participating today? (Max: " + maxplayers + ")"));
          if (noplayers > maxplayers) {
            print("You have entered more than the max number! (Max: " + maxplayers + ")");
          }
          else {
            playersformat = true;
          }//END if
        }//END try
        catch (NumberFormatException x) {
          print("You must enter an integer!");
        }//END catch
      }//END while
      
      //String[] playernames = new String[noplayers]; //Must be defined after noplayers is re-defined
      for (int x = 0; x <= (noplayers-1); x++) {
          String inputname = "";
          inputname = input("Enter player " + (x+1) + "'s name:");
          print("Great to meet you, " + inputname + ".\nGood Luck!");
          playernames[x] = inputname;
        }//END for
     
      print("You will each have 3 tries to get your question correct.\n" +
            "If your answer is still incorrect, you shall miss your roll!");
    }
    else {
      questionno = lquno;
      playerturn = lplayerturn;
      
      playernames = Arrays.copyOf(loadplayers, loadplayers.length);
      playerqucorrect = Arrays.copyOf(loadscores, loadscores.length);
      playerboardposition = Arrays.copyOf(lplayerposition, lplayerposition.length);
      noplayers = playernames.length;

      /////// Reconstruct board and then update GUI //////
      piece1 = updateBoard(playernames, playerboardposition, noplayers);
      upperPanel.removeAll();
      upperPanel.add(new JTextArea(piece1));
      SwingUtilities.updateComponentTreeUI(f);
      /////////////////////////////////////////////////////

      /////// Reconstruct leaderboard and update GUI ///////
      score = leaderboard(noplayers, playerqucorrect, playernames, questionno, playerturn);
      leaderPanel.removeAll();
      leaderPanel.add(new JTextArea(score));
      SwingUtilities.updateComponentTreeUI(l);
      //////////////////////////////////////////////////////
    }//END if

    f.setVisible(true);
    l.setVisible(true);

    while (gameactive) {
      if (playerturn > (noplayers-1)) {
        playerturn = playerturn % noplayers;
      }//END if
      
      String nextquestion = getQuestion(questionno);
      String ans = input(playernames[playerturn] + "'s turn!\n"+ "Question " + questionno + ":\n" + nextquestion);
      boolean correct = false; int tries = 0;
      while (!correct) {
        print("Interesting answer...");
        boolean attempt = false;
        attempt = getAnswer(questionno, ans);
        if (attempt) {
          print("You answer is correct! Click Ok to roll the dice and move on the board!");
          int movespaces = rollDice();
          while (movespaces == 6) {
            print("You rolled a 6! Do not move!");
            print("Roll again!");
            movespaces = rollDice();              
          }//END while
            
          playerqucorrect[playerturn]++;
          print("You have moved " + movespaces + " spaces on the board!");
          questionno = questionno + 1;
          playerturn = playerturn + 1;
          correct = true;
          movePlayer(movespaces, playerturn, playerboardposition);

          ///////Reconstruct board and then update GUI//////
          piece1 = updateBoard(playernames, playerboardposition, noplayers);
          upperPanel.removeAll();
          upperPanel.add(new JTextArea(piece1));
          SwingUtilities.updateComponentTreeUI(f);
          //////////////////////////////////////////////////

          ///////Reconstruct leaderboard and update GUI ///////
          score = leaderboard(noplayers, playerqucorrect, playernames, questionno, playerturn);
          leaderPanel.removeAll();
          leaderPanel.add(new JTextArea(score));
          SwingUtilities.updateComponentTreeUI(l);
          /////////////////////////////////////////////////////

        }
        else {
          tries = tries + 1;
          if (tries == 3) {
            print("Incorrect.\nOut of tries! Miss your roll!");
            questionno = questionno + 1;
            playerturn = playerturn + 1;
            correct = true;
            tries = 0;
          }
          else {
            print("Incorrect. Try again.\n" +
                  "Tries: " + tries);
            ans = input("Question " + questionno + ":\n" + nextquestion);
          }//END if
        }//END if

      }//END while

      if(checkEndGame(playerboardposition, playerturn, playernames)) {
        gameactive = false;
      }//END if
      else if (questionno == (numberofquestions + 1)) {
        print("All questions complete. Congratulations!\nGame will now exit.");
        gameactive = false;
      }//END if

    }//END while
  }//END game

  //Generates a random number between 1 and 6
  public static int rollDice() {
    Random rg = new Random();
    int randomInt = rg.nextInt(6) + 1; //nextInt(6) is up to but NOT including
    return randomInt;
  }//END rollDice

  public static String getQuestion(int qno) {
    //Define all questions inside this array;
    //Number of questions to be answered can be edited inside game() [numberofquestions]
    String[] quarray = {"What is the capital of Bulgaria?",
                        "How many states are there in the United States of America?",
                        "How many rings are on the Olympic flag?",
                        "What colour is vermilion a shade of?",
                        "Which was the most successful Grand National Horse?",
                        "What is the name of Dennis the Menace's dog?",
                        "In golf, what name is given to the No 3 wood?",
                        "Whose nose grew when they told a lie?",
                        "Which company did Bill Gates famously found?",
                        "What is a 'Winston Churchill?'",
                        "What planet is closest to the sun?",
                        "What is the largest state in the USA?",
                        "What chess piece could be a member of a church?"};
    return quarray[qno-1];
  }//END getQuestion

  public static Boolean getAnswer(int ano, String guess) {
    //Define all answers inside this array;
    //Correspond question number to answer per array element number
    String[] ansarray = {"Sofia",
                          "52",
                          "5",
                          "Red",
                          "Red Rum",
                          "Knasher",
                          "Spoon",
                          "Pinocchio",
                          "Microsoft",
                          "Cigar",
                          "Mercury",
                          "Alaska",
                          "Bishop"};
    if (guess.equalsIgnoreCase(ansarray[ano-1])) {
      return true;
    }
    else {
      return false;
    }//END if
  }//END getAnswer

  public static String updateBoard(String[] playernames, int[] playerboardposition, int noplayers) {

    ///////////////////////// BOARD VARIABLES ///////////////////
    String[] slot1 = new String[] {"\t", "\t", "\t", "\t",
                                   "\t", "\t", "\t", "\t",
                                   "\t", "\t", "\t", "\t",
                                   "\t", "\t", "\t", "\t", "\t"};

    String[] slot2 = new String[] {"\t", "\t", "\t", "\t",
                                   "\t", "\t", "\t", "\t",
                                   "\t", "\t", "\t", "\t",
                                   "\t", "\t", "\t", "\t", "\t"};

    String[] slot3 = new String[] {"\t", "\t", "\t", "\t",
                                   "\t", "\t", "\t", "\t",
                                   "\t", "\t", "\t", "\t",
                                   "\t", "\t", "\t", "\t", "\t"};
    //////////////////////////////////////////////////////////////
    //Keep local copy of variables to be constantly updated and board state tracked


    for (int i = 0; i < noplayers; i++) {
      if (playerboardposition[i] < slot1.length) {
          if (slot1[playerboardposition[i]].equals("\t")) {
            slot1[playerboardposition[i]] = playernames[i] + "\t";
          }
          else if (slot2[playerboardposition[i]].equals("\t")) {
            slot2[playerboardposition[i]] = playernames[i] + "\t";
          }
          else if (slot3[playerboardposition[i]].equals("\t")) {
            slot3[playerboardposition[i]] = playernames[i] + "\t";
          }
          else {
            print("The improbable has happened. Board cannot fit.");
          }//END if
        }//END if
    }//END for


    //Generates board by piecing together the slot# arrays
    String piece1 = "\t 1\t|\t2\t|\t3\t|\t4\t|\n" +
                    "\t\t|\t\t|\t\t|\t\t|\n" +
                    "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n" +
                    "\t" + slot1[1] + "|\t" + slot1[2] + "|\t" + slot1[3] + "|\t" + slot1[4] + "|\n" +
                    "1\t" + slot2[1] + "|\t" + slot2[2] + "|\t" + slot2[3] + "|\t" + slot2[4] + "|\n" +
                    "\t" + slot3[1] + "|\t" + slot3[2] + "|\t" + slot3[3] + "|\t" + slot3[4] + "|\n" +
                    "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n" +
                    "\t" + slot1[5] + "|\t" + slot1[6] + "|\t" + slot1[7] + "|\t" + slot1[8] + "|\n" +
                    "2\t" + slot2[5] + "|\t" + slot2[6] + "|\t" + slot2[7] + "|\t" + slot2[8] + "|\n" +
                    "\t" + slot3[5] + "|\t" + slot3[6] + "|\t" + slot3[7] + "|\t" + slot3[8] + "|\n" +
                    "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n" +
                    "\t" + slot1[9] + "|\t" + slot1[10] + "|\t" + slot1[11] + "|\t" + slot1[12] + "|\n" +
                    "3\t" + slot2[9] + "|\t" + slot2[10] + "|\t" + slot2[11] + "|\t" + slot2[12] + "|\n" +
                    "\t" + slot3[9] + "|\t" + slot3[10] + "|\t" + slot3[11] + "|\t" + slot3[12] + "|\n" +
                    "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n" +
                    "\t" + slot1[13] + "|\t" + slot1[14] + "|\t" + slot1[15] + "|\t" + slot1[16] + "|\n" +
                    "4\t" + slot2[13] + "|\t" + slot2[14] + "|\t" + slot2[15] + "|\t" + slot2[16] + "|\n" +
                    "\t" + slot3[13] + "|\t" + slot3[14] + "|\t" + slot3[15] + "|\t" + slot3[16] + "|\n";

    saveBoard(slot1, slot2, slot3, playerboardposition);
    return piece1;
  }//END updateBoard

  public static String leaderboard(int noplayers, int[] playerqucorrect, String[] playernames, int quno, int playerturn) {
    //Leaderboard is sorted by number of questions answered correctly.
    //Both leading[] and qucorrectcopy[] re-copied every time to
    //reconstruct leaderboard actively
    String leading[] = Arrays.copyOf(playernames, noplayers);
    int[] qucorrectcopy = Arrays.copyOf(playerqucorrect, playerqucorrect.length);

    ////////////////////// BUBBLE SORT ////////////////////// (Modified)
    boolean swapped = true;
    int j = 0;
    int tmp;
    String strtmp;
    for (int m = playerqucorrect.length; m >= 0; m--) {
      for (int i = 0; i < (playerqucorrect.length - 1); i++) {
        //Compares whether the question scores are higher or not
        if (qucorrectcopy[i] < qucorrectcopy[i + 1]) {
          //But swaps the new string array relative to new position
          tmp = qucorrectcopy[i];
          qucorrectcopy[i] = qucorrectcopy[i + 1];
          qucorrectcopy[i + 1] = tmp;

          strtmp = leading[i];
          leading[i] = leading[i+1];
          leading[i+1] = strtmp;
        }//END if
      }//END for
    }//END for
    /////////////////////////////////////////////////////////

    /*//////////////////// TEST //////////////////////
    String leadstr = "{";
    for (int h = 0; h <= (leading.length-2); h++) {
      leadstr = leadstr + leading[h] + ", ";
    }//END For
    leadstr = leadstr + leading[(leading.length-1)] + "}";
    System.out.println(leadstr);
    //////////////////// TEST ///////////////////////*/

    //Constructs string to return for display on leaderPanel
    String score = "CURRENT SCORES:\n\n";
    for (int s = 0; s <= (noplayers-1); s++) {
      if (leading[s] == null) {
        continue;
      }
      else {
        score = score + (s+1) + ") " + leading[s] + "\n";
      }//END if
    }//END for
    saveScores(playernames, playerqucorrect, quno, playerturn);
    return score;
  }//END leaderboard

  public static void movePlayer(int spaces, int playerturn, int[] playerboardposition) {
    if (playerboardposition[playerturn-1] + spaces > 16) {
      print("Too far! Do not move position.");
    }
    else {
      playerboardposition[playerturn-1] += spaces;
    }//END if
  }//END movePlayer

  public static boolean checkEndGame(int[] playerboardposition, int playerturn, String[] playernames) {
    if (playerboardposition[playerturn-1] == 16) {
      print("Game over. " + playernames[playerturn-1] + " has reached the end of the board.\nCongratulations! The game will now exit.");
      return true;
    }//END if

    return false;
  }

  public static void saveBoard(String[] slot1, String[] slot2, String[] slot3, int[] playerboardposition) {
    /////Create strings from arrays to be saved to file ///
    String pos1 = ""; String pos2 = ""; String pos3 = "";
    for (int a = 1; a <= 16; a++) {
      pos1 = pos1 + slot1[a] + " ";
      pos2 = pos2 + slot2[a] + " ";
      pos3 = pos3 + slot3[a] + " ";
    }//END for

    String playerpos = "";
    for (int p = 0; p <= (playerboardposition.length-1); p++) {
      playerpos = playerpos + playerboardposition[p] + " ";
    }//END for
    ///////////////////////////////////////////////////////

    //////////SAVE UPDATED BOARD TO .txt FILE ///////
    try {
      String saveFile = "savegame.txt"; //Object to write to file
      File file = new File(saveFile);
      FileWriter toFile = new FileWriter(saveFile);
      toFile.write(pos1 + "\n" + pos2 + "\n" + pos3);
      toFile.write("\n" + playerpos + "\n");
      toFile.flush();
      toFile.close();
    } //END try

    catch (IOException e) {
      print("Error: File not found. (Did you move the file? :( )");
      e.printStackTrace();
    } //END catch
    ///////////////////////////////////////////////////
  }//END saveBoard

  public static void saveScores (String[] playernames, int[] playerqucorrect, int quno, int playerturn) {
    /////Create strings from arrays to be saved to file///////
    String names = ""; String scores = "";
    for (int i = 0; i <= (playernames.length-1); i++) {
      if (playernames[i] != null) {
        names = names + playernames[i] + " ";
        scores = scores + playerqucorrect[i] + " ";
      }//END if
    }//END For
    ////////////////////////////////////////////////////////////

    /////////////SAVE UPDATED SCORES TO .txt FILE//////////////
    try {
      String saveFile = "savegame.txt";
      FileWriter toFile = new FileWriter(saveFile, true); //Object to write to file
      toFile.write(names + "\n" + scores + "\n");
      toFile.write(quno + "\n" + playerturn);
      toFile.close();
    }//END try

    catch (IOException e) {
      print("Error: File not found. (Did you move the file? :( )");
      e.printStackTrace();
    }//END catch
    ////////////////////////////////////////////////////////////
  }//END saveScores

  public static String input(String inp) {
    String z = "";
    z = JOptionPane.showInputDialog(inp);
    return z;
  }//END input


  public static void print(String msg) {
    JOptionPane.showMessageDialog(null, msg);
  }//END print
}//END class
