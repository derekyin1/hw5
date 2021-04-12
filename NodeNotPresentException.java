/** Derek Yin 113251504 Recitation Section 1
*  This class defines an exception called every time a non-existent node is attempted to be acccessed.
*
*  @author Derek Yin
*/
public class NodeNotPresentException extends Exception{

/**
*This is the constructor for the NodeNotPresentException
*
*@param message
*the error message to be displayed
*
*/
  public NodeNotPresentException(String message){
    super(message);
  }

}
