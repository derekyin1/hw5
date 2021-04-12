/** Derek Yin 113251504 Recitation Section 1
*  This class defines the StoryTreeNode that makes up a StoryTree.
*
*  @author Derek Yin
*/
import java.io.*;
public class StoryTreeNode{
  static final String WIN_MESSAGE = "YOU WIN";
  static final String LOSE_MESSAGE = "YOU LOSE";
  String position;
  String option;
  String message;
  StoryTreeNode leftChild;
  StoryTreeNode middleChild;
  StoryTreeNode rightChild;
  static String lines = "";
/**
*This is the constructor for the StoryTreeNode
*
*@param initPosition
*desired position
*@param initOption
*desired option
*@param initMessage
*desired message
*
*/
  public StoryTreeNode(String initPosition, String initOption, String initMessage){
    position = initPosition;
    option = initOption;
    message = initMessage;
  }
/**
*This is a constructor for this class that does not instantiate the position varible.
*
*@param initOption
*desired option
*@param initMessage
*desired message
*
*/
  public StoryTreeNode(String initOption, String initMessage){
    option = initOption;
    message = initMessage;
  }
/**
*This method returns true if this node is a leaf, false otherwise.
*
*@return
* T/F based on leaf status of this node.
*/
  public boolean isLeaf(){
    if (leftChild == null && middleChild == null && rightChild == null) return true;
    return false;
  }
/**
*This method returns true if this node is a winning node, false otherwise.
*
*@return
* T/F based on winning status of this node.
*/
  public boolean isWinningNode(){
     if (this.isLeaf() && message.contains(WIN_MESSAGE)) return true;
     return false;
  }
/**
*This method returns true if this node is a losing node, false otherwise.
*
*@return
* T/F based on winning status of this node.
*/
  public boolean isLosingNode(){
    if (this.isLeaf() && !message.contains(WIN_MESSAGE)) return true;
    return false;
  }
/**
*This getter returns position of this node.
*
*@return
* position of this node.
*/
  public String getPosition(){
    return position;
  }
/**
*This method sets a new position for this node.
*
*@param
* desired position.
*/
  public void setPosition(String newPosition){
    position = newPosition;
  }
/**
*This getter returns this node's option.
*
*@return
* option of this node.
*/
  public String getOption(){
    return option;
  }
/**
*This method sets a new option for this node.
*
*@param
* desired option.
*/
  public void setOption(String newOption){
    option = newOption;
  }
/**
*This getter returns the message of this node.
*
*@return
* message of this node.
*/
  public String getMessage(){
    return message;
  }
/**
*This method sets a new message for this node.
*
*@param
* desired message.
*/
  public void setMessage(String newMessage){
    message = newMessage;
  }
/**
*This getter returns the left child of this node.
*
*@return
* left child of this node.
*/
  public StoryTreeNode getLeftChild(){
    return leftChild;
  }
/**
*This method sets a new left child for this node.
*
*@param
* desired child.
*/
  public void setLeftChild(StoryTreeNode newLeftChild){
    leftChild = newLeftChild;
  }
/**
*This getter returns the middle child of this node.
*
*@return
* middle child of this node.
*/
  public StoryTreeNode getMiddleChild(){
    return middleChild;
  }
/**
*This method sets a new  middle child for this node.
*
*@param
* desired child.
*/
  public void setMiddleChild(StoryTreeNode newMiddleChild){
    middleChild = newMiddleChild;
  }
/**
*This getter returns the right child of this node.
*
*@return
* right child of this node.
*/
  public StoryTreeNode getRightChild(){
    return rightChild;
  }
/**
*This method sets a new right child for this node.
*
*@param
* desired child.
*/
  public void setRightChild(StoryTreeNode newRightChild){
    rightChild = newRightChild;
  }
/**
*This getter returns the number of children this node has.
*
*@return
* number of children this node has.
*/
  public int numChildren(){
    int counter = 0;
    if (isLeaf()) return 0;
    if (getLeftChild()!=null) counter++;
    if (getMiddleChild()!=null) counter++;
    if (getRightChild()!=null) counter++;
    return counter;

  }
/**
*This recursive method stores the Zork file in String format to be printed later.
*
*/
  public void preorder(){
    lines += this.getPosition() + " | " + this.getOption() + " | " + this.getMessage() + "\n";
    if (this.getLeftChild() != null) this.getLeftChild().preorder();
    if (this.getMiddleChild() != null) this.getMiddleChild().preorder();
    if (this.getRightChild() != null) this.getRightChild().preorder();

  }
//remove the Child, then left align. After left alignment, rename position.

/**
*This recursive method aligns the children of this node to the left and renames their position variables accordingly.
*
*/
  public void leftAlign(){
    if (this.isLeaf()) return;
    if (this.numChildren() == 2){
      if (this.getLeftChild() == null){
//move middle to left, then right to middle
        String midPos = this.getMiddleChild().getPosition();
        this.getMiddleChild().setPosition(midPos.substring(midPos.length()-1, midPos.length()) + "1");
        this.setLeftChild(this.getMiddleChild());
        String rightPos = this.getRightChild().getPosition();
        this.getRightChild().setPosition(rightPos.substring(rightPos.length()-1, rightPos.length()) + "2");
        this.setMiddleChild(this.getRightChild());
        this.setRightChild(null);
      }
      else if (this.getMiddleChild() == null){
        String rightPos = this.getRightChild().getPosition();
        this.getRightChild().setPosition(rightPos.substring(rightPos.length()-1, rightPos.length()) + "2");
        this.setMiddleChild(this.getRightChild());
        this.setRightChild(null);
      }
    }
    else if (this.numChildren() == 1){
      if (this.getLeftChild() == null && this.getMiddleChild() == null){
        String rightPos = this.getRightChild().getPosition();
        this.getRightChild().setPosition(rightPos.substring(rightPos.length()-1, rightPos.length()) + "1");
        this.setLeftChild(this.getRightChild());
        this.setRightChild(null);
      }
      else if (this.getLeftChild() == null && this.getRightChild() == null){
        String midPos = this.getMiddleChild().getPosition();
        this.getMiddleChild().setPosition(midPos.substring(midPos.length()-1, midPos.length()) + "1");
        this.setLeftChild(this.getMiddleChild());
      }

    }
    if (this.getLeftChild() != null) this.getLeftChild().leftAlign();
    if (this.getMiddleChild() != null) this.getMiddleChild().leftAlign();
    if (this.getRightChild() != null) this.getRightChild().leftAlign();
  }



}
