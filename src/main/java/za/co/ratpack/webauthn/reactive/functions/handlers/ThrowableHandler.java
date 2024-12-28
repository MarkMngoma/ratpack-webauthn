package za.co.ratpack.webauthn.reactive.functions.handlers;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import ratpack.exec.Promise;
import ratpack.handling.Context;
import ratpack.handling.InjectionHandler;
import ratpack.http.Status;
import za.co.ratpack.webauthn.reactive.functions.helpers.HttpContentHelper;

/**
 * @author markmngoma
 * @created at 22:38 on 27/12/2024
 */
@Slf4j
public class ThrowableHandler extends InjectionHandler {

  private final HttpContentHelper httpContentHelper;

  @Inject
  public ThrowableHandler(HttpContentHelper httpContentHelper) throws NoSuitableHandleMethodException {
    this.httpContentHelper = httpContentHelper;
  }

  public void handle(Context ctx, Throwable throwable, Status errorStatus, String message) {
    log.error("ThrowableHandler@handle containing exception :: {}", throwable.getMessage());
    Promise.value(new ImmutableMap.Builder<String, Object>()
        .put("error", errorStatus.getCode())
        .put("message", message)
        .build())
      .then(errorResponse -> this.httpContentHelper.renderJson(ctx, errorResponse, errorStatus));
  }
}
