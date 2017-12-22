package serialisation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



public interface Serialisation<T> {
	
	void toFile(ArrayList<T> object, File file) throws IOException;
    ArrayList<T> fromFile(File file) throws Exception;
	
	
}
