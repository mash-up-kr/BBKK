package org.seoul.kk.entity.constant.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.seoul.kk.entity.constant.Classification;

import java.io.IOException;

public class ClassificationSerializer extends StdSerializer<Classification> {

    public ClassificationSerializer() {
        super(Classification.class);
    }

    public ClassificationSerializer(Class c) {
        super(c);
    }

    @Override
    public void serialize(Classification value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("classification");
        gen.writeString(value.name());
        gen.writeEndObject();
    }
}
