package za.co.ratpack.webauthn.reactive.functions.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import za.co.ratpack.webauthn.reactive.functions.helpers.HttpContentHelper;

/**
 * @author markmngoma
 * @created at 22:55 on 27/12/2024
 */
@Slf4j
@Singleton
public class WriteAuthenticationResourceHandler implements Handler {

  private final HttpContentHelper httpContentHelper;
  private final ObjectMapper objectMapper;

  @Inject
  public WriteAuthenticationResourceHandler(HttpContentHelper httpContentHelper, ObjectMapper objectMapper) {
    this.httpContentHelper = httpContentHelper;
    this.objectMapper = objectMapper;
  }

  @Override
  public void handle(Context context) throws Exception {
   log.info("WriteAuthenticationResourceHandler@handle initiated...");

  }
}
