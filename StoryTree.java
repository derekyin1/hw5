public class StoryTree{
  StoryTreeNode root;
  StoryTreeNode cursor;
  GameState state;

  public StoryTree(){
    root = new StoryTreeNode("root", "root", "Hello, welcome to Zork!");
    cursor = root;
  }

  public static StoryTree readTree(String filename) throws DataFormatException{
    if (filename.equals("") || filename == null) throw new IllegalArgumentException();
    if (!filename.substring(filename.length()-4, filename.length()).equals(".txt")) throw new DataFormatException();



  }

  public static void saveTree(String filename, StoryTree tree){
    if (filename.equals("") || filename == null || tree == null) throw new IllegalArgumentException();

  }

  public GameState getGameState(){
    return state;
  }

  public String getCursorPosition(){
    return cursor.getPosition();
  }

  public String getCursorMessage(){
    return cursor.getMessage();
  }

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

  public void setCursorMessage(String message){
    cursor.setMessage(message);
  }

  public void setCursorOption(String option){
    cursor.setOption(option);
  }

  public void resetCursor(){
    cursor = root;
  }

  public void selectChild(String position) throws InvalidArgumentException, NodeNotPresentException{
    if (position == null || position.equals("")) throw new InvalidArgumentException();
    if (!position.equals(cursor.getPosition() + "-1") && !position.equals(cursor.getPosition() + "-2") && !position.equals(cursor.getPosition() + "-3")) throw new InvalidArgumentException();
    if (position.equals(cursor.getPosition() + "-1") && cursor.getLeftChild() != null) cursor = cursor.getLeftChild();
    else if (position.equals(cursor.getPosition() + "-2") && cursor.getMiddleChild() != null) cursor = cursor.getMiddleChild();
    else if (position.equals(cursor.getPosition() + "-3") && cursor.getRightChild() != null) cursor = cursor.getRightChild();
    else throw new NodeNotPresentException();
  }

  public void addChild(String option, String message) throws InvalidArgumentException, TreeFullException{
    if (option == null || option.equals("") || message == null || message.equals("")) throw new InvalidArgumentException();
    if (cursor.numChildren() == 3) throw new TreeFullException();
    StoryTreeNode newChild = new StoryTreeNode(option, message);
    if (cursor.getLeftChild() == null){
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
    else throw new TreeFullException();
  }

  public StoryTreeNode removeChild(String position){
    StoryTreeNode nodePtr = root;
    StoryTreeNode parent = null;
    while (nodePtr != null){
      parent = nodePtr;

    }
  }

  public void shiftLeft(String position){
    if (node == null) return;
    else{

//handle shifting here, then rename node
      shiftLeft(node.getLeftChild());
      shiftLeft(node.getMiddleChild());
      shiftLeft(node.getRightChild());
    }




//recursively call shiftLeft on the child subtrees left, middle, right
  }

  public void selectNode(String position){
    StoryTreeNode currentNode = root;
    int i = 0;
    while (i <= (position.length + 1) / 2)){
      if (position.charAt(i) == '1') currentNode = currentNode.getLeftChild();
      if (position.charAt(i) == '2') currentNode = currentNode.getMiddleChild();
      if (position.charAt(i) == '3') currentNode = currentNode.getRightChild();

      i+=2;
    }
    cursor = currentNode;
  }


}
