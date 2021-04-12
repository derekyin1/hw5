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

  public StoryTreeNode(String initPosition, String initOption, String initMessage){
    position = initPosition;
    option = initOption;
    message = initMessage;
  }

  public StoryTreeNode(String initOption, String initMessage){
    option = initOption;
    message = initMessage;
  }

  public boolean isLeaf(){
    if (leftChild == null && middleChild == null && rightChild == null) return true;
    return false;
  }

  public boolean isWinningNode(){
     if (this.isLeaf() && message.contains(WIN_MESSAGE)) return true;
     return false;
  }

  public boolean isLosingNode(){
    if (this.isLeaf() && !message.contains(WIN_MESSAGE)) return true;
    return false;
  }

  public String getPosition(){
    return position;
  }

  public void setPosition(String newPosition){
    position = newPosition;
  }

  public String getOption(){
    return option;
  }

  public void setOption(String newOption){
    option = newOption;
  }

  public String getMessage(){
    return message;
  }

  public void setMessage(String newMessage){
    message = newMessage;
  }

  public StoryTreeNode getLeftChild(){
    return leftChild;
  }

  public void setLeftChild(StoryTreeNode newLeftChild){
    leftChild = newLeftChild;
  }

  public StoryTreeNode getMiddleChild(){
    return middleChild;
  }

  public void setMiddleChild(StoryTreeNode newMiddleChild){
    middleChild = newMiddleChild;
  }

  public StoryTreeNode getRightChild(){
    return rightChild;
  }

  public void setRightChild(StoryTreeNode newRightChild){
    rightChild = newRightChild;
  }

  public int numChildren(){
    int counter = 0;
    if (isLeaf()) return 0;
    if (getLeftChild()!=null) counter++;
    if (getMiddleChild()!=null) counter++;
    if (getRightChild()!=null) counter++;
    return counter;

  }

  public void preorder(){
    lines += this.getPosition() + " | " + this.getOption() + " | " + this.getMessage() + "\n";
    if (this.getLeftChild() != null) this.getLeftChild().preorder();
    if (this.getMiddleChild() != null) this.getMiddleChild().preorder();
    if (this.getRightChild() != null) this.getRightChild().preorder();

  }
//remove the Child, then left align. After left alignment, rename position.
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
