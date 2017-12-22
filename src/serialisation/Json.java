package serialisation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.TypeFactory;

public class Json<T> implements Serialisation<T>{
	
	private Class<T> clases;
    public Class<T> getClases() {
        return clases;
    }
    public void setClases(Class<T> clases) {
        this.clases = clases;
    }

    public Json(Class<T> clases) {
        this.clases = clases;
    }

    @Override
    public void toFile(ArrayList<T> object, File file){
        ObjectMapper mapper = new ObjectMapper();
        try {
            FileWriter fileWriter = new FileWriter(file);
            mapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<T> fromFile(File file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();
        CollectionType collectionType = typeFactory.constructCollectionType(ArrayList.class, this.clases);
        return mapper.readValue(file, collectionType);
    }
//	  @Override
//	  public void toFile(File file, ArrayList<T> cars) throws IOException   {
//			Gson gson = new GsonBuilder().setPrettyPrinting().create();
//			FileWriter outFile = null;
//			
//			try {
//				outFile = new FileWriter(file);
//				gson.toJson(cars, outFile);
//			}
//			finally {
//				if(outFile != null)
//					outFile.close();
//			}
//			
//		}
//
//	  public ArrayList<T> fromFile(File file) throws IOException{
//		  	ArrayList<T> groupBuild = null;
//			FileReader inFile = null;
//			
//			try {
//				inFile = new FileReader(file);
//				groupBuild = new Gson().fromJson(inFile, new TypeToken<ArrayList<T>>(){}.getType());
//			}
//			finally {
//				if(inFile != null)
//					inFile.close();
//			}
//			
//			return groupBuild;
//		}


}
