package com.fractgen.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fractgen.api.model.Fractal;
import com.fractgen.api.model.FractalDTO;
import com.fractgen.api.model.IdClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FractalSerializer  extends StdSerializer<Fractal> {
  public FractalSerializer() {
    this(null);
  }

  public FractalSerializer(Class<Fractal> t) {
    super(t);
  }

  @Override
  public void serialize(Fractal fractal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    FractalDTO fractalDTO = new FractalDTO(fractal.getId(), fractal.getDataURL(), fractal.getName(), fractal.getDescription());
    jsonGenerator.writeObject(fractalDTO);
  }
}
