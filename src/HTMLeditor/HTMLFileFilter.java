package HTMLeditor;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by SergeyND on 29.05.2017.
 */
public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        if (f.isDirectory())
            return true;
        else if (!f.isDirectory()) {
            String path = f.getName().toLowerCase();
            return (path.endsWith(".htm")||path.endsWith(".html"));
        }
        return false;
    }

      /*  if (f.isDirectory()) {
            return true;
        }
        else if (!f.isDirectory()) {
            String fileName = f.getName().toLowerCase();
            return fileName.endsWith(".html") || fileName.endsWith(".htm");
        }

        return false;
    }*/

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }



    }
