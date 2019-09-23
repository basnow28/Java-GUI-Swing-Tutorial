package gui;
import java.io.File;

import javax.swing.filechooser.FileFilter;

public class PersonFileFilter extends FileFilter {
	
	@Override
	public boolean accept(File f) {
		if(f.isDirectory())
			return true;
		
		String fileExt = Utils.getExtension(f);
		
		if(fileExt == null)
			return false;
		
		if(fileExt == Utils.per)
			return true;
		
	/*	if(fileExt == "zip")
			return true;
		
		System.out.println(fileExt);*/
		
		return false;
	}

	@Override
	public String getDescription() {
		return "per";
	}

}
