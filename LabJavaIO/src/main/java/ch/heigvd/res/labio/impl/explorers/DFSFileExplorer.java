package ch.heigvd.res.labio.impl.explorers;

import ch.heigvd.res.labio.interfaces.IFileExplorer;
import ch.heigvd.res.labio.interfaces.IFileVisitor;

import java.io.File;
import java.util.LinkedList;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {

    vistor.visit(rootDirectory);
    if(!rootDirectory.isDirectory()) return;

    //LinkedList<File> directories = new LinkedList<>();
    //LinkedList<File> files = new LinkedList<>();

    for(final File fileEntry: rootDirectory.listFiles()){
      if (fileEntry.isDirectory()) explore(fileEntry, vistor);
      else vistor.visit(fileEntry);
    }

    /*for (final File fileEntry : rootDirectory.listFiles()) {
      if (fileEntry.isDirectory()) directories.add(fileEntry);
      else files.add(fileEntry);
    }


    for (File dir : directories) explore(dir, vistor);
    for (File f : files) vistor.visit(f);*/
    return;
  }

}

