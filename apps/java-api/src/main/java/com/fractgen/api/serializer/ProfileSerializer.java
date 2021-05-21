package com.fractgen.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fractgen.api.dto.IdClass;
import com.fractgen.api.model.Profile;

import java.io.IOException;

public class ProfileSerializer  extends StdSerializer<Profile> {
  public ProfileSerializer() {
    this(null);
  }

  public ProfileSerializer(Class<Profile> t) {
    super(t);
  }

  @Override
  public void serialize(Profile profile, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    IdClass id = new IdClass(profile.getId());
    jsonGenerator.writeObject(id);
  }
}
