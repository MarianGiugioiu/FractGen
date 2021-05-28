package com.fractgen.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fractgen.api.model.Comment;
import com.fractgen.api.dto.IdClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommentsSerializer extends StdSerializer<List<Comment>> {
  public CommentsSerializer(){
    this(null);
  }
  public CommentsSerializer(Class<List<Comment>> t) {
    super(t);
  }

  @Override
  public void serialize(List<Comment> comments, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    List<IdClass> ids = new ArrayList<>();
    for (Comment comment : comments){
      IdClass idObj = new IdClass(comment.getId());
      ids.add(idObj);
    }
    jsonGenerator.writeObject(ids);
  }
}
