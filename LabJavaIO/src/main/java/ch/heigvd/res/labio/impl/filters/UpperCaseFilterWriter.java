package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    //note: a more optimized implementation could be written
    String s = str.substring(off, off + len).toUpperCase();
    super.write(s, 0, s.length());
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    char[] uppercaseCbuf = new char[len];
    for(int i = 0; i < len; ++i)
      uppercaseCbuf[i] = Character.toUpperCase(cbuf[i + off]);
    super.write(uppercaseCbuf, 0, len);
  }

  @Override
  public void write(int c) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    super.write(Character.toUpperCase(c));
  }

}
