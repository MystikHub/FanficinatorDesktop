package fanficinate;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
public class fileExplorer {
	JFileChooser chooser = new JFileChooser();
	static fileExplorer f = new fileExplorer();
	
	public static void main(String[] args) {
		f.makeChooser();
	}
	
	public File makeChooser() {
		chooser.showOpenDialog(chooser);
	    File path = chooser.getSelectedFile();
	    try {
			path.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return path;
	}
}

