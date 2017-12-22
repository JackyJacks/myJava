package serialisation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Xml<T> implements Serialisation<T> {
	
	private Class<T> xml;
	  
	  public Class<T> getJson() {
	    return xml;
	  }
	  
	  public void setXml(Class<T> xml) {
	    this.xml = xml;
	  }

	  public Xml(Class<T> xml) {
	    this.xml = xml;
	  }
	  @Override
	  public void toFile(ArrayList<T> cars, File file) {
			XStream xStream = new XStream(new DomDriver());
			xStream.autodetectAnnotations(true);
			xStream.registerConverter(new dateSerializer.XmlDateSer());
			String strObj = xStream.toXML(cars);
	        FileWriter writer = null;
	        try {
	        	writer = new FileWriter(file);
	            writer.write(strObj);
	            writer.close();
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
		}

	@SuppressWarnings("unchecked")
	@Override
    public ArrayList<T> fromFile(File file) throws Exception {
		XStream xStream = new XStream(new DomDriver());
        FileReader fileReader = null;
        try {
        	fileReader = new FileReader(file);
        	return (ArrayList<T>) xStream.fromXML(fileReader);
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        }
        return null;
	}
}
