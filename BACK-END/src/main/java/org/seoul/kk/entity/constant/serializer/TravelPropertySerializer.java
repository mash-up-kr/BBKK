package org.seoul.kk.entity.constant.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.seoul.kk.entity.constant.TravelProperty;

import java.io.IOException;

public class TravelPropertySerializer extends StdSerializer<TravelProperty> {

    public TravelPropertySerializer() {
        super(TravelProperty.class);
    }

    public TravelPropertySerializer(Class c) {
        super(c);
    }

    @Override
    public void serialize(TravelProperty value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("property");
        gen.writeString(value.getType());
        gen.writeEndObject();
    }
}
