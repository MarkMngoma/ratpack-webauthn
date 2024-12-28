package za.co.ratpack.webauthn.reactive.functions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.util.UUID;

/**
 * @author markmngoma
 * @created at 22:37 on 27/12/2024
 */
@Slf4j
public class LoggingMDCHandler implements Handler {
  @Override
  public void handle(Context ctx) {
    MDC.put("requestId", UUID.randomUUID().toString());
    ctx.next();
  }
}
