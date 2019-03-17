package ch.heigvd.res.labio.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {
    String[] strs = new String[2];
    boolean endlineFound = false;
    int i;
    for(i = 0; i < lines.length(); ++i){
      if(lines.charAt(i) == '\n'){
        strs[0] = lines.substring(0, i + 1);
        endlineFound = true;
        break;
      }
      if(lines.charAt(i) == '\r'){
        if(i + 1 >= lines.length() || lines.charAt(i + 1) != '\n'){
          strs[0] = lines.substring(0, i + 1);
          endlineFound = true;
          break;
        }
      }
    }
    if(!endlineFound){
      strs[0] = "";
      strs[1] = lines;
    } else {
      if(i + 1 < lines.length()){
        strs[1] = lines.substring(i + 1);
      } else {
        strs[1] = "";
      }
    }

    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    return strs;
  }

}
