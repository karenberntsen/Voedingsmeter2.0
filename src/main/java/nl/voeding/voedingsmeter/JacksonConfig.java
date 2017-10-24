package nl.voeding.voedingsmeter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * @author reindert
 *
 *	Configure the conversion of dates from JSON to Java and vice versa 
 */
@Configuration
public class JacksonConfig {
	
	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public class LocalDateSerializer extends JsonSerializer<LocalDate> {
	    @Override
	    public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
	        gen.writeString(value.format(FORMATTER));
	    }
	}
	public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
	    @Override
	    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
	        return LocalDate.parse(p.getValueAsString(), FORMATTER);
	    }
	}
	
	@Bean
	@Primary
	public ObjectMapper serializingObjectMapper() {
	    ObjectMapper objectMapper = new ObjectMapper();
	    JavaTimeModule javaTimeModule = new JavaTimeModule();
	    javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer());
	    javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
	    objectMapper.registerModule(javaTimeModule);
	    return objectMapper;
	}
}
