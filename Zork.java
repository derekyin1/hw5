import java.util.Scanner;
import java.util.zip.DataFormatException;
public class Zork{
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    boolean fileInput = true;
    while (fileInput){
      System.out.println("Hello and Welcome to Zork!\n");
      System.out.println("Please enter the file name:");
      String filename = in.nextLine();
      // load file here..
      try{
        StoryTree story = StoryTree.readTree(filename);
        System.out.println("\nLoading game from file...\n");
        System.out.println("File loaded!\n");
        boolean init = true;
        while (init){
          System.out.println("Would you like to edit (E), play (P), or quit (Q)?\n");
          String choice = in.nextLine();

          if (choice.equals("e") || choice.equals("E")){
            editTree(story);
          }
          else if (choice.equals("p") || choice.equals("P")){
          }
          else if (choice.equals("Q") || choice.equals("q")){
            init = false;
            fileInput = false;
          }
          else{
            System.out.println("Invalid input.");
            init = false;
            init = true;
          }
        }
      }
      catch (DataFormatException e){
        System.out.println("Invalid file type.");
        fileInput = false;
        fileInput = true;
      }




    }




  }

  public static void editTree(StoryTree tree){
    boolean menu = true;
    while (menu){
      System.out.println("Zork Editor:");
      System.out.println("    V: View the cursor's position, option, and message.");
      System.out.println("    S: Select a child of this cursor (options are 1, 2, and 3).");
      System.out.println("    O: Set the option of the cursor.");
      System.out.println("    M: Set the message of the cursor.");
      System.out.println("    A: Add a child StoryNode to the cursor.");
      System.out.println("    D: Delete one of the cursor's children and all its descendants.");
      System.out.println("    R: Move the cursor to the root of the tree.");
      System.out.println("    Q: Quit editing and return to main menu.");
      Scanner on = new Scanner(System.in);
      String s = on.nextLine();
      if (s.equals("v") || s.equals("V")){
        System.out.println("\nPosition: " + tree.getCursorPosition());
        System.out.println("Option: " + tree.getCursorOption());
        System.out.println("Message: " + tree.getCursorMessage());
      }
      else if (s.equals("s") || s.equals("S")){
        String children = "[";
        for (int i = 1; i <= tree.getCursor().numChildren(); i++){
          if (i == tree.getCursor().numChildren()) children += i;
          else children += i + ",";
        }
        children +="]";
        System.out.println("\nPlease select a child: " + children);
        if (on.hasNextInt()){
          int child = on.nextInt();
          if (child > 0 && child <= tree.getCursor().numChildren()){
            try{
              tree.selectChild(child);
            }
            catch (NodeNotPresentException e){
              System.out.println("No child " + child + " for current node.");
              menu = false;
              menu = true;
            }
          }
          else{
            System.out.println("No child " + child + " for current node.");
            menu = false;
            menu = true;
          }
        }
        else{
          System.out.println("Invalid input.");
          menu = false;
          menu = true;
        }
      }
      else if (s.equals("o") || s.equals("O")){
        System.out.println("Please enter a new option:");
        String option = on.nextLine();
        tree.setCursorOption(option);
      }
      else if (s.equals("m") || s.equals("M")){
        System.out.println("Please enter a new message:");
        String message = on.nextLine();
        tree.setCursorMessage(message);
      }
      else if (s.equals("a") || s.equals("A")){
        System.out.println("Enter an option:");
        String option =on.nextLine();
        System.out.println("Enter a message:");
        String message = on.nextLine();
        try{
          tree.addChild(option, message);
        }
        catch (InvalidArgumentException e){
          System.out.println("Invalid inputs.");
          menu = false;
          menu = true;
        }
        catch (TreeFullException e){
          System.out.println("Error. Cursor is full.");
          menu = false;
          menu = true;
        }
      }
      else if (s.equals("d") || s.equals("D")){
        String children = "[";
        for (int i = 1; i <= tree.getCursor().numChildren(); i++){
          if (i == tree.getCursor().numChildren()) children += i;
          else children += i + ",";
        }
        children +="]";
        System.out.println("\nPlease select a child: " + children);
        if (on.hasNextInt()){
          int child = on.nextInt();
          if (child > 0 && child <= tree.getCursor().numChildren()){
            try{
              String position = tree.getCursorPosition() + "-" + child;
              tree.removeChild(position);
            }
            catch (NodeNotPresentException e){
              System.out.println("Error. Selected child does not exist.");
              menu = false;
              menu = true;
            }
          }
          else {
            System.out.println("Invalid input.");
            menu = false;
            menu = true;
          }
        }
        else {
          System.out.println("Invalid input.");
          menu = false;
          menu = true;
        }

      }
      else if (s.equals("r") || s.equals("R")){
        tree.resetCursor();
        System.out.println("Cursor moved to root.");
      }
      else if (s.equals("q") || s.equals("Q")){
        menu = false;
      }
      else{
        System.out.println("Invalid input.");
        menu = false;
        menu = true;
      }
    }
  }

  public static void playTree(StoryTree tree){

  }


}
