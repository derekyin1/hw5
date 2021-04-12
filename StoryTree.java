/** Derek Yin 113251504 Recitation Section 1
*  This class defines the StoryTree class that is the data structure used for the Zork game.
*  @author Derek Yin
*/
import java.util.Scanner;
import java.io.*;
import java.util.zip.DataFormatException;
public class StoryTree{
  StoryTreeNode root;
  StoryTreeNode cursor;
  GameState state;
  boolean newFileCreated = false;
  /**
  *This is the constructor for theStoryTree
  *
  */
  public StoryTree(){
    root = new StoryTreeNode("root", "root", "Hello, welcome to Zork!");
    cursor = root;
  }
/**
*This method reads a text file that contains a Zork game, or creates a new Zork file. This is then stored as a StoryTree.
*
*@param filename
*desired Zork text file.
*@return
*the newly created StoryTree from the text file.
*@throws
*DataFormatException when filename is invalid.
*
*/
  public static StoryTree readTree(String filename) throws DataFormatException{
    if (filename.equals("") || filename == null) throw new IllegalArgumentException();
    if ( filename.length() < 5 || !filename.substring(filename.length()-4, filename.length()).equals(".txt")) throw new DataFormatException();
    //handle file not found exception by making empty tree. (catch)
    try {

      Scanner fileIn = new Scanner(new File(filename));
      StoryTree newTree = new StoryTree();
      while (fileIn.hasNextLine()){
        String line = fileIn.nextLine();
        String[] tokens = line.split(" \\| ");
        StoryTreeNode newNode = new StoryTreeNode(tokens[0], tokens[1], tokens[2]);
        StoryTreeNode currentNode = newTree.root;
        int i = 0;
        while (i <= tokens[0].length() - 1 - 2){

          if (tokens[0].charAt(i) == '1'){
            currentNode = currentNode.getLeftChild();

            i+=2;
          }

          else if (tokens[0].charAt(i) == '2'){
            currentNode = currentNode.getMiddleChild();

            i+=2;
          }

          else if (tokens[0].charAt(i) == '3'){
            currentNode = currentNode.getRightChild();

            i+=2;
          }



        }
        newTree.cursor = currentNode;
        newTree.addChild(newNode);


      }
      fileIn.close();
      return newTree;

    }

    catch (FileNotFoundException fnfe){
      StoryTree newTree = new StoryTree();
      newTree.newFileCreated = true;
      return newTree;
    }


  }


/**
*This method saves a StoryTree to either an existing text file or saves a Storytree to a new file in this directory.
*
*@param filename
*desired Zork text file to save to.
*@param tree
*desired tree to save from.
*
*/
  public static void saveTree(String filename, StoryTree tree){
    if (filename.equals("") || filename == null || tree == null) throw new IllegalArgumentException();

    try {

      PrintWriter fileOut = new PrintWriter(new File(filename));
      if (!tree.newFileCreated){
        tree.resetCursor();
        tree.cursor.preorder();
        String toPrint = StoryTreeNode.lines.substring(0, StoryTreeNode.lines.length()-2);
        fileOut.println(toPrint); //TESTING
        fileOut.close();
      }
      else{
        fileOut.println("root | root | Hello, welcome to Zork!");
        fileOut.close();
      }

    }
    catch (FileNotFoundException fnfe){

    }
  }




/**
*This getter returns the cursor of this tree.
*
*@return
* cursor of this tree.
*/
  public StoryTreeNode getCursor(){
    return cursor;
  }
/**
*This getter returns the GameState of this tree.
*
*@return
* GameState of this tree.
*/
  public GameState getGameState(){
    return state;
  }
/**
*This getter returns the position of the cursor.
*
*@return
* position of cursor.
*/
  public String getCursorPosition(){
    return cursor.getPosition();
  }
/**
*This getter returns the message of the cursor.
*
*@return
* Cursor message.
*/
  public String getCursorMessage(){
    return cursor.getMessage();
  }

/**
*This getter returns the option of the cursor.
*
*@return
* Cursor option.
*/
  public String getCursorOption(){
    return cursor.getOption();
  }
/**
*This getter returns an array containing the positions and options of the cursor's children.
*
*@return
* String array containing positions and options of the cursor's children.
*/
  public String[][] getOptions(){
    String[][] options = new String[cursor.numChildren()][2];
    int count = 0;
    if (cursor.getLeftChild() != null){
      options[count][0] = cursor.getLeftChild().getPosition();
      options[count][1] = cursor.getLeftChild().getOption();
      count++;
    }
     if (cursor.getMiddleChild() != null){
      options[count][0] = cursor.getMiddleChild().getPosition();
      options[count][1] = cursor.getMiddleChild().getOption();
      count++;
    }
    if (cursor.getRightChild() != null){
      options[count][0] = cursor.getRightChild().getPosition();
      options[count][1] = cursor.getRightChild().getOption();
      count++;
    }

    return options;
  }
/**
*This method sets the message of the cursor.
*
*@param message
* desired message
*/
  public void setCursorMessage(String message){
    cursor.setMessage(message);
  }
/**
*This method sets the option of the cursor.
*
*@param option
* desired option
*/
  public void setCursorOption(String option){
    cursor.setOption(option);
  }
/**
*This method resets the cursor to the root of this Tree.
*
*/
  public void resetCursor(){
    cursor = root.getLeftChild();
  }
/**
*This method selects a direct child of the cursor to be the new cursor.
*
*@param position
* desired position
*@throws
* InvalidArgumentException when position is null or empty
*@throws
* NodeNotPresentException when direct child does not exist.
*/
  public void selectChild(String position) throws InvalidArgumentException, NodeNotPresentException{
    if (position == null || position.equals("")) throw new InvalidArgumentException("");
    if (!position.equals(cursor.getPosition() + "-1") && !position.equals(cursor.getPosition() + "-2") && !position.equals(cursor.getPosition() + "-3")) throw new InvalidArgumentException("");
    if (position.equals(cursor.getPosition() + "-1") && cursor.getLeftChild() != null) cursor = cursor.getLeftChild();
    else if (position.equals(cursor.getPosition() + "-2") && cursor.getMiddleChild() != null) cursor = cursor.getMiddleChild();
    else if (position.equals(cursor.getPosition() + "-3") && cursor.getRightChild() != null) cursor = cursor.getRightChild();
    else throw new NodeNotPresentException("");
  }
/**
*This overloaded method selects a direct child of the cursor using an integer instead of a position String.
*
*@param child
* desired index of child [1,2,3]
*@throws
* InvalidArgumentException when child is negative or greater than number of direct children of cursor.
*@throws
* NodeNotPresentException when direct child does not exist.
*/
  public void selectChild(int child) throws NodeNotPresentException, InvalidArgumentException{
    if (child < 0 || child > cursor.numChildren()) throw new InvalidArgumentException("");
    if (child == 1){
      if (cursor.getLeftChild() == null) throw new NodeNotPresentException("");
      else cursor = cursor.getLeftChild();
    }
    if (child == 2){
      if (cursor.getMiddleChild() == null) throw new NodeNotPresentException("");
      else cursor = cursor.getMiddleChild();
    }
    if (child == 3){
      if (cursor.getRightChild() == null) throw new NodeNotPresentException("");
      else cursor = cursor.getRightChild();
    }
  }
/**
*This method adds a child to the cursor.
*
*@param option
* desired option
*@param message
* desired message
*@throws
* InvalidArgumentException when option is null or empty
*@throws
* TreeFullException when cursor already has 3 children.
*/
  public void addChild(String option, String message) throws InvalidArgumentException, TreeFullException{
    if (option == null || option.equals("") || message == null || message.equals("")) throw new InvalidArgumentException("");
    if (cursor.numChildren() == 3) throw new TreeFullException("");
    StoryTreeNode newChild = new StoryTreeNode(option, message);
    if (newFileCreated){
      root.setLeftChild(newChild);
      cursor = root.getLeftChild();
      newChild.setPosition("1");
      newFileCreated = false;
    }
    else if (cursor.getLeftChild() == null){
      cursor.setLeftChild(newChild);
      newChild.setPosition(cursor.getPosition() + "-1");
    }
    else if (cursor.getMiddleChild() == null){
      cursor.setMiddleChild(newChild);
      newChild.setPosition(cursor.getPosition() + "-2");
    }
    else if (cursor.getRightChild() == null){
      cursor.setRightChild(newChild);
      newChild.setPosition(cursor.getPosition() + "-3");
    }
    else throw new TreeFullException("");
  }

  public void addChild(StoryTreeNode node){
    if (newFileCreated){
      root.setLeftChild(node);
      cursor = root.getLeftChild();
      node.setPosition("1");
      newFileCreated = false;
    }
    else if (cursor.getLeftChild() == null){
      cursor.setLeftChild(node);
    }
    else if (cursor.getMiddleChild() == null){
      cursor.setMiddleChild(node);
    }
    else if (cursor.getRightChild() == null){
      cursor.setRightChild(node);
    }
  }
/**
*This method removes a child from the cursor, then shifts nodes to the left if needed.
*
*@param position
* desired position
*@return
* removed node, and implicitly removed subtree
*@throws
* NodeNotPresentException when desired node does not exist.
*/

  public StoryTreeNode removeChild(String position) throws NodeNotPresentException{
    StoryTreeNode currentNode = root;
    int endPos = 0;
    int i = 0;
    while (i <= (position.length() - 1 - 2)){
      if (position.charAt(i) == '1'){
        currentNode = currentNode.getLeftChild();
        endPos = 1;
        i+=2;
      }

      else if (position.charAt(i) == '2'){
        currentNode = currentNode.getMiddleChild();
        endPos = 2;
        i+=2;
      }

      else if (position.charAt(i) == '3'){
        currentNode = currentNode.getRightChild();
        endPos = 3;
        i+=2;
      }
    }
    cursor = currentNode;
    StoryTreeNode removed;
    if (endPos == 1){
      removed = cursor.getLeftChild();
      cursor.setLeftChild(null);
    }
    else if (endPos == 2){
      removed = cursor.getMiddleChild();
      cursor.setMiddleChild(null);
    }
    else if (endPos == 3) {
      removed = cursor.getRightChild();
      cursor.setRightChild(null);

    }
    else throw new NodeNotPresentException("");

    this.cursor.leftAlign();
    return removed;

  }




}
