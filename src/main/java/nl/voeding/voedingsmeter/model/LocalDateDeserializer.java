package nl.voeding.voedingsmeter.model;

/**
 * obtained from https://kodejava.org/how-to-format-localdate-object-using-jackson/
 *
 */

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    protected LocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    	System.out.println(parser.readValueAs(String.class));
    	return LocalDate.parse(parser.readValueAs(String.class),DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    	//return LocalDate.parse(parser.readValueAs(String.class));
    }
}
