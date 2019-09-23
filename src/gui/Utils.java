package gui;
import java.io.File;

public class Utils {
	
	public static final String per = "per";
	
	public static String getExtension(File f) {
		String ext = "";
		int pointIndex = f.getName().lastIndexOf('.');
		
		if(pointIndex == -1)
			return null;
		
		if(pointIndex == f.getName().length()-1)
			return null;
		
		ext = f.getName().substring(pointIndex+1, f.getName().length());
		
		return ext;
	}

}
