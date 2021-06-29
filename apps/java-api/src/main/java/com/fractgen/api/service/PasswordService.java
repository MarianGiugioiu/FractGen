package com.fractgen.api.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class PasswordService {
  private static final int ENCODING_STRENGTH = 10;
  public static String encodePassword(String password)
  {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(ENCODING_STRENGTH);
    return encoder.encode(password);
  }
  public static boolean matches(String password, String password2)
  {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(ENCODING_STRENGTH);
    return encoder.matches(password, password2);
  }

  public static boolean isEncoded(String password)
  {
    Pattern bcryptPattern = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

    return bcryptPattern.matcher(password).matches();
  }
}
