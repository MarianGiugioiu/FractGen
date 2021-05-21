package com.fractgen.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fractgen.api.dto.IdClass;
import com.fractgen.api.model.Account;

import java.io.IOException;

public class AccountSerializer  extends StdSerializer<Account> {
  public AccountSerializer() {
    this(null);
  }

  public AccountSerializer(Class<Account> t) {
    super(t);
  }

  @Override
  public void serialize(Account account, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    IdClass id = new IdClass(account.getId());
    jsonGenerator.writeObject(id);
  }
}
