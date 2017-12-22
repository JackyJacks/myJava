package dateSerializer;

import java.io.IOException;
import java.time.LocalDate;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class JsonLocDateDes extends JsonDeserializer<LocalDate>{
	
	@Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return LocalDate.from(LocalDate.parse(jp.getText()));
    }

}
