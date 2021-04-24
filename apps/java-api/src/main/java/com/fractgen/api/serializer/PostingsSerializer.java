package com.fractgen.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fractgen.api.model.Comment;
import com.fractgen.api.model.IdClass;
import com.fractgen.api.model.Posting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostingsSerializer extends StdSerializer<List<Posting>> {
  public PostingsSerializer(){
    this(null);
  }
  public PostingsSerializer(Class<List<Posting>> t) {
    super(t);
  }

  @Override
  public void serialize(List<Posting> postings, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    List<IdClass> ids = new ArrayList<>();
    for (Posting posting : postings){
      IdClass idObj = new IdClass(posting.getId());
      ids.add(idObj);
    }
    jsonGenerator.writeObject(ids);
  }
}
