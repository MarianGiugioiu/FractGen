package com.fractgen.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fractgen.api.model.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfilesSerializer extends StdSerializer<List<Profile>> {
  public ProfilesSerializer(){
    this(null);
  }
  public ProfilesSerializer(Class<List<Profile>> t) {
    super(t);
  }

  @Override
  public void serialize(List<Profile> profiles, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    List<Long> ids = new ArrayList<>();
    for (Profile profile : profiles){
      ids.add(profile.getId());
    }
    jsonGenerator.writeObject(ids);
  }
}
