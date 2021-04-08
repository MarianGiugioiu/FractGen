package com.fractgen.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fractgen.api.model.Posting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostingSerializer  extends StdSerializer<Posting> {
  public PostingSerializer() {
    this(null);
  }

  public PostingSerializer(Class<Posting> t) {
    super(t);
  }

  @Override
  public void serialize(Posting posting, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    Long id = posting.getId();
    jsonGenerator.writeObject(id);
  }
}
