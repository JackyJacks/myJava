package dateSerializer;

import java.io.IOException;
import java.time.LocalDate;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class JsonLocDateSer extends JsonSerializer<LocalDate> {
	@Override
    public void serialize(LocalDate value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeString(value.toString());
    }
}
