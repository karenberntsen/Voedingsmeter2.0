package nl.voeding.voedingsmeter.model;

/**
 * obtained from https://kodejava.org/how-to-format-localdate-object-using-jackson/
 *
 */

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateSerializer extends StdSerializer<LocalDate> {

    public LocalDateSerializer() {
        super(LocalDate.class);
    }

    @Override
    public void serialize(LocalDate value, JsonGenerator generator, SerializerProvider provider) throws IOException {
    //	System.out.println(value);
    //	System.out.println(value.toString());
    //	System.out.println(value.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    //	generator.writeString(value.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    	generator.writeString(value.format(DateTimeFormatter.ISO_DATE));    
    }
}