package ch.heigvd.res.labio.impl.filters;

import java.io.*;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private int counter = 1;
  private boolean newline = true;
  private boolean previousCharWasSlashR = false;
  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    if(str.length() < off + len) throw new IOException("out of range access to char[] buffer");
    for(int i = off; i < off + len; ++i){
      write(str.charAt(i));
    }
  }

  private void writeNumbering() throws IOException {
    String number = Integer.toString(counter);
    super.write(number, 0, number.length());
    ++counter;
    super.write('\t');
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    if(cbuf.length < off + len) throw new IOException("out of range access to char[] buffer");
    for(int i = off; i < off + len; ++i){
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    if(newline){
      writeNumbering();
      newline = false;
    }

    if(c == '\r'){
      super.write(c);
      previousCharWasSlashR = true;
      return;
    }

    if(c == '\n'){
      super.write(c);
      writeNumbering();
      previousCharWasSlashR = false;
    } else {
      if(previousCharWasSlashR){
        writeNumbering();
        previousCharWasSlashR = false;
      }
      super.write(c);
    }

  }

}
