public class StoryTreeNode{
  static final String WIN_MESSAGE = "YOU WIN";
  static final String LOSE_MESSAGE = "YOU LOSE";
  String position;
  String option;
  String message;
  StoryTreeNode leftChild;
  StoryTreeNode middleChild;
  StoryTreeNode rightChild;

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
     if (this.isLeaf() && message.equals(WIN_MESSAGE)) return true;
     return false;
  }

  public boolean isLosingNode(){
    if (this.isLeaf() && message.equals(LOSE_MESSAGE)) return true;
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

}
