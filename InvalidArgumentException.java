/** Derek Yin 113251504 Recitation Section 1
*  This class defines an exception called when an invalid argument is inputted.
*
*  @author Derek Yin
*/
public class InvalidArgumentException extends Exception{

/**
*This is the constructor for the InvalidArgumentException
*
*@param message
*the error message to be displayed
*
*/
  public InvalidArgumentException(String message){
    super(message);
  }

}
