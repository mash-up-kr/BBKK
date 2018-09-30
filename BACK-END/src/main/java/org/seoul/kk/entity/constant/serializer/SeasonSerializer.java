package org.seoul.kk.entity.constant.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.seoul.kk.entity.constant.Season;

import java.io.IOException;

public class SeasonSerializer extends StdSerializer<Season> {

    public SeasonSerializer() {
        super(Season.class);
    }

    public SeasonSerializer(Class c) {
        super(c);
    }

    @Override
    public void serialize(Season value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("season");
        gen.writeString(value.name());
        gen.writeEndObject();
    }
}
